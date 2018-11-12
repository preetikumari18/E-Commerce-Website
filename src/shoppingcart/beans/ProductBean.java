package shoppingcart.beans;

import java.util.ArrayList;

public class ProductBean {
	ArrayList<ProductBean> productList = new ArrayList<>();
	public String cdid;
    public String title;
    public String price;
    public String details;
	
	public ProductBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCdid() {
        return cdid;
    }
    public void setCdid(String cdid) {
        this.cdid = cdid;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
   
}
