package week1.task2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {
	
	@Test
	void testStatistic1() {
		List<Order> allOrders = new ArrayList<>();
		Assertions.assertDoesNotThrow(()->Task2.statistic1(allOrders));
	}
	
	@Test
	void testStatistic2() {
		List<Order> allOrders = new ArrayList<>();
		Assertions.assertDoesNotThrow(()->Task2.statistic2(allOrders));
	}

	@Test
	void testStatistic3() {
		List<Order> allOrders = new ArrayList<>();
		Assertions.assertDoesNotThrow(()->Task2.statistic3(allOrders));
	}
	
	@Test
	void testStatistic4() {
		List<Order> allOrders = new ArrayList<>();
		Assertions.assertDoesNotThrow(()->Task2.statistic4(allOrders));
	}	
	
	@Test
	void testStatistic5() {
		List<Order> allOrders = new ArrayList<>();
		Assertions.assertDoesNotThrow(()->Task2.statistic5(allOrders));
	}
	
	@Test
	void testMain() throws IOException {
		String[] args = {"a","b","c"};
		Assertions.assertDoesNotThrow(()->Task2.main(args));
	}
	
}
