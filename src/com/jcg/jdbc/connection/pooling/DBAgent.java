package com.jcg.jdbc.connection.pooling; //package containing the connection pool class

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class DBAgent {
	
	//
	private DB db; // database we are connecting
	private String pathToServer; //path to Server config XML
	private String pathToQueries;  //path to SQL Queries XML

	//JDBC Driver Name and Database URL
	private String JDBC_DRIVER;
	private String JDBC_DB_URL;
	
	//Database Credentials
	private String JDBC_USER; 
	private String JDBC_PWD ;
	
	//Global Data Source
	private DataSource dataSource;
	
	//SQLQueries
	private List<SQLQuery> sqlQueriesToRun; // the collection of queries read from the file
	
	private static GenericObjectPool genericPool = null;
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	
	
	//Getters and Setters
		public String getPathToServer() {
			return pathToServer;
		}

		public void setPathToServer(String pathToServer) {
			this.pathToServer = pathToServer;
		}

		public String getPathToQueries() {
			return pathToQueries;
		}

		public void setPathToQueries(String pathToQueries) {
			this.pathToQueries = pathToQueries;
		}

		public List<SQLQuery> getSqlQueriesToRun() {
			return sqlQueriesToRun;
		}

		public void setSqlQueriesToRun(List<SQLQuery> sqlQueriesToRun) {
			this.sqlQueriesToRun = sqlQueriesToRun;
		}
		//End of Getters and Setters
		
	//Default Constructor
	public DBAgent()
	{
		
	}
	
	//overloaded Constructor
	public DBAgent(String sv, String sql)
	{
		//First setup parameters so we know the path to read the files from 
		setupParameters(sv,sql);
		
		//Read the SQL messages and replace the parameters in the string.
		setSqlQueriesToRun(retrieveQueries(this.pathToQueries)); //set the list of queries from the file.
		
		//Start the connectionPool
		try {
			dataSource = this.configurePool();
			printDbStatus(); //print details of the pool
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//setup the parameters
	private void setupParameters(String pathToSv, String pathToSQL )
	{
		db = new DB();
		setPathToServer(pathToSv);
		setPathToQueries(pathToSQL);
		
		//Read DB parameters from specified file and storing in the DB Class
		 try{
	        	File file = new File(getPathToServer());
	        	JAXBContext jContext = JAXBContext.newInstance(DB.class);
	        	
	        	Unmarshaller jbUnmarshaller = jContext.createUnmarshaller();
	        	db = (DB) jbUnmarshaller.unmarshal(file);
	        	//assigning the values;
				JDBC_DRIVER = db.getJdbcdriver();
				JDBC_DB_URL = db.getJdbcdburl();
				JDBC_USER =  db.getJdbcuser();
				JDBC_PWD = db.getJdbcpwd();
            }
	      catch(JAXBException ex)
	        {
	        	ex.printStackTrace();
	        }
			
	}
		
	//retrieve SQLQueries
	private List<SQLQuery> retrieveQueries(String pathToSQL)
	{
		
	    List<SQLQuery> sqlQueries = new ArrayList<SQLQuery>();
		SQLQuery tempQuery;
	       
	       try{
	    	   
	    	   //read the file, and get to the root element
	    	   File input = new File(pathToSQL);
	    	   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	   DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
	    	   Document doc = docBuilder.parse(input);
	    	   doc.getDocumentElement().normalize();
	    	   NodeList nodeList = doc.getElementsByTagName("sqlQuery");
	    	   
	    	   //let's print it out
	    	   for(int i=0; i < nodeList.getLength(); i++)
	    	   {
	    		   tempQuery = new SQLQuery();
	    		   Node tempNode = nodeList.item(i);
	    		   
	    		   if(tempNode.getNodeType() == Node.ELEMENT_NODE)
	    		   {
	    			   Element nodeElement = (Element)tempNode;
	    			   
	    			   //set ID and no of Params
	    			   tempQuery.setId(Integer.parseInt(nodeElement.getAttribute("id")));
	    			   tempQuery.setNoOfParameters(Integer.parseInt(nodeElement.getAttribute("noOfParams")));
	    			   tempQuery.setSqlQueryString(nodeElement.getElementsByTagName("sqlQueryString").item(0).getTextContent());
	    			   sqlQueries.add(tempQuery);
	    		   } 	   		   
	    	   
	    	   }
	    	   return sqlQueries;
	       }
	       catch(Exception ex)
	    	   {
	    		   ex.printStackTrace();
	    		   return sqlQueries;
	    	   }
	}
	

	@SuppressWarnings("unused")
	public DataSource configurePool() throws Exception{
		
		Class.forName(JDBC_DRIVER);
		
		//Instantiating the Generic Object Pool that holds pool of connection objects
		genericPool =new GenericObjectPool();
		genericPool.setMaxActive(5); //maximum of 5 connections.
		
		//Create a connection Factory object for the pool
		ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL,JDBC_USER,JDBC_PWD);
		
		//Create a poolable connectionfactory 
		PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf,genericPool,null,null,false,true);
		return new PoolingDataSource(genericPool);		
	}
	
	//a method just to show the status of the pool
	private void printDbStatus(){
		System.out.println("Max.:"+getConnectionPool().getMaxActive()+"\n Active: "+ getConnectionPool().getNumActive() + " ;\n Idle: "+ getConnectionPool().getNumIdle());
	}
	
	public GenericObjectPool getConnectionPool()
	{
		return genericPool;
	}
	
	public ResultSet getResults(int queryId, String[] parameters)
	{
					
		try{
			conn = this.dataSource.getConnection();
			
			//printing poolstatus
			printDbStatus();
			
			//getquery and replace parameters
			String query = getSqlQueriesToRun().get(queryId).getSqlQueryString();
			
			for(int i= 0; i < parameters.length; i++)
			{
				String g = "P" + Integer.toString(i);
				System.out.println("P"+i);
				query = query.replaceAll(g, parameters[i]);
			}
			
			ps = conn.prepareStatement(query);
			System.out.println(query);
			rs = ps.executeQuery();
			return rs;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public int executeSQL(int queryId, String[] parameters){
		
		
		int noOfRows = 0;
		
		try{
			conn = this.dataSource.getConnection();
			conn.setAutoCommit(false); //to prevent autocommit i.e. entering transaction mode
			
			//printing poolstatus
			printDbStatus();
			
			//getquery and replace parameters
			String query = getSqlQueriesToRun().get(queryId).getSqlQueryString();
			System.out.println("Query = " +query);
			
			for(int i= 0; i < parameters.length; i++)
			{
				String g = "P" + Integer.toString(i);
				query = query.replaceAll(g, parameters[i]);
			}
			
			System.out.println("--query--"+query);
			ps = conn.prepareStatement(query);
			noOfRows = ps.executeUpdate(); //transaction not executed
			
			conn.commit(); //commit the transaction
			
			return noOfRows;
		
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return 0;
	
	}
	
	public void closeConnection(){
		
		// Closing ResultSet, PreparedStatement, Connection Object

		if(conn != null) {
			try {
				conn.close();
				if(rs != null) {
					rs.close();
				 }
				if(ps != null) {
					ps.close();
			    }
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
		}
		
	}

}


