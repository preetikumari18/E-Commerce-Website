package Controller;

import shoppingcart.beans.Cart;
import shoppingcart.beans.CartItemBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;


/*
 *  CartController class is taken from http://www.tech-freaks.com/jsp-servlets/shopping-cart/all-pages.html
 *  I made some changes to all methods
 *  edited by Sofana
 */
/**
 * Servlet implementation class CartController
 */

public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<CartItemBean> cdShoppingList = new ArrayList<CartItemBean>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//take user action to either add to cart, update or delete
		String strAction = request.getParameter("action");
		boolean check = false;
	  
		  if(strAction!=null && !strAction.equals("")) {			  
		   if(strAction.equalsIgnoreCase("add")) {
			   HttpSession session = request.getSession();
			   Object objUser = session.getAttribute("User");
			   if(objUser!=null) {
					  User user = (User) objUser ;
					      check = addToCart(request,user);
					  }else{
						 check = addToCartNewUser(request);
					  }
		    }else if (strAction.equalsIgnoreCase("+")) {
		    	
		    	         check = updateCart(request);
		    	         if(check)
		    	         request.setAttribute("update", "Item Update Successfully");
		    	         else
		    	         request.setAttribute("error", "Please try again!!");	 
			}else if (strAction.equalsIgnoreCase("-")) {
   	                     check = deleteCart(request); 
   	                     if(check)
 		    	         request.setAttribute("update", "Item Deleted Successfully");
 		    	         else
 		    	         request.setAttribute("error", "Please try again!!");
			}
		  }	
		  if(check){  
			response.sendRedirect("Index.jsp");
		   }else{
					//need to add
		   }
	}

	
		 // Add products to shopping cart as well as database
	 protected boolean addToCart(HttpServletRequest request, User user) {
		  
		  String strCdid = request.getParameter("modelNo");
		  String strDescription = request.getParameter("description");
		  String strPrice = request.getParameter("price");
		  String strQuantity = request.getParameter("quantity");
		  
		  
		  Cart cart = new Cart();
		   
		  boolean check = cart.pickUp(strCdid, strDescription, strPrice, strQuantity, user );
		  
		  
	     return check;	
		  }
	 
	 protected boolean addToCartNewUser(HttpServletRequest request) {
		 
		  String strCdid = request.getParameter("modelNo");
		  String strDescription = request.getParameter("description");
		  String strPrice = request.getParameter("price");
		  String strQuantity = request.getParameter("quantity");
		  
		  System.out.println("--------------");
		  
		  
		  Cart cart = new Cart();
		  
		  boolean check = cart.pickUpNewUser(strCdid, strDescription, strPrice, strQuantity);
		  
		  
		 return check;	
		  }


	 protected boolean updateCart(HttpServletRequest request){
		  
		 Cart cart = new Cart();
		 String strEmail = "";
		 HttpSession session = request.getSession();
		   Object objUser = session.getAttribute("User");
		   if(objUser!=null) {
			  User user = (User) objUser ;
			  strEmail = user.getEmail();
		   }else{
			   strEmail = "new";
		   }
		 
		  String strModelNo = request.getParameter("modelNo");
		 // String strQuantity = request.getParameter("quantity");
		  boolean check = cart.updateQuantity(strModelNo, strEmail);
		  
		return check;
		  
    }
	 protected boolean deleteCart(HttpServletRequest request){
		  
		 Cart cart = new Cart();
		 String strEmail = "";
		 HttpSession session = request.getSession();
		   Object objUser = session.getAttribute("User");
		   if(objUser!=null) {
			  User user = (User) objUser ;
			  strEmail = user.getEmail();
		   }else{
			   strEmail = "new";
		   }
		 
		  String strModelNo = request.getParameter("modelNo");
		 // String strQuantity = request.getParameter("quantity");
		  boolean check = cart.deleteQuantity(strModelNo, strEmail);
		  
		return check;
		  
    }
	 
}