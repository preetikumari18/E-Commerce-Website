package shoppingcart.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jcg.jdbc.connection.pooling.DBAgent;

public class StoreProducts {

	public StoreProducts() {
		// TODO Auto-generated constructor stub
	}
	
	 
	//gets the list of product categories for the store 
	public ArrayList<String> getCategoryList(){
		  
	    DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		String parameter[] = {};
 		ResultSet rs = dba.getResults(8, parameter);
 		ArrayList<String> category = new ArrayList<String>();
		
 		try {
			while(rs.next()){
				category.add(rs.getString("category"));
			}
		} catch (SQLException e) {
		}
 	  	return category; 
    }
	 
	 public ResultSet getProductList (String categoryid) {
		 
		 DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
			String parameter[] = {categoryid};
			ResultSet rs = dba.getResults(1, parameter);
			return rs;
		 
	 }
	 
	//gets the detailed product information for a product.

	 public String getProductInfo(String cdid) {

	 DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");

	 String detail = "";
	 
	 String parameter[] = {cdid};
     ResultSet rs = dba.getResults(25, parameter);
      
	   try {
		while (rs.next()) {
		      detail= rs.getString("details");           
		   }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   dba.closeConnection();
	   return detail;
	 }
	 
}
