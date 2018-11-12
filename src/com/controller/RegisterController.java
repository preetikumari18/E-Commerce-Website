package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.model.UserRegister;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	String firstName = null;
	String lastName = null;
	String password1 = null;
	String password2 = null;
	String email = null;
	String phone = null;
	String street = null;
	String country = null;
	String province = null;
	String pincode = null;
	String payment = null;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		password1 = request.getParameter("password1");
		password2 = request.getParameter("password2");
		email = request.getParameter("email");
		phone = request.getParameter("phone");
		street = request.getParameter("street");
		country = request.getParameter("country");
		province = request.getParameter("province");
		pincode = request.getParameter("pincode");
		payment = request.getParameter("payment");
		
		
		if(!password1.equalsIgnoreCase(password2)){
			String message = "Password doesnot match";
			request.setAttribute("registermessage", message);
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}	
				
		UserRegister userRegister = new UserRegister();
		String status = userRegister.register(email);
				
		//HttpSession session = request.getSession();
	    //session.setAttribute("User", user);
	    
		//response.setContentType("text/html");  
		//PrintWriter pw=response.getWriter();  
		  
		if(status.equalsIgnoreCase("success")){
			User accountInfo = new User();
			accountInfo.setFirstname(firstName);
			accountInfo.setLastname(lastName);
			accountInfo.setEmail(email);
			accountInfo.setPassword(password1);
			accountInfo.setPhoneno(phone);
			accountInfo.setStreet(street);
			accountInfo.setProvince(province);
			accountInfo.setCountry(country);
			accountInfo.setPincode(pincode);
			accountInfo.setPayment(payment);
			
			String account = userRegister.createAccount(accountInfo);
			if(account.equalsIgnoreCase("success")){
				
				HttpSession session = request.getSession();
				session.setAttribute("User", accountInfo); 
				request.getRequestDispatcher("Index.jsp").forward(request, response);
			}else{
				String message = "Please try again";
				request.setAttribute("registermessage", message);
				request.getRequestDispatcher("/Register.jsp").forward(request, response);
			}
			
		}else{
			String message = "User already exsits";
			request.setAttribute("registermessage", message);
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		    //response.sendRedirect("register.jsp");
		}
		//pw.close();  
	}

}
