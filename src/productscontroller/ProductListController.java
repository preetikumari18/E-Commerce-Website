package productscontroller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import shoppingcart.beans.ProductBean;
import shoppingcart.beans.StoreProducts;

/**
 * Servlet implementation class ProductListController
 */
//@WebServlet("/ProductListController")
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StoreProducts allCategory = new StoreProducts();
		ArrayList<String> category = allCategory.getCategoryList();
		response.setContentType("application/json");
		new Gson().toJson(category, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strAction = request.getParameter("drop_down");
		
		if(strAction!=null && !strAction.equals("")) {
			List<ProductBean> productList = new ArrayList<ProductBean>();
			
				productList = getprolist(strAction);
				request.setAttribute("productList", productList);
				request.getRequestDispatcher("ProductList.jsp").forward(request, response);
			
			
	    }
				
	}	
	
	//get ProductList according to category
	public List<ProductBean> getprolist(String categoryid){
		 
		  StoreProducts specificCategory = new StoreProducts();
		  ResultSet rs  = specificCategory.getProductList (categoryid);
		  List<ProductBean> productList = new ArrayList<ProductBean>();
		 
		  try {
			while (rs.next()) {
				
				ProductBean pro = new ProductBean();
				 pro.setCdid(rs.getString("cdid"));
				  pro.setTitle(rs.getString("title"));
				  pro.setPrice(rs.getString("price"));
				  pro.setDetails(rs.getString("details"));
				  productList.add(pro);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  
	   return productList;
		
	}

}



