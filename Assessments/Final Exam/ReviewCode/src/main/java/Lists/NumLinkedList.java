package Lists;

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
     * Reverses the elements in a list
     * 
     * !Operates with constant space in linear time 
     */
    public void reverse() {
        // Create pointers to traverse the list and track nodes
        IntegerNode previous = null;
        IntegerNode current = this.getHead();
        IntegerNode next = null;

        // Loop through the entire list
        while (current != null) {
            // Remember the next node
            next = current.getNext();

            // Change the current nodes pointer to point to the previous node
            current.setNext(previous);

            // Advance the previous and current node's pointers
            previous = current;
            current = next;
        }
        this.setHead(previous);
    }
    
    /**
     * Merges two lists into one, and preserve sorted-ness
     * 
     * @param list1 the first list
     * @param list2 the next node to merge with the first
     * @return The merged NumLinkedList
     */
    public static NumLinkedList merge(NumLinkedList list1, NumLinkedList list2) {
        // Create a dummy node with no data and a traversal pointer
        IntegerNode dummy = new IntegerNode(0); // Initialize with any value
        IntegerNode trav = dummy;
    
        // Get the heads of both lists to traverse
        IntegerNode head1 = list1.getHead();
        IntegerNode head2 = list2.getHead();
    
        // Traverse both lists until one is null
        while (head1 != null && head2 != null) {
            IntegerNode newNode;
            
            // Append the lesser one to trav and move forward
            if (head1.getElement() < head2.getElement()) {
                // Create a new node with the value from head1
                newNode = new IntegerNode(head1.getElement());
                head1 = head1.getNext();
            } else {
                // Create a new node with the value from head2
                newNode = new IntegerNode(head2.getElement());
                head2 = head2.getNext();
            }
    
            // Link the new node to the merged list
            trav.setNext(newNode);
            trav = newNode;
        }
    
        // Append the remaining elements from each non-null list
        while (head1 != null) {
            trav.setNext(new IntegerNode(head1.getElement()));
            trav = trav.getNext();
            head1 = head1.getNext();
        }
        
        while (head2 != null) {
            trav.setNext(new IntegerNode(head2.getElement()));
            trav = trav.getNext();
            head2 = head2.getNext();
        }
    
        return new NumLinkedList(dummy.getNext());
    }
        

    /**
     * Duplicates a list with different memory addresses and new pointers
     * 
     * @param list the list to duplicate
     * @return a new NumLinkedList that is a duplicate of the original
     */
    public static NumLinkedList duplicate(NumLinkedList list1) {
        // Check if the original list is empty
        if (list1 == null || !list1.iterator().hasNext()) {
            return new NumLinkedList(null);
        }

        // Create an iterator if the list has elements
        Iterator<IntegerNode> trav = list1.iterator();

        // Initialize the head and the pointer to aid in list creation
        IntegerNode headCopy = new IntegerNode(trav.next().getElement(), null);
        IntegerNode currentCopy = headCopy;

        // Create a new node from the element data of trav 
        while (trav.hasNext()) {
            IntegerNode newNode = new IntegerNode(trav.next().getElement(), null);

            // Set the next node and advance the creation pointer
            currentCopy.setNext(newNode);
            currentCopy = newNode;

        }
        return new NumLinkedList(headCopy);
    }

    /**
     * Prints the elements in a List in order
     * 
     * @return the formatted string representation of the list
     */
    @Override
    public String toString() {
        // Create an iterator and builder for memory efficiency
        Iterator<IntegerNode> trav = this.iterator();
        StringBuilder listString = new StringBuilder();

        // Append each element to the builder separated by a pointer
        while (trav.hasNext()) {
            listString.append(trav.next().getElement());
            listString.append(" -> ");
        }
        return listString.append("null").toString();
    }

    /**
     * Checks if elements in a list are equal
     * 
     * @param obj the object to test against the called list
     * @return true if the objects are exactly equal
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same reference
        if (this == obj) {
            return true;
        }

        // Check if the object is of the same type
        if (!(obj instanceof NumLinkedList)) {
            return false;
        }

        // Typecast the object and create two iterators
        NumLinkedList list2 = (NumLinkedList) obj;
        Iterator<IntegerNode> itr1 = this.iterator();
        Iterator<IntegerNode> itr2 =  list2.iterator();

        // Iterate through both lists and compare each element
        while (itr1.hasNext() && itr2.hasNext()) {
            if (itr1.next().getElement() != itr2.next().getElement()) {
                return false;
            }
        }

        // Check if both lists have had their elements exhausted
        return !itr1.hasNext() && !itr2.hasNext();
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