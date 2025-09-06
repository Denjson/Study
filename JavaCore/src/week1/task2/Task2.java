package week1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import week1.task2.res.Generators;
import week1.task2.res.People;

public class Task2 {

	public static void main(String[] args) throws IOException {
		ArrayList<People> all = Generators.generate();							// external data generator of names, cities, etc.
		System.out.println("External data received successfully.");	 
		System.out.println("List of orders received from on-line shop:");
		
		List<Order> allOrders = new ArrayList<>();								// collection for all orders
		int ordersQ = 1000;														// required order quantity (for test reason)
		
		for (int i = 0; i<ordersQ; i++) {										// adding orders into the collection
			String id = "RD"+1000+i;											// creating id
			Order newOrder = new Order(id, all);								// creating order with data obtained from on-line shop
			allOrders.add(newOrder);
			System.out.println(newOrder.toString());
		}
		
		System.out.println("\nStatistics\n");
				 
			statistic1(allOrders);												// List of unique cities where orders came from
			statistic2(allOrders);												// Total income for all completed orders
			statistic3(allOrders);												// The most popular product by sales
			statistic4(allOrders);												// Average check for successfully delivered orders 
			statistic5(allOrders);												// Customers who have more than 5 orders

	}
	
	private static void statistic1 (List<Order> allOrders) {							// List of unique cities where orders came from
		System.out.println("List of unique cities where orders came from:");
		allOrders.stream().map(Order::getCity).distinct().sorted().forEach(System.out::println);
	}
	
	private static void statistic2(List<Order> allOrders) {								// Total income for all completed orders
		 double totalRevenue = allOrders.stream()
				 .filter(st -> st.getStatus().equals(OrderStatus.DELIVERED))			// selecting only delivered orders
				 .mapToDouble(Order::getIncomeOrder)
				 .sum();
		 System.out.println("\nTotal income of delivered orders: " + Math.round(100*totalRevenue)/100.00 + " $");
		
	}

	private static void statistic3(List<Order> allOrders) {								// The most popular product by sales
		 System.out.println("\nThe most popular products by sales are (Descending order):");
		 Stream<OrderItem> flattenedStream = allOrders.stream()
				 .map(Order::getItems)
				 .flatMap(Collection::stream);											// getting all orders in one stream
		 Map<String, Integer> bestProduct = flattenedStream
				 .collect(
		       		Collectors.groupingBy(
		       				OrderItem::getProductName,
		                  	Collectors.summingInt(OrderItem::getQuantity)
		                )
		       );;																		// grouping by product name and summing quantity	
//		       System.out.println(bestProduct);
		 
		        Map<String, Integer> sortedMapDesc = bestProduct.entrySet()
		                .stream()
		                .sorted(Entry.<String, Integer>comparingByValue().reversed())
		                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
		                        (e1, e2) -> e1, LinkedHashMap::new));					// just sorting in descending order
		        System.out.println(sortedMapDesc);
		        
		        Optional<Map.Entry<String, Integer>> first = bestProduct.entrySet()
		                .stream()
		                .sorted(Entry.<String, Integer>comparingByValue().reversed()).findFirst();
		        System.out.println("The most popular product by sales is:\n" + first.get());
		
	}
	
	private static void statistic4(List<Order> allOrders) {								// Average check for successfully delivered orders 
		 double totalRevenue = allOrders.stream()
				 .filter(st -> st.getStatus().equals(OrderStatus.DELIVERED))			// selecting only delivered orders
				 .mapToDouble(Order::getIncomeOrder)
				 .sum();
		 long happyCustomers = allOrders.stream()
				 .filter(st -> st.getStatus().equals(OrderStatus.DELIVERED))			// selecting only delivered orders
				 .count()																// counting delivered cases
				 ;
		 System.out.println("\nAverage check for successfully delivered orders: " + Math.round(100*totalRevenue/happyCustomers)/100.00 + " $");
		
	}
	
	private static void statistic5(List<Order> allOrders) {								// Customers who have more than 5 orders
		 System.out.println("\nCustomers who have more than 5 orders:");
		 
		 allOrders.stream()
		 .filter(st -> st.getStatus().equals(OrderStatus.DELIVERED))
		 .filter(st -> st.getItems().size()>5)
		 .forEach(System.out::println);
		 
		 System.out.println("\nCustomers who have more than 5 orders (only names):");
		 allOrders.stream()
		 .filter(st -> st.getStatus().equals(OrderStatus.DELIVERED))
		 .filter(st -> st.getItems().size()>5)
		 .map(Order::getCustomer)
		 .map(Customer::getName)
		 .forEach(System.out::println);
	}
	
}