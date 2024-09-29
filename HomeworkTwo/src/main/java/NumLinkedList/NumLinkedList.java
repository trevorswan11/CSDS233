package NumLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A list of IntegerNodes
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class NumLinkedList implements Iterable<IntegerNode> {
    // Attribute for iterable use
    private IntegerNode head;

    /**
     * Sets the head for the list
     * 
     * @param head an IntegerNode to be the new head
     */
    public void setHead(IntegerNode head) {
        this.head = head;
    }

    /**
     * Gets the head of the list
     * 
     * @return the head of the IntegerNode list
     */
    public IntegerNode getHead() {
        return this.head;
    }

    /**
     * Creates a new NumLinkedList with a desired head node.
     * 
     * @param head the desired head of the list
     */
    public NumLinkedList(IntegerNode head) {
        this.setHead(head);
    }

    /**
     * Checks if the list is empty (size == 0)
     * 
     * @return true if there are no elements in the list
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the size of a list
     * 
     * @return the total number of elements in the list
     */
    public int size() {
        // Create a new iterator and counter for the elements
        Iterator<IntegerNode> trav = this.iterator();
        int numElements = 0;

        // Add up the elements until there are none left
        while (trav.hasNext()) {
            trav.next();
            numElements++;
        }
        return numElements;
    }

    /**
     * Adds an int to the end of the list
     * 
     * @param element the element to append to the list
     */
    public void add(int element) {
        // Create a head if the list is empty
        if (this.isEmpty()) {
            this.setHead(new IntegerNode(element, null));
        }

        // Otherwise append the element
        else {
            // Traverse the list with a pointer until null is found
            IntegerNode nodeptr = this.getHead();
            while (nodeptr.getNext() != null) {
                nodeptr = nodeptr.getNext();
            }

            // Create a new node to point to
            nodeptr.setNext(new IntegerNode(element, null));
        }
    }

    /**
     * Checks if a list is sorted in ascending order.
     * 
     * @return true if the list is sorted in ascending order
     */
    public boolean isSorted() {
        IntegerNode nodeptr = this.getHead();

        // Consider a list sorted if it has 0 or 1 elements
        if (nodeptr == null || nodeptr.getNext() == null) {
            return true;
        }

        // Track the next node and compare with previous
        while (nodeptr.getNext() != null) {
            IntegerNode nextNode = nodeptr.getNext();
            
            // List is not sorted if any element exceeds the next
            if (nodeptr.getElement() > nextNode.getElement()) {
                return false;
            }
            nodeptr = nextNode;
        }
        return true;
    }

    /**
     * Reverses the elements in a list in constant space with linear time 
     */
    public void reverse() {

    }
    
    /**
     * Merges two lists into one, and preserve sorted-ness
     * 
     * @param list1 the first list
     * @param list2 the next node to merge with the first
     * @return The merged NumLinkedList
     */
    public static NumLinkedList merge(NumLinkedList list1, NumLinkedList list2) {
        return null;
    }

    /**
     * Duplicates a list with different memory addresses and new pointers
     * 
     * @param list the list to duplicate
     * @return a new NumLinkedList that is a duplicate of the original
     */
    public static NumLinkedList duplicate(NumLinkedList list1) {
        return null;
    }

    /**
     * Creates an iterator for a list
     */
    @Override
    public Iterator<IntegerNode> iterator() {
        return new NumLinkedListIterator(this.getHead());
    }
    
    /**
     * Private nested class to handle iterator behavior
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class NumLinkedListIterator implements Iterator<IntegerNode> {
        // Keep track of the current node for traversals
        private IntegerNode currentNode;

        /**
         * Creates an iterator starting at a given node
         * @param node
         */
        public NumLinkedListIterator(IntegerNode node) {
            this.currentNode = node;
        }

        /**
         * Checks if there are more nodes in the list
         * 
         * @return true if the list has more elements
         */
        @Override
        public boolean hasNext() {
            return this.currentNode != null;
        }

        /**
         * Returns the next node in the list
         * 
         * @return the next IntegerNode in the list
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public IntegerNode next() {
            // Check if there is a next element
            if (!this.hasNext()) {
                throw new NoSuchElementException("List is empty");
            }

            // return the current node and increment the pointer
            IntegerNode temp = this.currentNode;
            this.currentNode = this.currentNode.getNext();
            return temp;
        }
    }
}