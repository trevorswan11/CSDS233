package BinarySearchTree;

/**
 * An implemented binary search tree with required methods and operations
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BinarySearchTree {
    /** The root of the BST */
    private Node root = null;

    /**
     * Creates a BST with a given int key
     * 
     * @param key the int key for the root
     */
    public BinarySearchTree(int key) {
        this.root = new Node(key);
    }

    /**
     * Returns the root of the BST
     * 
     * @return the root Node
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Inserts a new node with a given key into the binary search tree
     * 
     * @param key the int key to use for the node
     */
    public void insert(int key) {

    }

    /**
     *  Creates a binary search tree from an array of integers
     * 
     * @param values the key values to insert into a new tree
     */
    public void createTree(int[] values) {

    }

    /**
     * Searches for a node with a given key in the binary search tree
     * 
     * @param key the int key to look for
     * @return true if the key is found, false otherwise
     */
    public boolean search(int key) {
        return true;
    }

    /**
     * Deletes a node with the specified key from the binary search tree and
     * returns it
     * 
     * @param key the key of the node to delete
     * @return the deleted node, null if not found
     */
    public Node delete(int key) {
        return null;
    }

    /**
     * Finds the minimum key value in the binary search tree
     * 
     * @return the minimum int key
     */
    public int findMin() {
        return 0;
    }

    /**
     * Finds the maximum key value in the binary search tree
     * 
     * @return the maximum int key
     */
    public int findMax() {
        return 0;
    }

    /**
     * Finds the height of the binary search tree
     * 
     * @return the int height or depth of the tree
     */
    public int height() {
        return 0;
    }

    /**
     * Traverses the tree in in-order and prints the elements
     */
    public void inorderTrav() {

    }

    /**
     * Traverses the tree in pre-order and prints the elements
     */
    public void preorderTrav() {

    }

    /**
     * Traverses the tree in post-order and prints the elements
     */
    public void postorderTrav() {

    }

    /**
     * A nested class to represent a node in the binary tree. Fields are to be
     * accessed and modified directly, there is no boilerplate :)
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    @SuppressWarnings("unused")
    private class Node {
        // The key and children of a node
        private int key;
        private Node left, right;

        /**
         * Creates a BST Node with a given int and null children
         * 
         * @param key the int to have as the Nodes key
         */
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }
}
