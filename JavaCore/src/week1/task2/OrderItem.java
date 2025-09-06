package week1.task2;

import java.util.Random;

class OrderItem {
    private String productName;
    private int quantity;
	private double price;
    private Category category;
    
    @Override
	public String toString() {
		return "OrderItem [productName=" + productName + ", quantity=" + quantity + ", price=" + price + ", category="
				+ category + "]";
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public OrderItem() {
		Random random = new Random();
	    int goodsN = 1 + random.nextInt(100); 	// random number between 0 and 99
		this.productName = "Product_No" + goodsN;		// instead of product name
		this.quantity = random.nextInt(10);
		this.price = random.nextDouble()*100;
        int categ = random.nextInt(6); 					// random number between 0 and 5
		this.category = Category.getName(categ);		// getting random category of goods
	}
}