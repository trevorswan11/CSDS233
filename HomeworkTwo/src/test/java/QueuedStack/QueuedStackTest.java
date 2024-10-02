package QueuedStack;

import java.util.EmptyStackException;
import java.util.LinkedList;
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
        // Establish the test results and additions
        String[] results = {
            "zero",
            "one, zero",
            "two, one, zero",
            "three, two, one, zero"
        };

        String[] additions = {
            "zero",
            "one",
            "two",
            "three"
        };
        
        // Create a queued stack out of a linkedlist storing strings 
        QueuedStack<String> qsString = new QueuedStack<>(new LinkedList<>());
        
        // Push the elements in additions and check the content against the results
        for (int i = 0; i < results.length; i++) {
            assertEquals(additions[i], qsString.push(additions[i]));
            assertEquals(results[i], qsString.toString()); // Note: this also tests toString
        }
    }

    // Test the pop and peek methods
    @Test
    public void qsPeekPopTest() {
        // Create a queued stack out of a linkedlist storing chars 
        QueuedStack<Character> qsChar = new QueuedStack<>(new LinkedList<>());

        // Create an array containing all of the characters in the alphabet
        char[] alphabet = new char[26];

        // Add all of the characters to both the stack and the array
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            alphabet[alphabet.length - (i + 1)] = c; // FILO behavior will be checked
            qsChar.push(c);
        }
        
        // peak then pop as peeking should have no affect on the next value
        for (char c : alphabet) {
            assertEquals(c, qsChar.peek().charValue());
            assertEquals(c, qsChar.pop().charValue());
        }
    }

    // Try to pop and peek a QueuedStack that is empty
    @Test
    public void qsExceptionHandling() {
        // Create a queued stack out of a linkedlist storing bools
        QueuedStack<Boolean> qsBool = new QueuedStack<>(new LinkedList<>());
        
        // try to peek then pop to throw two EmptyStackExceptions
        try {
            qsBool.peek();
        } catch (EmptyStackException e) {
            assertTrue(true);
            try {
                qsBool.pop();
            } catch (EmptyStackException f) {
                assertTrue(true);
                return;
            }
        }

        // If the try-catch didn't return, then the exception was not thrown
        fail("EmptyStackException was not thrown :(");
    }

    // Test the empty method
    @Test
    public void qsEmptyTest() {
        // Create a queued stack out of a linkedlist storing ints 
        QueuedStack<Integer> qsInteger = new QueuedStack<>(new LinkedList<>());
        
        // Test after creation, after pushing, peeking, and popping
        assertTrue(qsInteger.empty());
        qsInteger.push(2);
        assertFalse(qsInteger.empty());
        qsInteger.peek();
        assertFalse(qsInteger.empty());
        qsInteger.pop();
        assertTrue(qsInteger.empty());
    }

    // Test the isValid leetcode question which commonly uses a stack
    @Test
    public void isValidTest() {
        // 50 generated test cases
        String[][] testCases = {
                { "()", "true" },
                { "()[]{}", "true" },
                { "(]", "false" },
                { "([)]", "false" },
                { "{[]}", "true" },
                { "", "true" },
                { "(((((((", "false" },
                { "}}}}}}", "false" },
                { "{[()()]}", "true" },
                { "{[()()]}]", "false" },
                { "()", "true" },
                { "()[]{}", "true" },
                { "(]", "false" },
                { "([)]", "false" },
                { "{[]}", "true" },
                { "", "true" },
                { "(((((((", "false" },
                { "}}}}}}", "false" },
                { "{[()()]}", "true" },
                { "{[()()]}]", "false" },
                { "{", "false" },
                { "}", "false" },
                { "[({})]", "true" },
                { "[(])", "false" },
                { "[[[[[[[[]]]]]]]]", "true" },
                { "[({(())})]", "true" },
                { "[{)]}", "false" },
                { "((", "false" },
                { "))", "false" },
                { "({[]})", "true" },
                { "([{})]", "false" },
                { "(()", "false" },
                { "())", "false" },
                { "[]", "true" },
                { "{}", "true" },
                { "([{}])", "true" },
                { "[{]}", "false" },
                { "({{{{{}}}}})", "true" },
                { "((((()))))", "true" },
                { "([]{})", "true" },
                { "()(()", "false" },
                { "({[}", "false" },
                { "{[]((){})}", "true" },
                { "{[", "false" },
                { "]}", "false" },
                { "{[[(({{}}))]]}", "true" },
                { "[[]]({})", "true" },
                { "[{([])}]", "true" },
                { "[{()]", "false" },
                { "{{{{{{{{{{{{}}}}}}}}}}}}", "true" },
                { "({[({[({[({[]})]})]})]})", "true" },
                { "{[}]", "false" },
                { "((()()))", "true" },
                { "({{{{{}}}}})()", "true" },
                { "(()[)", "false" },
                { "([])", "true" },
                { "{{}[]", "false" },
                { "{([])}", "true" },
                { "(([[{{}}]]))", "true" }
        };
        
        // check the method against the expected results
        for (String[] testCase :  testCases) {
            assertEquals(new Boolean(testCase[1]), isValid(testCase[0]));
        }
    }

    /**
     * My solution to the isValid leetcode question using the Queued stack instead
     * of the usual stack. While this is a more involved test, it makes use of every
     * required method to ensure that the correct behavior is observed with my
     * implementation.
     * 
     * @param s the string to check, contains only parentheses
     * @return true if the parentheses are properly nested and closed
     * @see https://leetcode.com/problems/valid-parentheses/description/
     */
    public static boolean isValid(String s) {
        QueuedStack<Character> paren = new QueuedStack<>(new LinkedList<>());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                paren.push(s.charAt(i));
            } else if (s.charAt(i) == ')' && !paren.empty() && paren.peek() == '(') {
                paren.pop();
            } else if (s.charAt(i) == '}' && !paren.empty() && paren.peek() == '{') {
                paren.pop();
            } else if (s.charAt(i) == ']' && !paren.empty() && paren.peek() == '[') {
                paren.pop();
            } else {
                return false;
            }
        }
        return paren.empty();
    }
}
