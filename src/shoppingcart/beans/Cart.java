package shoppingcart.beans;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.jcg.jdbc.connection.pooling.DBAgent;

//import db.DbConnection;

public class Cart {

	 // pickUp method inserts selected items to database
	public boolean pickUp(String strModelNo, String strDescription, String strPrice, String strQuantity, User user){
		
		
		String poID = "";
		int count = 0;
		int cdCount=0;
		int quantity = 0;
		String duplicateCD = null;
		List<String> cdlist = new ArrayList<String>();

		
		DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		
		String strLname = user.getLastname();
		String strFname = user.getFirstname();
		String strEmail = user.getEmail();
		String addressid = user.getAddressid();
		
			
		String checkingId1[]={"PROCESSED",strEmail};
		ResultSet rs = dba.getResults(13, checkingId1);
		try {
			if(rs.next()) {
			count++;
			}
		
		dba.closeConnection(); 
		System.out.println("The counter -------- ="+ count);
		
		if (count == 0) {
			String details[]={strLname,strFname,"PROCESSED",addressid,strEmail};
			 dba.executeSQL(14, details); //insert into PO table
			dba.closeConnection();
			 
			 
			String checkingId2[]={"PROCESSED",strEmail};
		    ResultSet resultS = dba.getResults(13, checkingId2);// get purchase order id from PO table 
		    if(resultS.next()) {
			poID = resultS.getString("id");
		    }
			dba.closeConnection();
		    
			String parameter[] = {poID,strModelNo, strPrice,strQuantity, strDescription};
			//cartitemBean.setQuantity(Integer.parseInt(strQuantity));
			dba.executeSQL(0, parameter);
			
			dba.closeConnection();
	 		
	 		return true;
	 		
		}else if(count > 0) {
			String checkingId2[]={"PROCESSED",strEmail};
		    ResultSet rss = dba.getResults(13, checkingId2);// get the id
		    
		    if(rss.next()){
			poID = rss.getString("id");
		    }
		    
		    dba.closeConnection();
		    
			 String checkingCDID[]= {poID};
			 ResultSet rsss = dba.getResults(15, checkingCDID);  //get cdid and stores in list
			 
			 while(rsss.next()){
				 cdlist.add(rsss.getString("cdid"));
			 }
			 
			 dba.closeConnection();
			 
			 System.out.println("cdlist----"+cdlist);
				 
				 for (String strCD : cdlist) {
		               if(strModelNo.equalsIgnoreCase(strCD)){
		            	   System.out.println("-------");
	                         duplicateCD = strCD;
	                         cdCount++;
		               } 
		           }
				 
								 
				 if(cdCount==0){
					 String parameter[] = {poID,strModelNo, strPrice,strQuantity, strDescription};
						int z1 = dba.executeSQL(0, parameter);
				 		System.out.println("No of rows inserted ="+ z1);
				 		//cartitemBean.setQuantity(Integer.parseInt(strQuantity));
				 		
				 		dba.closeConnection();
				 	    return true;
				 }else{
					 String fetchquantity[]={poID,duplicateCD};
					 ResultSet rs1 = dba.getResults(16, fetchquantity);
					 if(rs1.next()) {
						 quantity = Integer.parseInt(rs1.getString("quantity")); 
					 }
					 					 
					 quantity = quantity + 1;
					 String newQuantity = Integer.toString(quantity);
					 //cartitemBean.setQuantity(Integer.parseInt(newQuantity));
					 dba.closeConnection();
					 
					 
					 String updateparameter[] = {newQuantity,poID,duplicateCD};
					 dba.executeSQL(17, updateparameter);
					 dba.closeConnection();
					 
					 return true;
				 }
				 
				 
				 
				 
				/*if(rsss.getString("cdid").equalsIgnoreCase(strModelNo))
			  return false;
			  else {
					 System.out.println("-------2nd----"+strModelNo);
				        System.out.println("-------2nd----"+strPrice);
				        System.out.println("-------2nd----"+strQuantity);
					 String parameter[] = {poID,strModelNo,strPrice,strQuantity};
						int z1 = dba.executeSQL(0, parameter);
				 		System.out.println("No of rows inserted ="+ z1);
				 	return true;	
				 }*/
			 
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
	}	
	
public boolean pickUpNewUser(String strModelNo, String strDescription, String strPrice, String strQuantity){
		
		
		String poID = "";
		int count = 0;
		int cdCount=0;
		int quantity = 0;
		String duplicateCD = null;
		List<String> cdlist = new ArrayList<String>();
		String addressid = null;
		
		DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		
		String checkAddree[] ={"new"};
	    ResultSet rs2 = dba.getResults(9, checkAddree);
	    
	    System.out.println(rs2);
	    
		   try {
			if(!rs2.next()){
				
				System.out.println("---------if---");
			
				   String address[]={"new","new", "new", "new"};	
				   dba.executeSQL(6, address);	
			
			dba.closeConnection();	
			
			String addressidnew[] = {"new"};
			ResultSet rs5 = dba.getResults(9, addressidnew);
			while(rs5.next())
			addressid = Integer.toString(rs5.getInt("id"));

			dba.closeConnection();
			}else{
				System.out.println("=====else===");
				addressid = Integer.toString(rs2.getInt("id"));
			}
						
			System.out.println("addressid------"+addressid);
			//String addressidnew[] = {"new"};
			//ResultSet rs5 = dba.getResults(9, addressidnew);
			//addressid = Integer.toString(rs5.getInt("id"));
			//dba.closeConnection();
//			   }else{
//				   addressid = Integer.toString(rs2.getInt("id"));
//			   }
		  } catch (SQLException e1) {
			e1.printStackTrace();
		  }	
		
		 
		String checkingId1[]={"PROCESSED","new"};
		ResultSet rs = dba.getResults(13, checkingId1);
		try {
			if(rs.next()) {
			count++;
			}
		
		dba.closeConnection(); 
		System.out.println("The counter -------- ="+ count);
		
		System.out.println("The counter -------- ="+ addressid);
		
		if (count == 0) {
			String details[]={"new","new","PROCESSED",addressid,"new"};
			 dba.executeSQL(14, details); //insert into PO table
			dba.closeConnection();
			 
			 
			String checkingId2[]={"PROCESSED","new"};
		    ResultSet resultS = dba.getResults(13, checkingId2);// get purchase order id from PO table 
		    while(resultS.next()) {
			poID = resultS.getString("id");
		    }
			dba.closeConnection();
		    
			String parameter[] = {poID,strModelNo, strPrice,strQuantity, strDescription};
			//cartitemBean.setQuantity(Integer.parseInt(strQuantity));
			dba.executeSQL(0, parameter);
			
			dba.closeConnection();
	 		
	 		return true;
	 		
		}else if(count > 0) {
			String checkingId2[]={"PROCESSED","new"};
		    ResultSet rss = dba.getResults(13, checkingId2);// get the id
		    
		    if(rss.next()){
			poID = rss.getString("id");
		    }
		    
		    dba.closeConnection();
		    
			 String checkingCDID[]= {poID};
			 ResultSet rsss = dba.getResults(15, checkingCDID);  //get cdid and stores in list
			 
			 while(rsss.next()){
				 cdlist.add(rsss.getString("cdid"));
			 }
			 
			 dba.closeConnection();
			 
			 System.out.println("cdlist----"+cdlist);
				 
				 for (String strCD : cdlist) {
		               if(strModelNo.equalsIgnoreCase(strCD)){
		            	   System.out.println("-------");
	                         duplicateCD = strCD;
	                         cdCount++;
		               } 
		           }
				 
								 
				 if(cdCount==0){
					 String parameter[] = {poID, strModelNo, strPrice,strQuantity, strDescription};
						int z1 = dba.executeSQL(0, parameter);
				 		System.out.println("No of rows inserted ="+ z1);
				 		//cartitemBean.setQuantity(Integer.parseInt(strQuantity));
				 		
				 		dba.closeConnection();
				 	    return true;
				 }else{
					 String fetchquantity[]={poID,duplicateCD};
					 ResultSet rs1 = dba.getResults(16, fetchquantity);
					 if(rs1.next()) {
						 quantity = Integer.parseInt(rs1.getString("quantity")); 
					 }
					 					 
					 quantity = quantity + 1;
					 String newQuantity = Integer.toString(quantity);
					 //cartitemBean.setQuantity(Integer.parseInt(newQuantity));
					 dba.closeConnection();
					 
					 
					 String updateparameter[] = {newQuantity,poID,duplicateCD};
					 dba.executeSQL(17, updateparameter);
					 dba.closeConnection();
					 
					 return true;
				 }
				 
				 
				 
				 
				/*if(rsss.getString("cdid").equalsIgnoreCase(strModelNo))
			  return false;
			  else {
					 System.out.println("-------2nd----"+strModelNo);
				        System.out.println("-------2nd----"+strPrice);
				        System.out.println("-------2nd----"+strQuantity);
					 String parameter[] = {poID,strModelNo,strPrice,strQuantity};
						int z1 = dba.executeSQL(0, parameter);
				 		System.out.println("No of rows inserted ="+ z1);
				 	return true;	
				 }*/
			 
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return false;
	}	
	

//updateQuantity method update selected items to database
public boolean updateQuantity (String strModelNo, String strEmail){

    String poID = "";
    int quantityItem = 0;
    DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
    


    // get purchase order id from PO table 
    String checkingId[]={"PROCESSED", strEmail};
    ResultSet resultS = dba.getResults(13, checkingId);

    try {
		while(resultS.next()) {
		     poID = resultS.getString("id");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

    dba.closeConnection();
    
    String quantity[]={poID,strModelNo};
    ResultSet rs1 = dba.getResults(16, quantity);
    try {
		while(rs1.next()){
			quantityItem = rs1.getInt("quantity");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    quantityItem = quantityItem + 1;
    		
    // update the price and quantity for specific purchase order id and cd id
    String parameter[] = {Integer.toString(quantityItem),poID,strModelNo};
    int z = dba.executeSQL(17, parameter);
    dba.closeConnection();
    if(z==1)
    	return true;
    else    
	    return false;

 }


//updateQuantity method update selected items to database
public boolean deleteQuantity (String strModelNo, String strEmail){

  int quantityItem = 0;	
  String poID = "";
  DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
  int z = 0;


  // get purchase order id from PO table 
  String checkingId[]={"PROCESSED", strEmail};
  ResultSet resultS = dba.getResults(13, checkingId);

  try {
		while(resultS.next()) {
		     poID = resultS.getString("id");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

  dba.closeConnection();
  
  if(!poID.equals(null) && !poID.equals("")){
  String quantity[]={poID,strModelNo};
  ResultSet rs1 = dba.getResults(16, quantity);
  try {
		while(rs1.next()){
			quantityItem = rs1.getInt("quantity");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
  quantityItem = quantityItem - 1;
  
  if(quantityItem>0){
  // update the price and quantity for specific purchase order id and cd id
  String parameter[] = {Integer.toString(quantityItem),poID,strModelNo};
  z = dba.executeSQL(17, parameter);
  dba.closeConnection();
  }else if(quantityItem==0){
	  String parameter[] = {poID,strModelNo};
	  z = dba.executeSQL(24, parameter);
	  dba.closeConnection();
  }
  
  
  if(z==1)
  	return true;
  else    
    return false;
}else
	return false;

}

}

