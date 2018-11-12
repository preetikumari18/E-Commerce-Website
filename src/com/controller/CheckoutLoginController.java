package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.model.UserLogin;

public class CheckoutLoginController extends HttpServlet{
	
private static final long serialVersionUID = 1L;
	
	String email = null;
	String password = null;
	UserLogin userLogin = new UserLogin();
	User user = new User();
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

   protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		user = userLogin.login( email, password);
		
		response.setContentType("text/html");  
		HttpSession session = request.getSession();
	    		 
		if(!(user==null)){
			session.setAttribute("User", user); 
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}else{
			session.setAttribute("LoginDenied", "Email and password doesnot match"); 
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}
	


}
