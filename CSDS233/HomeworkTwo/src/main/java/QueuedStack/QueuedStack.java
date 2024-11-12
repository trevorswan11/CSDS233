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
    private Queue<T> queue;

    /**
     * Creates a QueuedStack with a single Queue object
     * 
     * @param queue the queue to use for the stack
     */
    public QueuedStack() {
        this.queue = new LinkedList<T>();
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
}
