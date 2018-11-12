package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.User;
import com.jcg.jdbc.connection.pooling.DBAgent;

public class UserLogin {

	public User login(String email, String password){
		
		DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		String parameter[] = {email,password};
		ResultSet rs = dba.getResults(2, parameter);
		
		try {
			if (!rs.next()) {
				  return null;
				} else {
					User accountInfo = new User();
				   do {
					    accountInfo.setUserid(Integer.toString(rs.getInt("userid")));
					    accountInfo.setFirstname(rs.getString("Firstname"));
						accountInfo.setLastname(rs.getString("LastName"));
						accountInfo.setEmail(rs.getString("email"));
						accountInfo.setPassword(rs.getString("password"));
						accountInfo.setPhoneno(rs.getString("phoneno"));
						accountInfo.setPayment(rs.getString("paymentmode"));						
				  } while (rs.next());
				   
				   dba.closeConnection();
				   
				   String userid[] = {accountInfo.getUserid()};
				   ResultSet rs1 = dba.getResults(11, userid);
				   String addressid = null;
				   while (rs1.next()){
					 addressid =  Integer.toString(rs1.getInt("addressid"));
				   }
				   
				   dba.closeConnection();
				   
				   String address[] = {addressid};
				   ResultSet rs3 = dba.getResults(12, address);
				   while (rs3.next()){
					    accountInfo.setAddressid(Integer.toString(rs3.getInt("id")));
					    accountInfo.setStreet(rs3.getString("street"));
						accountInfo.setProvince(rs3.getString("province"));
						accountInfo.setCountry(rs3.getString("country"));
						accountInfo.setPincode(rs3.getString("zip"));
					   }
				   
				   dba.closeConnection();
				   
				   System.out.println("==="+accountInfo);
				   
				  return accountInfo; 
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
public User checkoutlogin(String email, String password){
		
		DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		String parameter[] = {email,password};
		ResultSet rs = dba.getResults(2, parameter);
		
		try {
			if (!rs.next()) {
				  return null;
				} else {
					User accountInfo = new User();
				   do {
					    accountInfo.setUserid(Integer.toString(rs.getInt("userid")));
					    accountInfo.setFirstname(rs.getString("Firstname"));
						accountInfo.setLastname(rs.getString("LastName"));
						accountInfo.setEmail(rs.getString("email"));
						accountInfo.setPassword(rs.getString("password"));
						accountInfo.setPhoneno(rs.getString("phoneno"));
						accountInfo.setPayment(rs.getString("paymentmode"));						
				  } while (rs.next());
				   
				   dba.closeConnection();
				   
				   String userid[] = {accountInfo.getUserid()};
				   ResultSet rs1 = dba.getResults(11, userid);
				   String addressid = null;
				   while (rs1.next()){
					 addressid =  Integer.toString(rs1.getInt("addressid"));
				   }
				   
				   dba.closeConnection();
				   
				   String address[] = {addressid};
				   ResultSet rs3 = dba.getResults(12, address);
				   while (rs3.next()){
					    //accountInfo.setAddressid(Integer.toString(rs3.getInt("id")));
					    accountInfo.setStreet(rs3.getString("street"));
						accountInfo.setProvince(rs3.getString("province"));
						accountInfo.setCountry(rs3.getString("country"));
						accountInfo.setPincode(rs3.getString("zip"));
					   }
				   
				   dba.closeConnection();
				   
				   String adrsid[] = {"new"};
				   ResultSet rs2 = dba.getResults(9, adrsid);
				   String addressidNew = null;
				  
					while(rs.next()){ 
						addressidNew =  Integer.toString(rs2.getInt("id"));
						accountInfo.setAddressid(addressid);
					}
				   
					String addressnew[]={accountInfo.getStreet(),accountInfo.getProvince(), accountInfo.getCountry(),accountInfo.getPincode(), addressidNew};	
					dba.executeSQL(19, addressnew);	
					dba.closeConnection();
				   
				   System.out.println("==="+accountInfo);
				   
				   return accountInfo; 
				}
		}catch(Exception e){
			e.printStackTrace();
			
			return null;		
		}
	
}
}