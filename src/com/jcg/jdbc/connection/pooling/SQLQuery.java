package com.jcg.jdbc.connection.pooling;

public class SQLQuery {
	
	private int id; //queryId
	private int noOfParameters; //no of Parameters
	private String sqlQueryString; //queryString
	private String[] sqlParameters; //queryParameter
	
	public int getNoOfParameters() {
		return noOfParameters;
	}

	public void setNoOfParameters(int noOfParameters) {
		this.noOfParameters = noOfParameters;
	}
	
	public String getSqlQueryString() {
		return sqlQueryString;
	}
	
	public void setSqlQueryString(String sqlQuery) {
		this.sqlQueryString = sqlQuery;
	}
	
	public String[] getSqlParameters() {
		return sqlParameters;
	}
	
	public void setSqlParameters(String[] sqlParameters) {
		this.sqlParameters = sqlParameters;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
