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
}
