package week1.task1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SafeLinkedListTest {

	@Test
	void testSize() {
		LinkedList<Integer> template = new LinkedList<>();
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		template.add(34);
		template.add(12);
		template.add(3);

		for (Integer i : template)
			myList.add(i);
		Assertions.assertEquals(template.size(), myList.size());
	}

	@Test
	void testAddFirstT() {
		LinkedList<Integer> template = new LinkedList<>();
		template.add(1);
		template.add(2);
		template.add(3);
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		myList.addFirst(3);
		myList.addFirst(2);
		myList.addFirst(1);
		Assertions.assertEquals(template, myList);
	}

	@Test
	void testAddLastT() {
		LinkedList<Integer> template = new LinkedList<>();
		template.add(1);
		template.add(2);
		template.add(3);
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		myList.addLast(1);
		myList.addLast(2);
		myList.addLast(3);
		Assertions.assertEquals(template, myList);
	}

	@Test
	void testAddIntT() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		myList.add(0,1);
		myList.add(1,5);
		myList.add(2,10);
		Assertions.assertDoesNotThrow(()->myList.add(100,10));

	}

	@Test
	void testGetFirst() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		Assertions.assertDoesNotThrow(()->myList.getFirst());
	}

	@Test
	void testGetLast() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		Assertions.assertDoesNotThrow(()->myList.getLast());
	}

	@Test
	void testGetInt() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		myList.add(0,1);
		myList.add(1,5);
		myList.add(2,10);
		Assertions.assertDoesNotThrow(()->myList.get(100));
	}

	@Test
	void testRemoveFirst() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		Assertions.assertDoesNotThrow(()->myList.removeFirst());
	}

	@Test
	void testRemoveLast() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		Assertions.assertDoesNotThrow(()->myList.removeLast());
	}

	@Test
	void testRemoveInt() {
		SafeLinkedList<Integer> myList = new SafeLinkedList<>();
		myList.add(0,1);
		myList.add(1,5);
		myList.add(2,10);
		Assertions.assertDoesNotThrow(()->myList.remove(100));
	}

}
