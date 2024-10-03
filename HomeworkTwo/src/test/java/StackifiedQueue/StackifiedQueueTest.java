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
    // Test the add method and isEmpty
    @Test
    public void sqAddTest() {
        StackifiedQueue<Integer> sqInteger = new StackifiedQueue<>(new Stack<>(), new Stack<>());
        StringBuilder expected = new StringBuilder();
        assertTrue(sqInteger.isEmpty());
        
        // Add elements and make sure true is returned and fifo is fulfilled
        for (int i = 0; i < 11; i++) {
            assertTrue(sqInteger.add(i));
            expected.append(i == 0 ? "" : ", ").append(i);
            assertEquals(expected.toString(), sqInteger.toString());
            assertFalse(sqInteger.isEmpty());
        }
    }

    // Test the peek and poll methods
    @Test
    public void sqPeekPollTest() {
        StackifiedQueue<Character> sqChar = new StackifiedQueue<>(new Stack<>(), new Stack<>());

        // Create an array containing all of the characters in the alphabet
        char[] alphabet = new char[26];

        // Add all of the characters to both the stack and the array
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            alphabet[i] = c; // FIFO behavior will be checked
            sqChar.add(c);
        }

        // Check the poll method for FIFO behavior
        for (char c : alphabet) {
            assertFalse(sqChar.isEmpty());
            assertEquals(c, sqChar.peek().charValue());
            assertEquals(c, sqChar.poll().charValue());
        }

        // Check that null is returned when empty
        assertTrue(sqChar.isEmpty());
        assertNull(sqChar.peek());
        assertNull(sqChar.poll());
    }

    // Test creating a queue with premade stacks, no assertions just printing
    @Test
    public void sqNonNullTest() {
        Stack<String> stringStack1 = new Stack<>(), stringStack2 = new Stack<>();

        // add some strings to the stacks
        for (int i = 0; i < 11; i++) {
            stringStack1.add((char)('a' + i) + ":" + i);
            stringStack2.add((char)('l' + i) + ":" + (10 + i));
        }
        
        // Create the StackifiedQueue with the inputs
        StackifiedQueue<String> sqString = new StackifiedQueue<String>(stringStack1, stringStack2);

        // Add some numbers to the end of the stack 
        for (int i = 0; i < 11; i++) {
            sqString.add("" + i);
        }
        System.out.println(sqString);

        // Poll the first 21 elements and add them to the expected string
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 22; i++) {
            expected.append(i == 0 ? "" : ", ").append(sqString.poll());
        }

        // Expected should show all of the alpha-numeric keys
        System.out.println(expected);
        System.out.println(sqString);
    }
}
