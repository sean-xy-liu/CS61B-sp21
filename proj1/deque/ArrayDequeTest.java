package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

  @Test
  public void initTest(){
    ArrayDeque<String> ad = new ArrayDeque<>();
    for (int i=0; i < 2000; i++){
      ad.addFirst("Get Back.");
    }
    for (int i=0; i < 2000; i++){
      ad.addLast("Let it be");
    }
    ad.show();
    ad.clear();
    ad.show();
    ad.addLast("Once there was a way,");
    ad.show();
  }

  @Test
  public void addTest(){
    ArrayDeque<String> ad = new ArrayDeque<>();
    ad.addFirst("JoJo");
    ad.addLast("Go home!");
    String expect = "JoJo";
    String actual = ad.getFirst();
    assertEquals(expect, actual);
    expect = "Go home!";
    actual = ad.getLast();
    assertEquals(expect, actual);
  }

  @Test
  public void removeTest(){
    ArrayDeque<String> ad = new ArrayDeque<>();
    ad.addFirst("JoJo");
    ad.addLast("Go home!");
    ad.addLast("Your mom is waiting for you.");
    String expect = "Your mom is waiting for you.";
    String actual = ad.getLast();
    assertEquals(expect, actual);
    expect = "JoJo";
    actual = ad.getFirst();
    assertEquals(expect, actual);
    int expectInt = 3;
    int actualInt = ad.size();
    assertEquals(expectInt, actualInt);
    assertEquals(expect, ad.removeFirst());
    expect = ad.getLast();
    assertEquals(expect, ad.removeLast());
    ad.printDeque();
    expectInt = 1;
    actualInt = ad.size();
    assertEquals(expectInt, actualInt);
  }

  @Test
  /** Adds a few things to the list, checking isEmpty() and size() are correct,
   * finally printing the results.
   *
   * && is the "and" operation. */
  public void addIsEmptySizeTest() {

    ArrayDeque<String> lld1 = new ArrayDeque<>();
//        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

    assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
    lld1.addFirst("front");

    // The && operator is the same as "and" in Python.
    // It's a binary operator that returns true if both arguments true, and false otherwise.
    assertEquals(1, lld1.size());
    assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

    lld1.addLast("middle");
    assertEquals(2, lld1.size());

    lld1.addLast("back");
    assertEquals(3, lld1.size());

    System.out.println("Printing out deque: ");
    lld1.printDeque();
  }
  @Test
  /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
  public void addRemoveArrayTest() {

    ArrayDeque<String> lld1 = new ArrayDeque<>();
    // should be empty
    assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

    lld1.addFirst("JoJo");
    // should not be empty
    assertFalse("lld1 should contain 1 item", lld1.isEmpty());

    lld1.removeFirst();
    // should be empty
    assertTrue("lld1 should be empty after removal", lld1.isEmpty());
  }
  @Test
  /* Tests removing from an empty deque */
  public void removeEmptyArrayTest() {

    ArrayDeque<String> lld1 = new ArrayDeque<>();
    lld1.addFirst("JoJo");

    lld1.removeLast();
    lld1.removeFirst();
    lld1.removeLast();
    lld1.removeFirst();

    int size = lld1.size();
    String errorMsg = "  Bad size returned when removing from empty deque.\n";
    errorMsg += "  student size() returned " + size + "\n";
    errorMsg += "  actual size() returned 0\n";

    assertEquals(errorMsg, 0, size);
  }
  @Test
  /* check if null is return when removing from an empty LinkedListDeque. */
  public void emptyNullReturnArrayTest() {

    ArrayDeque<String> lld1 = new ArrayDeque<>();
    boolean passed1 = false;
    boolean passed2 = false;
    assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
    assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

  }

}