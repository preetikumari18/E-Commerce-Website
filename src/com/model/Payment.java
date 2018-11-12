package com.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.jcg.jdbc.connection.pooling.DBAgent;

public class Payment {

	public void updateStatus(HttpServletRequest request){
		HttpSession session = request.getSession();
		   Object objUser = session.getAttribute("User");
		   DBAgent dba = new DBAgent("/SQL/DB.xml","/SQL/sqlquery2.xml");
		   
		   if(objUser!=null) {
			   User user = (User) objUser ;
			   String email = user.getEmail();
			   String userPO[]={"ORDERED", email, "PROCESSED"};
			   dba.executeSQL(21, userPO);
			   dba.closeConnection();
		   }
	}
	
}
