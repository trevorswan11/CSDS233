package BinarySearchTree;

/**
 * A class to test a BST using a main method.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BinarySearchTreeMain {
    /** A test tree for test cases */
    BinarySearchTree tree1, tree2, tree3, tree4, tree5;

    /**
     * The entry point for the class to display the required test cases 
     */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.createTree(new int[] {1, 2, -2, -4, 5, 6, -7, -5, 9, 10});
        bst.printTree();
    }
}
