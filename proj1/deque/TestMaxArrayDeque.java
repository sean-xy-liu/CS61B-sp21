package deque;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Comparator;

public class TestMaxArrayDeque {
  @Test
  public void testMax(){
    Comparator<Integer> comparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    };
    MaxArrayDeque<Integer> madInt = new MaxArrayDeque<>(comparator);
    madInt.addFirst(3);
    madInt.addFirst(9);
    madInt.addFirst(33);
    madInt.addLast(-3);
    madInt.addLast(0);
    int expect = 33;
    int actual = madInt.max();
    assertEquals(expect,actual);
    Comparator<Integer> comparator1 = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        o1 = o1 % 5;
        o2 = o2 % 5;
        return o1 - o2;
      }
    };
    expect = 9;
    actual = madInt.max(comparator1);
    assertEquals(expect, actual);
    MaxArrayDeque<String> madString = new MaxArrayDeque<>();
    madString.addLast("hello");
    madString.addLast("how are you");
    madString.addFirst("chao");
    Comparator<String> compareLength = new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return o1.length() - o2.length();
      }
    };
    String expectString = "how are you";
    String actualString = madString.max(compareLength);
    assertEquals(expectString, actualString);
  }
}