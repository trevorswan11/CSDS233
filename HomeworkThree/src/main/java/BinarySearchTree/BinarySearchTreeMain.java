package BinarySearchTree;

/**
 * A class to test a BST using a main method.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BinarySearchTreeMain {
    /** A test tree for its corresponding test case */
    private static BinarySearchTree tree1 = new BinarySearchTree(),
            tree2 = new BinarySearchTree(),
            tree3 = new BinarySearchTree(),
            tree4 = new BinarySearchTree(),
            tree5 = new BinarySearchTree(),
            tree6 = new BinarySearchTree();

    /**
     * ! Test Case #1: <p>
     * <strong>Insert Nodes and Display Traversals</strong> <p>
     * ? (1) Insert a set of elements into the tree. <p>
     * ? (2) Display the in-order, pre-order, and post-order traversals after <p>
     * ?     inserting nodes.
     */
    public static void testcaseOne() {
        System.out.println("Case #1 - Insert Nodes and Display Traversals:");

        // Insert the set of elements
        tree1.insert(3);
        tree1.insert(1);
        tree1.insert(4);
        tree1.insert(7);
        tree1.insert(8);
        tree1.insert(9);
        tree1.insert(10);
        tree1.insert(2);

        // Print!
        tree1.inorder();
        tree1.preorder();
        tree1.postorder();
        System.out.println();
    }

    /**
     * ! Test Case #2: <p>
     * <strong>Test CreateTree Method</strong> <p>
     * ? (1) Create a binary search tree from an array of integers and display the <p>
     * ?     in-order traversal.
     */
    public static void testcaseTwo() {
        System.out.println("Case #2 - Test CreateTree Method:");
        int[] arr = {8, -1, 2, 4, 50, 30, -1, 3, 5, 7, 7, 10, 9};
        tree2.createTree(arr);
        System.out.println("\tResult should show keys in ascending order:");
        tree2.inorder();
        System.out.println();
    }

    /**
     * ! Test Case #3: <p>
     * <strong>Test Search Method</strong> <p>
     * ? (1) Search for a few key values (three present and three absent) in the tree <p>
     * ?     and print the result.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseThree() throws Fail {
        System.out.println("Case #3 - Test Search Method:");
        String message;

        // Create the tree
        int[] arr = {8, -1, 2, 4, 50, 30, -1, 3, 5, 7, 7, 10, 19};
        tree3.createTree(arr);
        
        // Search for the elements; 3 in 3 not
        boolean[] results = new boolean[6];
        results[0] = tree3.search(50);
        results[1] = tree3.search(-10000);
        results[2] = tree3.search(10);
        results[3] = tree3.search(0);
        results[4] = tree3.search(19);
        results[5] = tree3.search(11);
        boolean[] expecteds = {true, false, true, false, true, false};

        // Creates some strings to format results
        StringBuilder sb1 = new StringBuilder("\tResults: [");
        StringBuilder sb2 = new StringBuilder("\tExpected: [");

        // Loop through the results and compare them to the expected ones
        for (int i = 0; i < 6; i++) {
            if (results[i] != expecteds[i]) {
                boolean result = results[i];
                boolean expected = expecteds[i];
                message = String.format("Search number %d was %s when %s was expected.", i, result, expected);
                throw new Fail(message);
            }
            sb1.append(results[i]).append(i != 5 ? ", " : ']');
            sb2.append(expecteds[i]).append(i != 5 ? ", " : ']');
        }

        // Print the results
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        System.out.println("Searches of 3 present and 3 absent keys was successful.");
        System.out.println();
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
        System.out.println("Case #4 - Test Delete Method:");
        String message;

        // Create the tree and determine which ones to remove
        int[] arr = {8, -1, 2, 4, 50, 30, -1, 3, 5, 7, 7, 10, 19};
        tree4.createTree(arr);
        int[] toDeleteKeys = {8, 3, 19};
        
        // Print the original inorder traversal
        System.out.println("\tOriginal Tree:");
        tree4.inorder();

        // State which key is to be removed and then the result after
        for (int i = 0; i < 3; i++) {
            message = String.format("\tRemoving key %d -> Result %d:", toDeleteKeys[i], i + 1);
            System.out.println(message);
            tree4.delete(toDeleteKeys[i]);
            tree4.inorder();
        }

        // Try to delete a node that isn't present
        System.out.println("\tTrying to delete key 9 -> Result:");
        tree4.delete(9);
        tree4.inorder();
        System.out.println();
    } 

    /**
     * ! Test Case #5: <p>
     * <strong>Test Find Min and Max Operations</strong> <p>
     * ? (1) Test the findMin() method and display the minimum value <p>
     * ? (2) Test the findMax() method and display the maximum value <p>
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseFive() throws Fail {
        System.out.println("Case #5 - Test Find Min and Max Operations:");
        String message;

        // Try to find the min of an empty (null) tree
        try {
            System.out.println("\tTrying to find the min of a tree with a null root:");
            tree5.findMin();
        } catch (IllegalStateException good) {
            System.out.println("IllegalStateException was thrown as tree is null; correct behavior.");
        } catch (Exception bad) {
            throw new Fail(bad.getMessage());
        }

        // Try to find the max of an empty (null) tree
        try {
            System.out.println("\tTrying to find the max of a tree with a null root:");
            tree5.findMax();
        } catch (IllegalStateException good) {
            System.out.println("IllegalStateException was thrown as tree is null; correct behavior.");
        } catch (Exception bad) {
            throw new Fail(bad.getMessage());
        }

        // Create the tree and determine the minimum and maximum values
        int[] arr = {8, -10, 2, 4, 50, 30, -1, 3, 5, 7, 7, 10, 19};
        tree5.createTree(arr);
        System.out.println("\tTree to analyze:");
        tree5.inorder();
        int expectedMin = -10;
        int expectedMax = 50;

        // Verify the findMin method
        int realMin = tree5.findMin();
        if (expectedMin != realMin) {
            message = String.format("Minimum value of %d was found but %d was expected.", realMin, expectedMin);
            throw new Fail(message);
        }
        message = String.format("Minimum value correctly found to be %d.", realMin);
        System.out.println(message);

        // Verify the findMax method
        int realMax = tree5.findMax();
        if (expectedMax != realMax) {
            message = String.format("Maximum value of %d was found but %d was expected.", realMax, expectedMax);
            throw new Fail(message);
        }
        message = String.format("Maximum value correctly found to be %d.", realMax);
        System.out.println(message);
        System.out.println();
    }

    /**
     * ! Test Case #6: <p>
     * <strong>Test Tree Height Method</strong> <p>
     * ? (1) Test the height() method and display the height of the binary search <p>
     * ?     tree.
     * 
     * @throws Fail upon encountering unexpected behavior
     */
    public static void testcaseSix() throws Fail{
        System.out.println("Case #6 - Test Tree Height Method:");
        String message;
        int expectedHeight;
        int realHeight;

        // Create and test a linear tree
        System.out.println("\tFor a 'linear' tree:");
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        tree6.createTree(arr);
        tree6.inorder();
        expectedHeight = 10;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);

        // Create and test a non-linear tree
        System.out.println("\tFor a 'non-linear' tree:");
        tree6.reset(10);
        int[] additions = {7, 14, 5, 9, 13, 15, 3};
        tree6.createTree(additions);
        tree6.inorder();
        expectedHeight = 3;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);

        // Adding 8 should not change the tree height
        System.out.println("\tAdding a key of 8 to the previous tree:");
        tree6.insert(8);
        tree6.inorder();
        expectedHeight = 3;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);

        // Adding 2 will change the height
        System.out.println("\tAdding a key of 2 to the previous tree:");
        tree6.insert(2);
        tree6.inorder();
        expectedHeight = 4;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);

        // Deleting a leaf node that is not at maximum depth will not change the height
        System.out.println("\tDeleting a key of 15 from the previous tree:");
        tree6.delete(15);
        tree6.inorder();
        expectedHeight = 4;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);

        // Deleting the single leaf node on the maximum depth level will reduce the height
        System.out.println("\tDeleting a key of 2 from the previous tree:");
        tree6.delete(2);
        tree6.inorder();
        expectedHeight = 3;
        realHeight = tree6.height();
        if (expectedHeight != realHeight) {
            message = String.format("Tree was expected to have a height of %d, but %d was returned.", expectedHeight, realHeight);
            throw new Fail(message);
        }
        message = String.format("A height of %d was correctly found.", realHeight);
        System.out.println(message);
        System.out.println();
    }

    // Runs all static test methods
    public static void main(String[] args) throws Fail {
        testcaseOne();
        testcaseTwo();
        testcaseThree();
        testcaseFour();
        testcaseFive();
        testcaseSix();
        System.out.println("Testing completed without any errors :)");
    }

    /**
     * A custom exception to throw if a testcase behaves unexpectedly.
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private static class Fail extends Exception {
        public Fail(String message) {
            super(message);
        }
    }
}
