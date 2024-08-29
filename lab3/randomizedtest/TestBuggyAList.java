package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove(){
    BuggyAList<Integer> buggyAList = new BuggyAList<>();
    AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();
    for (int i = 0; i < 3; i++) {
      buggyAList.addLast(i);
      aListNoResizing.addLast(i);
    }
    assertEquals(buggyAList.removeLast(), aListNoResizing.removeLast());
    assertEquals(buggyAList.removeLast(), aListNoResizing.removeLast());
    assertEquals(buggyAList.removeLast(), aListNoResizing.removeLast());
  }
  @Test
  public void randomizedTest(){
    AListNoResizing<Integer> L = new AListNoResizing<>();
    BuggyAList<Integer> B = new BuggyAList<>();

    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 5);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);
        B.addLast(randVal);
      } else if (operationNumber == 1) {
        // size
        int size = L.size();
        assertEquals(size, B.size());
      } else if (operationNumber == 2) {
        // removeLast
        if (L.size() > 0){
          int last = L.removeLast();
          int lastBug = B.removeLast();
          assertEquals(last, lastBug);
        }
      } else if (operationNumber == 3){
        //getLast
        if (L.size() > 0){
          int last = L.getLast();
          int lastBug = B.getLast();
        }
      } else if (operationNumber == 4) {
        if (L.size() > 0){
          int randVal = StdRandom.uniform(0, L.size());
          int item = L.get(randVal);
          int itemBug = B.get(randVal);
          assertEquals(item, itemBug);
        }
      }

    }
  }

}
