package week1.task1;

import java.util.LinkedList;

public class SafeLinkedList<T> extends LinkedList<T> {			// safe implementation of LinkedList<> for study purposes, do not throw Exceptions

	private static final long serialVersionUID = 1L;

	@Override
	public int size() {
		int size = super.size();
		if (size == 0)
			System.out.println("The list is empty");
		return super.size();
	}

	@Override
	public void addFirst(T e) {
		super.addFirst(e);
		System.out.println("The current list is " + this.toString());
	}

	@Override
	public void addLast(T e) {
		super.addLast(e);
		System.out.println("The current list is " + this.toString());
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > (this.size() + 1)) {
			System.out.println("An incorrect index was used");
			return;
		}

		super.add(index, element);
	}

	@Override
	public T getFirst() {
		if (this.isEmpty()) {
			System.out.println("The list is empty");
			return null;
		}
		return super.getFirst();
	}

	@Override
	public T getLast() {
		if (this.isEmpty()) {
			System.out.println("The list is empty");
			return null;
		}
		return super.getLast();
	}
	@Override
	public T get(int index) {
		if (this.isEmpty()||index<0||index>=this.size()) {
			System.out.println("An incorrect index was used or the list is empty");
			return null;
		}
		return super.get(index);
	}
	@Override
	public T removeFirst() {
		if (this.isEmpty()) {
			System.out.println("The list is empty");
			return null;
		}
		return super.removeFirst();
	}
	@Override
	public T removeLast() {
		if (this.isEmpty()) {
			System.out.println("The list is empty");
			return null;
		}
		return super.removeLast();
	}
	@Override
	public T remove(int index) {
		if ((this.isEmpty()||index<0||index>=this.size())){
			System.out.println("Unable to remove an element by index or the list is empty");
			return null;
		}
				
		return super.remove(index);
	}

}
