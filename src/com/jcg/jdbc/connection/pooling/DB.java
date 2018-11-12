/**
 * 
 */
package com.jcg.jdbc.connection.pooling;

import javax.xml.bind.annotation.*;

/**
 * @author Sai
 *
 */

@XmlRootElement
public class DB {
	
//private String pathToDbConfig;	
private String jdbcdriver;
private String jdbcdburl;
private String jdbcuser;
private String jdbcpwd;

//constructor that loads the configuration

public String getJdbcdriver() {
	return jdbcdriver;
}

@XmlElement
public void setJdbcdriver(String jdbcdriver) {
	this.jdbcdriver = jdbcdriver;
}
public String getJdbcdburl() {
	return jdbcdburl;
}

@XmlElement
public void setJdbcdburl(String jdbcdburl) {
	this.jdbcdburl = jdbcdburl;
}
public String getJdbcuser() {
	return jdbcuser;
}

@XmlElement
public void setJdbcuser(String jdbcuser) {
	this.jdbcuser = jdbcuser;
}
public String getJdbcpwd() {
	return jdbcpwd;
}

@XmlElement
public void setJdbcpwd(String jdbcpwd) {
	this.jdbcpwd = jdbcpwd;
}





}
