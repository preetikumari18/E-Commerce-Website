package com.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.bean.User;
import com.jcg.jdbc.connection.pooling.DBAgent;

public class UserRegister {
	
	//create account for the new user
	public String createAccount(User user){
		
		   String addressid = null;
		   String userid = null;
	   
		   long millis=System.currentTimeMillis();
	       Date date = new Date(millis);
	       SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	       DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
	        
	       String checkAddree[] ={user.getPincode()};
	       ResultSet rs2 = dba.getResults(9, checkAddree);
		   try {
			if(!rs2.next()){
				   String address[]={user.getStreet(),user.getProvince(), user.getCountry(),user.getPincode()};	
				   dba.executeSQL(6, address);	
				   dba.closeConnection();
			   }
		  } catch (SQLException e1) {
			e1.printStackTrace();
		  }			
	       
		   String parameter[] = {user.getFirstname(), user.getPassword(),sdf.format(date),"true",user.getFirstname(),user.getLastname(),
				   user.getEmail(),user.getPhoneno(),"0",user.getPayment()};
		   dba.executeSQL(3, parameter);
		   dba.closeConnection();
			
		   String adrsid[] = {user.getPincode()};
		   ResultSet rs = dba.getResults(9, adrsid);
		   try {
			while(rs.next()){   
			addressid = Integer.toString(rs.getInt("id"));
			user.setAddressid(addressid);
			}
			dba.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   
		   String uid[] = {user.getEmail()};
		   ResultSet rs1 = dba.getResults(10, uid);
		   try {
			while(rs1.next()){   
			userid = Integer.toString(rs1.getInt("userid"));
			user.setUserid(userid);
			}
			dba.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    
		 String useraddress[]={addressid,userid};
		 dba.executeSQL(7, useraddress);
		 dba.closeConnection();
		
		return "success";
			
	}
	
	//create account for the new user while checkout
		public String createAccountCheckout(User user){
			
			   String addressid = null;
			   String userid = null;
		   
			   long millis=System.currentTimeMillis();
		       Date date = new Date(millis);
		       SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        
		       DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		        
		      /* String checkAddree[] ={user.getPincode()};
		       ResultSet rs2 = dba.getResults(9, checkAddree);
			   try {
				if(!rs2.next()){
					   String address[]={user.getStreet(),user.getProvince(), user.getCountry(),user.getPincode()};	
					   dba.executeSQL(6, address);	
					   dba.closeConnection();
				   }
			  } catch (SQLException e1) {
				e1.printStackTrace();
			  }			*/
		       
			   String parameter[] = {user.getFirstname(), user.getPassword(),sdf.format(date),"true",user.getFirstname(),user.getLastname(),
					   user.getEmail(),user.getPhoneno(),"0",user.getPayment()};
			   dba.executeSQL(3, parameter);
			   dba.closeConnection();
				
			   String adrsid[] = {"new"};
			   ResultSet rs = dba.getResults(9, adrsid);
			   try {
				while(rs.next()){   
				addressid = Integer.toString(rs.getInt("id"));
				System.out.println("---"+addressid);
				
				user.setAddressid(addressid);
				}
				dba.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			   
			   System.out.println("---"+addressid);
			   
			   String addressnew[]={user.getStreet(),user.getProvince(), user.getCountry(),user.getPincode(), addressid};	
			   dba.executeSQL(19, addressnew);	
			   dba.closeConnection();
			   
			   
			   String uid[] = {user.getEmail()};
			   ResultSet rs1 = dba.getResults(10, uid);
			   try {
				while(rs1.next()){   
				userid = Integer.toString(rs1.getInt("userid"));
				user.setUserid(userid);
				}
				dba.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			    
			 String useraddress[]={addressid,userid};
			 dba.executeSQL(7, useraddress);
			 dba.closeConnection();
			 
			 String userPO[]={user.getLastname(),user.getFirstname(),user.getEmail(),addressid};
			 dba.executeSQL(20, userPO);
			 dba.closeConnection();
			 
			
			return "success";
				
		}
	
	//check whether accountName already exists
	public String register(String email){
		DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
        
        String checkNewUser[] ={email};
        ResultSet rs = dba.getResults(5, checkNewUser);
        try {
			if(!rs.next()){
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return "fail";
		
	}
	
}
