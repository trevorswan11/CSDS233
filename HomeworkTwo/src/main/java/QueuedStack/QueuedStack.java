package QueuedStack;

import java.util.Queue;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * A class to mimic the behavior of a stack using a single queue.
 * FILO behavior mimicked (stack) using a queue (FIFO)
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class QueuedStack<T> {
    /**
     * The backbone of the class which behaves like a stack.
     * 
     * @apiNote There is no way to access this field directly
     */
    Queue<T> queue;

    /**
     * Creates a QueuedStack with a single Queue object
     * 
     * @param queue the queue to use for the stack
     */
    public QueuedStack(Queue<T> queue) {
        this.queue = queue;
    }

    /**
     * Pushes an element onto the stack
     * 
     * @param element the element to push
     * @return the element that was pushed
     */
    public T push(T element) {
        // Add the element to the queue
        queue.add(element);

        // Rotate the queue to mimic FILO behavior
        for (int i = 1; i < queue.size(); i++) {
            // For queue length, move first element to end of list
            queue.add(queue.remove());
        }
        return element;
    }

    /**
     * Removes the element at the top of the stack
     * 
     * @return the popped element
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        return queue.remove();
    }

    /**
     * Checks the element at the top of the stack
     * 
     * @return the top element
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (this.empty()) {
            throw new EmptyStackException();
        }
        return queue.peek();
    }

    /**
     * Checks if a QueuedStack is empty
     * 
     * @return true if there are no elements
     */
    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * Creates a string representation of the queue
     * 
     * @return the formatted string
     */
    @Override
    public String toString() {
        // Nothing to loop through if there are no elements
        if (queue.isEmpty()) {
            return null;
        }
        
        // Create a string builder and append each element
        StringBuilder result = new StringBuilder();
        for (T element : this.queue) {
            result.append(element.toString());
            result.append(", ");
        }

        // Remove the last two characters to improve format
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }

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
    public static void main(String[] args) {
        String[][] testCases = {
            {"()", "true"},
            {"()[]{}", "true"},
            {"(]", "false"},
            {"([)]", "false"},
            {"{[]}", "true"},
            {"", "true"},  // Empty string is considered valid
            {"(((((((", "false"},  // Only opening brackets
            {"}}}}}}", "false"},   // Only closing brackets
            {"{[()()]}", "true"},  // Nested valid parentheses
            {"{[()()]}]", "false"},  // Mismatched closing bracket
            {"()", "true"},
            {"()[]{}", "true"},
            {"(]", "false"},
            {"([)]", "false"},
            {"{[]}", "true"},
            {"", "true"}, // Empty string is valid
            {"(((((((", "false"}, // Only opening brackets
            {"}}}}}}", "false"}, // Only closing brackets
            {"{[()()]}", "true"}, // Nested valid parentheses
            {"{[()()]}]", "false"}, // Mismatched closing bracket
            {"{", "false"}, // Single open bracket
            {"}", "false"}, // Single close bracket
            {"[({})]", "true"}, // All types of matching brackets
            {"[(])", "false"}, // Misordered brackets
            {"[[[[[[[[]]]]]]]]", "true"}, // Deeply nested brackets
            {"[({(())})]", "true"}, // Multiple levels of valid nesting
            {"[{)]}", "false"}, // Closing bracket out of order
            {"((", "false"}, // Unmatched open parentheses
            {"))", "false"}, // Unmatched close parentheses
            {"({[]})", "true"}, // Correctly nested, multiple types
            {"([{})]", "false"}, // Incorrect closing bracket for one type
            {"(()", "false"}, // Missing closing parenthesis
            {"())", "false"}, // Extra closing parenthesis
            {"[]", "true"}, // Simple pair of square brackets
            {"{}", "true"}, // Simple pair of curly brackets
            {"([{}])", "true"}, // Nested, all types valid
            {"[{]}", "false"}, // Incorrect closing bracket
            {"({{{{{}}}}})", "true"}, // Deeply nested curly brackets
            {"((((()))))", "true"}, // Long balanced parentheses
            {"([]{})", "true"}, // Interleaved but balanced brackets
            {"()(()", "false"}, // Unbalanced parentheses
            {"({[}", "false"}, // Incomplete and misordered
            {"{[]((){})}", "true"}, // Complex nested valid case
            {"{[", "false"}, // Incomplete opening brackets
            {"]}", "false"}, // Incomplete closing brackets
            {"{[[(({{}}))]]}", "true"}, // Multiple types, nested deeply
            {"[[]]({})", "true"}, // Mixed types, valid
            {"[{([])}]", "true"}, // Nested with correct ordering
            {"[{()]", "false"}, // One extra closing bracket
            {"{{{{{{{{{{{{}}}}}}}}}}}}", "true"}, // Large number of nested curly brackets
            {"({[({[({[({[]})]})]})]})", "true"}, // Extremely deeply nested brackets
            {"{[}]", "false"}, // Misplaced closing curly bracket
            {"((()()))", "true"}, // Balanced, multiple levels
            {"({{{{{}}}}})()", "true"}, // Valid with extra parentheses outside
            {"(()[)", "false"}, // Misordered closing bracket
            {"([])", "true"}, // Simple nested valid case
            {"{{}[]", "false"}, // Missing closing square bracket
            {"{([])}", "true"}, // Balanced, simple nesting
            {"(([[{{}}]]))", "true"} // Nested multiple levels with all bracket types
        };
        String result;
        boolean winner = true;
        for (String[] testCase : testCases) {
            System.out.println("Input: " + testCase[0] + " Result: " + (result = new Boolean(testCase[1]).equals(isValid(testCase[0])) ? "correct" : "failure"));
            winner = result.equals("correct");
        }
        System.out.println(winner);
    }
}
