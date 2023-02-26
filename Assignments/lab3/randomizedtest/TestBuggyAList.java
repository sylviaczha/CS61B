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
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> c = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                c.addLast(randVal);
                b.addLast(randVal);
            }else if  (operationNumber == 1){
                // size
                assertEquals(c.size(), b.size());
            } else if (operationNumber == 2) {
                //getLast
                if ((c.size() > 0) && (b.size() > 0)) {
                    assertEquals(c.getLast(), b.getLast());
                }
            } else if (operationNumber == 3) {
                //removeLast
                if ((c.size() > 0) && (b.size() > 0)) {
                    assertEquals(c.removeLast(), b.removeLast());
                }
            }
        }
    }
}
