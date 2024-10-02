package StackifiedQueue;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Stack;

/**
 * A class to test a mimicked queue using stacks
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class StackifiedQueueTest {
    // Test the add method
    @Test
    public void qsPushTest() {
        assertNull(null);
    }

    public static void main(String[] args) {
        StackifiedQueue<Integer> test = new StackifiedQueue<>(new Stack<>(), new Stack<>());
        for (int i = 0; i < 11; i++) {
            test.add(i);
            System.out.println(test);
        }
        for (int i = 0; i < 12; i++) {
            System.out.println(test);
            System.out.println(test.peek());
            System.out.println(test.poll());
            System.out.println(test);
            System.out.print(i == 11 ? "" : "\n");
        }
    }
}
