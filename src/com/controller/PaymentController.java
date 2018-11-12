package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Payment;

public class PaymentController  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> cardlist = new ArrayList<String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		
		String cardnumber = req.getParameter("cardnumber");
		cardlist.add(cardnumber);
		boolean check = checkCard(cardlist);
		
		if(check){
			Payment paym = new Payment();
			paym.updateStatus(req);
			req.setAttribute("odermessage", "Order Successfully Completed.");
			req.getRequestDispatcher("OrderSuccess.jsp").forward(req, resp);
		}else{
			cardlist.clear();
			req.setAttribute("odermessage", "Credit Card Authorization Failed");
			req.getRequestDispatcher("OrderSuccess.jsp").forward(req, resp);
		}
		
	}
	
	public boolean checkCard(ArrayList<String> cardlist){
		
		int count = 0;
		 count = cardlist.size();
		
		 if(count>0 && count<5)
		    return true;
		 else
			return false; 
		
	}
	
	

}
