package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import shoppingcart.beans.CartItemBean;

import com.bean.User;
import com.jcg.jdbc.connection.pooling.DBAgent;

public class ShoppingCartDetails {
	
	
	double dblUnitCost = 0.0;
	double total = 0.0;
	int iQuantity = 0;
	
	public List<CartItemBean> getProductList(HttpServletRequest request){
		
		List<CartItemBean> cartList = new ArrayList<CartItemBean>();
		
		 HttpSession session = request.getSession();
		   Object objUser = session.getAttribute("User");
		   if(objUser!=null) {
				  User user = (User) objUser ;
				  cartList = getProduct(user);
				  System.out.println("user");
				  }else{
				  cartList = getProductNewUser();
				  System.out.println("new user");
				  }
		return cartList;
		}
	
    public List<CartItemBean> getProduct(User user){
    	
    	String strEmail = user.getEmail();
    	String poID = null;
    	 List<CartItemBean> shoppingtList = new ArrayList<CartItemBean>();
 		
    	
    	DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
    	
    	String checkingId2[]={"PROCESSED",strEmail};
	    ResultSet resultS = dba.getResults(13, checkingId2);// get purchase order id from PO table 
	    try {
			if(resultS.next()) {
			poID = resultS.getString("id");
			}else{
				return shoppingtList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dba.closeConnection();
	    
		String checkingPOItem[]={poID};
		ResultSet rs = dba.getResults(18, checkingPOItem);
			
		try {
			while(rs.next()){
				CartItemBean cartItem = new CartItemBean();
				cartItem.setPartNumber(rs.getString("cdid"));
				cartItem.setModelDescription(rs.getString("title"));
				dblUnitCost = rs.getInt("price");
				cartItem.setUnitCost(dblUnitCost);
				iQuantity = rs.getInt("quantity");
				cartItem.setQuantity(iQuantity);
				double price = totalCost(dblUnitCost,iQuantity);
				cartItem.setTotalCost(price);
				shoppingtList.add(cartItem);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		dba.closeConnection();
		
		return shoppingtList;
    }
    

	public List<CartItemBean> getProductNewUser(){
		
		String strEmail = "new";
    	String poID = null;
    	 List<CartItemBean> shoppingtList = new ArrayList<CartItemBean>();
 		
    	
    	DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
    	
    	String checkingId2[]={"PROCESSED",strEmail};
	    ResultSet resultS = dba.getResults(13, checkingId2);// get purchase order id from PO table 
	    try {
			if(resultS.next()) {
			poID = Integer.toString(resultS.getInt("id"));
			}else{
				return shoppingtList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dba.closeConnection();
	    
		System.out.println("======="+poID);
		
		String checkingPOItem[]={poID};
		ResultSet rs = dba.getResults(18, checkingPOItem); //get po item from poitem table
			
		
		try {
			while(rs.next()){
				CartItemBean cartItem = new CartItemBean();
				cartItem.setPartNumber(rs.getString("cdid"));
				cartItem.setModelDescription(rs.getString("title"));
				dblUnitCost = rs.getInt("price");
				cartItem.setUnitCost(dblUnitCost);
				iQuantity = rs.getInt("quantity");
				cartItem.setQuantity(iQuantity);
				double price = totalCost(dblUnitCost,iQuantity);
				cartItem.setTotalCost(price);
				shoppingtList.add(cartItem);
			  }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dba.closeConnection();
		System.out.println("shoppingtList"+shoppingtList);
		
		return shoppingtList;
    }
	
	public double totalCost(double strUnitCost, double strQuantity){
		
		return (strUnitCost*strQuantity);
		
	}
	
	public List<Double> gettotal(List<CartItemBean> listCart){
		
		double sum = 0;
		List<Double> total = new ArrayList<Double>();
		
		for(CartItemBean obj: listCart){
			sum = sum + obj.getTotalCost();
		}
		
		double tax = sum*0.13;
		String strtax = new DecimalFormat("##.##").format(tax);
		double newTax = Double.parseDouble(strtax);
		
		double totalpayment = sum + newTax;
		
		total.add(sum);
		total.add(newTax);
		total.add(totalpayment);
		
		return total;
		
	}
	
}
