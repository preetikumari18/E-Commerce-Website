package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import shoppingcart.beans.Cart;
import shoppingcart.beans.StoreProducts;

import com.bean.User;
import com.model.UserLogin;
import com.model.UserRegister;

public class TestPart1 {
	
	static User accountInfo ;
	
	@BeforeClass
	public static void setUserDetail(){
		accountInfo = new User();
		accountInfo.setFirstname("Alex");
		accountInfo.setLastname("Laud");
		accountInfo.setEmail("al@gmail.com");
		accountInfo.setPassword("12345");
		accountInfo.setPhoneno("613457895");
		accountInfo.setStreet("Byward Market");
		accountInfo.setProvince("Ontario");
		accountInfo.setCountry("Canada");
		accountInfo.setPincode("A5JW58");
		accountInfo.setPayment("Credit");
	}
	
	@Test
	public void testRegister(){
		UserRegister registerCheck = new UserRegister();
		String str = registerCheck.createAccount(accountInfo);
		assertEquals("User is Registered", "success", str);;
		}
	
	@Test
	public void testLogin(){
		UserLogin loginCheck = new UserLogin();
		User obj = loginCheck.login("pk@gmail.com", "12345");
		assertNotNull(obj);
		}
	
	@Test
	public void testProductListCategory(){
		boolean check = false;
		StoreProducts specificCategory = new StoreProducts();
	    ResultSet rs  = specificCategory.getProductList ("ROCK");
	    try {
			while(rs.next()){
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(check);
		}

	@Test
	public void testProductDetails(){
		
		StoreProducts specificDetails = new StoreProducts();
	    String detail  = specificDetails.getProductInfo ("cd012");
	    assertTrue((!detail.equals(null) && !detail.equals("")));
		}
	
	@Test
	public void testAddToCart(){
		Cart cart = new Cart();
		boolean test = cart.pickUp("cd013", "ROYALTY", "16", "1", accountInfo);
		assertTrue(test);
		
		}
	
	@Test
	public void testUpdateCart(){
		Cart cart = new Cart();
		boolean test = cart.updateQuantity("cd013", accountInfo.getEmail());
		assertTrue(test);
		
		}
	
	@Test
	public void testDeleteCart(){
		Cart cart = new Cart();
		boolean test = cart.deleteQuantity("cd001", "rb@uottawa.ca");
		assertFalse(test);
		
		}
	
	
}
