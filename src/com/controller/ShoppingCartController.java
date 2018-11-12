package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.beans.CartItemBean;

import com.google.gson.Gson;
import com.model.ShoppingCartDetails;

public class ShoppingCartController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				
		ShoppingCartDetails allProducts = new ShoppingCartDetails();
		List<CartItemBean> shoppingList = new ArrayList<CartItemBean>();
		List<Double> totalpriceList = new ArrayList<Double>();
		
		shoppingList = allProducts.getProductList(req);
		totalpriceList = allProducts.gettotal(shoppingList);
		
		if(shoppingList.size()==0){
			req.setAttribute("shoppingListEmpty", "Your Cart is Empty");
			req.getRequestDispatcher("EmptyShoppingCart.jsp").forward(req, resp);
		}else{
			req.setAttribute("shoppingList", shoppingList);
			req.setAttribute("paymentList", totalpriceList);
			req.getRequestDispatcher("ShoppingCart.jsp").forward(req, resp);
		}
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*ShoppingCartDetails allProducts = new ShoppingCartDetails();
		List<CartItemBean> shoppingList = new ArrayList<CartItemBean>();
		shoppingList = allProducts.getProductList(req);
		
		System.out.println(shoppingList);
		
		
		req.setAttribute("shoppingList", shoppingList);
		req.getRequestDispatcher("MainShoppingCart.jsp").forward(req, resp);*/
		
	}

}
