package NumLinkedList;

/**
 * A class to represent a node in a singly linked list containing integers.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class IntegerNode {
    // Basic Node attributes
    private int element;
    private IntegerNode nextNode;

    // Default constructor for operations
    public IntegerNode() {}

    /**
     * Creates an IntegerNode with a given element and succeeding node.
     * 
     * @param element the int to set as the node's element
     * @param nextNode the node to be pointed to
     */
    public IntegerNode(int element, IntegerNode nextNode) {
        this.setElement(element);
        this.setNext(nextNode);
    }

    /**
     * Return the element of the current node
     * 
     * @return the int element
     */
    public int getElement() {
        return this.element;
    }

    /**
     * Sets the element of the current node
     * 
     * @param the int element
     */
    public void setElement(int element) {
        this.element = element;
    }

    /**
     * Return the next node for the current node
     * 
     * @return the node being pointed to, null if null
     */
    public IntegerNode getNext() {
        return this.nextNode;
    }

    /**
     * Set the node to be pointed to by the current node
     * 
     * @param nextNode the desired node to put next
     */
    public void setNext(IntegerNode nextNode) {
        this.nextNode = nextNode;
    }
}
