package week1.task1;

public class SafeLinkedList<K> {

  private static class Node<K> {
    K data;
    Node<K> next;
    Node<K> prev;

    Node(K data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  private Node<K> head;
  private Node<K> tail;
  private int size;

  public SafeLinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(K el) {
    Node<K> newNode = new Node<>(el);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    }
    size++;
  }

  public void addLast(K el) {
    Node<K> newNode = new Node<>(el);
    if (tail == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
  }

  public void add(int index, K el) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    if (index == 0) {
      addFirst(el);
    } else if (index == size) {
      addLast(el);
    } else {
      Node<K> current = head;
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      Node<K> newNode = new Node<>(el);
      newNode.prev = current.prev;
      newNode.next = current;
      current.prev.next = newNode;
      current.prev = newNode;
      size++;
    }
  }

  public K getFirst() {
    if (head == null) {
      throw new java.util.NoSuchElementException("List is empty");
    }
    return head.data;
  }

  public K getLast() {
    if (tail == null) {
      throw new java.util.NoSuchElementException("List is empty");
    }
    return tail.data;
  }

  public K get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    Node<K> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  public K removeFirst() {
    if (head == null) {
      throw new java.util.NoSuchElementException("List is empty");
    }
    K data = head.data;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      head = head.next;
      head.prev = null;
    }
    size--;
    return data;
  }

  public K removeLast() {
    if (tail == null) {
      throw new java.util.NoSuchElementException("List is empty");
    }
    K data = tail.data;
    if (head == tail) {
      head = null;
      tail = null;
    } else {
      tail = tail.prev;
      tail.next = null;
    }
    size--;
    return data;
  }

  public K remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    if (index == 0) {
      return removeFirst();
    }
    if (index == size - 1) {
      return removeLast();
    }

    Node<K> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    K data = current.data;
    current.prev.next = current.next;
    current.next.prev = current.prev;
    size--;
    return data;
  }
}
