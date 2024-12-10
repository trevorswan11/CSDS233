package Queues;

import java.util.Stack;

/**
 * A class to mimic the behavior of a queue using a stacks
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class StackifiedQueue<T> {
    /**
     * The stacks which act as the backbone of the queue
     * 
     * @apiNote these stacks cannot be directly accessed or modified
     */
    private Stack<T> inputStack, outputStack;

    /**
     * Creates a StackifiedQueue based on two input stacks
     * 
     * @param inputStack  The stack to use as the input
     * @param outputStack The stack to use as the output
     */
    public StackifiedQueue() {
        this.inputStack = new Stack<T>();
        this.outputStack = new Stack<T>();
    }

    /**
     * Adds an element to the queue
     * 
     * @param element the element of type T to add to the queue
     * @return true if the element was successfully added
     * @apiNote Stacks are 'infinite' in length so true is always returned
     */
    public boolean add(T element) {
        return this.inputStack.add(element);
    }

    /**
     * Removes and returns element at the head of the queue
     * 
     * @return the element at the head of the queue
     */
    public T poll() {
        // Return null if empty according to guidelines
        if (this.isEmpty()) {
            return null;
        }

        // If the output is empty, transfer elements and pop
        if (outputStack.empty()) {
            transfer(this.inputStack, this.outputStack);
        }
        return outputStack.pop();
    }

    /**
     * Checks the element at the head of the queue without removing it
     * 
     * @return the element at the head of the queue
     */
    public T peek() {
        // Return null if empty according to guidelines
        if (this.isEmpty()) {
            return null;
        }

        // If the output is empty, transfer elements and peek
        if (outputStack.empty()) {
            transfer(this.inputStack, this.outputStack);
        }
        return outputStack.peek();
    }

    /**
     * Checks if the queue is empty or not
     * 
     * @return true if there are no elements in the queue
     */
    public boolean isEmpty() {
        return this.inputStack.empty() && this.outputStack.empty();
    }

    /**
     * Converts the queue to a string representation without destroying any data
     * 
     * @return a formatted string representation
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
    
        // Add elements from outputStack (front of the queue, in reverse)
        for (int i = outputStack.size() - 1; i >= 0; i--) {
            result.append(outputStack.get(i));
            if (i > 0 || !inputStack.isEmpty()) {
                result.append(", ");
            }
        }
    
        // Add elements from inputStack (back of the queue, in the current order)
        for (int i = 0; i < inputStack.size(); i++) {
            result.append(inputStack.get(i));
            if (i < inputStack.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    /**
     * A helper method to move the elements from the input to the output stack
     * 
     * @param inputStack  the stack to empty
     * @param outputStack the stack to fill
     */
    private static <T> void transfer(Stack<T> inputStack, Stack<T> outputStack) {
        // Reverses element order to behave as a stack
        while (!inputStack.empty()) {
            outputStack.add(inputStack.pop());
        }
    }
}
