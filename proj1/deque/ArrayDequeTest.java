package deque;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void initTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        for (int i = 0; i < 2000; i++) {
            ad.addFirst("Get Back.");
        }
        for (int i = 0; i < 2000; i++){
            ad.addLast("Let it be");
        }
        ad.addLast("Once there was a way,");
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
    public void equalTest() {
        ArrayDeque<String> lldStr = new ArrayDeque<>();
        lldStr.addLast("Get Back");
        lldStr.addLast("Let it be.");
        lldStr.addLast("JoJo was a woman");
        ArrayDeque<String> lldStr1 = new ArrayDeque<>();
        lldStr1.addLast("Get Back");
        lldStr1.addLast("Let it be.");
        lldStr1.addLast("JoJo was a woman");
        boolean expect = true;
        boolean actual = lldStr1.equals(lldStr);
        assertEquals(expect, actual);
        actual = lldStr1.equals(lldStr1);
        assertEquals(expect, actual);
        lldStr.addLast("JoJo was a woman");
        actual = lldStr1.equals(lldStr);
        expect = false;
        assertEquals(expect, actual);
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
    @Test
    public void iteratorTest() {
        ArrayDeque<String> ads = new ArrayDeque<>();
        ads.addLast("Penny Lane");
        ads.addFirst("Strawberry Field");
        Iterator<String> iteratorString = ads.iterator();
        while (iteratorString.hasNext()) {
            System.out.println(iteratorString.next());
        }
        LinkedListDeque<String> llds = new LinkedListDeque<>();
        llds.addLast("Penny Lane");
        llds.addFirst("Strawberry Field");
        Iterator<String> iteratorString2 = llds.iterator();
        while (iteratorString2.hasNext()) {
            System.out.println(iteratorString2.next());
        }

    }

}