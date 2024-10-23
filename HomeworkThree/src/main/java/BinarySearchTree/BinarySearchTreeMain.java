package BinarySearchTree;

/**
 * A class to test a BST using a main method.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BinarySearchTreeMain {
    /** A test tree for its corresponding test case */
    private BinarySearchTree tree1, tree2, tree3, tree4, tree5, tree6;

    /**
     * ! Test Case #1: <p>
     * <strong>Insert Nodes and Display Traversals</strong> <p>
     * ? (1) Insert a set of elements into the tree. <p>
     * ? (2) Display the in-order, pre-order, and post-order traversals after <p>
     * ?     inserting nodes.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseOne() {
    }

    /**
     * ! Test Case #2: <p>
     * <strong>Test CreateTree Method</strong> <p>
     * ? (1) Create a binary search tree from an array of integers and display the <p>
     * ?     in-order traversal.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseTwo() {

    }

    /**
     * ! Test Case #3: <p>
     * <strong>Test Search Method</strong> <p>
     * ? (1) Search for a few key values (three present and three absent) in the tree <p>
     * ?     and print the result.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseThree() {

    }

    /**
     * ! Test Case #4: <p>
     * <strong>Test Delete Method</strong> <p>
     * ? (1) Delete three nodes from the tree and display the in-order traversal <p>
     * ?     after each deletion.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseFour() {

    } 

    /**
     * ! Test Case #5: <p>
     * <strong>Test Find Min and Max Operations</strong> <p>
     * ? (1) Test the findMin() method and display the minimum value <p>
     * ? (2) Test the findMax() method and display the maximum value <p>
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseFive() {

    }

    /**
     * ! Test Case #6: <p>
     * <strong>Test Tree Height Method</strong> <p>
     * ? (1) Test the height() method and display the height of the binary search <p>
     * ?     tree.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseSix() {

    }

    // Runs all static test methods
    public static void main(String[] args) {
        testcaseOne();
        testcaseTwo();
        testcaseThree();
        testcaseFour();
        testcaseFive();
        testcaseSix();
    }

    /**
     * A custom exception to throw if a testcase behaves unexpectedly.
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class Fail extends Exception {
        private String message;
        public Fail(String message) {
            this.message = message;
        }
    }
}
