package QueuedStack;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * A class to test a mimicked stack using a queue
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class QueuedStackTest {
    // Test the push method
    @Test
    public void qsPushTest() {
        Queue<Integer> queue = new LinkedList<Integer>();
        assertNull(null);
        queue.add(null);
    }

    public static void main(String[] args) {
        QueuedStack<Integer> tester = new QueuedStack<>(new LinkedList<Integer>());
        for (int i = 0; i < 11; i++) {
            tester.push(i);
            System.out.println(tester.toString());
        }
        for (int i = 11; i < 21; i++) {
            tester.push(i);
            System.out.println(tester.peek());
        }
        for (int i = 0; i < 11; i++) {
            tester.pop();
            System.out.println(tester.toString());
        }
    }
}
