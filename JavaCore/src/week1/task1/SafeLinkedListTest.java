package week1.task1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SafeLinkedListTest {

  private SafeLinkedList<Integer> list;

  @BeforeEach
  void setUp() {
    list = new SafeLinkedList<>();
  }

  @Test
  @DisplayName("The size of an empty list must be 0")
  void testEmptyListSize() {
    assertEquals(0, list.size());
  }

  @Test
  @DisplayName("Getting the first element from an empty list should throw an exception")
  void testGetFromEmptyList() {
    assertThrows(NoSuchElementException.class, () -> list.getFirst());
    assertThrows(NoSuchElementException.class, () -> list.getLast());
  }

  @Test
  @DisplayName("Removing from an empty list should throw an exception")
  void testRemoveFromEmptyList() {
    assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    assertThrows(NoSuchElementException.class, () -> list.removeLast());
  }

  @Test
  @DisplayName("Adding to the beginning should increase the size")
  void testAddFirstIncreasesSize() {
    list.addFirst(10);
    assertEquals(1, list.size());
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3, 4, 5})
  @DisplayName("Adding elements multiple times")
  void testMultipleAdditions(int count) {
    for (int i = 0; i < count; i++) {
      list.addLast(i);
    }
    assertEquals(count, list.size());
  }

  @Test
  @DisplayName("Adding by index")
  void testAddAtIndexMiddle() {
    list.addLast(1);
    list.addLast(3);
    list.add(1, 2);
    assertEquals(3, list.size());
    assertEquals(Integer.valueOf(1), list.get(0));
    assertEquals(Integer.valueOf(2), list.get(1));
    assertEquals(Integer.valueOf(3), list.get(2));
  }

  @Test
  @DisplayName("Adding with an invalid index should throw an exception")
  void testAddAtInvalidIndex() {
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));

    list.addFirst(7);
    assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 10));
  }

  @Nested
  @DisplayName("Element Getting Tests")
  class GetTests {

    @BeforeEach
    void setUp() {
      list.addLast(1);
      list.addLast(2);
      list.addLast(3);
    }

    @Test
    @DisplayName("Getting the first element")
    void testGetFirst() {
      assertEquals(Integer.valueOf(1), list.getFirst());
    }

    @Test
    @DisplayName("Getting the last element")
    void testGetLast() {
      assertEquals(Integer.valueOf(3), list.getLast());
    }

    @ParameterizedTest
    @CsvSource({"0, 1", "1, 2", "2, 3"})
    @DisplayName("Getting an element by index")
    void testGetByIndex(int index, Integer expected) {
      assertEquals(expected, list.get(index));
    }

    @Test
    @DisplayName("Getting by invalid index should throw an exception")
    void testGetInvalidIndex() {
      assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
      assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }
  }

  @Nested
  @DisplayName("Element Removal Tests")
  class RemoveTests {

    @BeforeEach
    void setUp() {
      list.addLast(1);
      list.addLast(2);
      list.addLast(3);
      list.addLast(4);
    }

    @Test
    @DisplayName("Removing the first element")
    void testRemoveFirst() {
      assertEquals(Integer.valueOf(1), list.removeFirst());
      assertEquals(3, list.size());
      assertEquals(Integer.valueOf(2), list.getFirst());
    }

    @Test
    @DisplayName("Removing the last element")
    void testRemoveLast() {
      assertEquals(Integer.valueOf(4), list.removeLast());
      assertEquals(3, list.size());
      assertEquals(Integer.valueOf(3), list.getLast());
    }

    @Test
    @DisplayName("Delete by index")
    void testRemoveAtIndex() {
      assertEquals(Integer.valueOf(2), list.remove(1));
      assertEquals(3, list.size());
      assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    @DisplayName("Deleting by invalid index should throw an exception")
    void testRemoveInvalidIndex() {
      assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
      assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
    }
  }
}
