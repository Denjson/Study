package week1.task2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import week1.task2.res.*;

class Order {
    private String orderId;
    private LocalDateTime orderDate;
    private Customer customer;
	private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    
    public Order(String orderId, ArrayList<People> all) {

		this.orderId = orderId;
		this.orderDate = Generators.timeGenerator();
		this.customer = new Customer(all);
		Random random = new Random();
	    int goodsQ = 1 + random.nextInt(7);				// quantity of goods in one order
     	for (int i = 0; i<goodsQ; i++) {				// filling collection with goods
			OrderItem item = new OrderItem();
			this.items.add(item);
//			System.out.println("Item no " + i);
		}

		int statusN = random.nextInt(5); 				// random number for status of order
		this.status = OrderStatus.getName(statusN);
	}
    
    
    @Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", customer=" + customer + ", items=" + items
				+ ", status=" + status + "]";
	}
    
	public String getCity() {
		String cityName = this.customer.getCity();
		return cityName;
	}
	
	public Double getIncomeOrder() {
		double income = 0;
		if (status == OrderStatus.DELIVERED) {							// revenue is valid only if goods are delivered
			for (OrderItem item : items) {
				income = income + item.getPrice()*item.getQuantity();
			}
		}
		return income;
	}
	
	public Integer getOrdersQ() {									// counting Orders quantity
		int income = 0;
		if (status == OrderStatus.DELIVERED) {							
			income = items.size();
			}
		return income;
	}
	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}   
    
    
}