package BinarySearchTree;

/**
 * An implemented binary search tree with required methods and operations
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BinarySearchTree {
    /** The root of the BST */
    private Node root;

    /**
     * Creates a BST with a given int key
     * 
     * @param key the int key for the root
     */
    public BinarySearchTree(int key) {
        this.root = new Node(key);
    }

    /**
     * Creates a BST with a null root
     */
    public BinarySearchTree() {
        this.root = null;
    };

    /**
     * Inserts a new node with a given key into the binary search tree
     * 
     * @param key the int key to use for the node
     */
    public void insert(int key) {
        // Find the parent of the new node
        Node parent = null;
        Node trav = this.root;
        while (trav != null) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }

        // Insert the node according to BST rules
        if (parent == null) {
            // Tree must be empty
            this.root = new Node(key);
        } else if (key < parent.key) {
            parent.left = new Node(key);
        } else {
            parent.right = new Node(key);
        }
    }

    /**
     *  Creates a binary search tree from an array of integers
     * 
     * @param values the key values to insert into a new tree
     */
    public void createTree(int[] values) {
        for (int value : values) {
            this.insert(value);
        }
    }

    /**
     * Searches for a node with a given key in the binary search tree
     * 
     * @param key the int key to look for
     * @return true if the key is found, false otherwise
     */
    public boolean search(int key) {
        // The node is in the tree only if null is not returned
        return this.searchTree(this.root, key) != null;
    }
    
    /**
     * A helper method to search the tree for a certain key node
     * 
     * @param root the root of the tree
     * @param key the key of the root 
     * @return the node if it exists
     */
    private Node searchTree(Node root, int key) {
        Node trav = root;
        while (trav != null) {
            if (key == trav.key) {
                return trav;
            } else if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        return null;
    }

    /**
     * Deletes a node with the specified key from the binary search tree and
     * returns it
     * 
     * @param key the key of the node to delete
     * @return the deleted node, null if not found
     */
    public Node delete(int key) {
        // Find the node with the key to delete
        Node trav = root;
        Node parent = null;
        while (trav != null && trav.key != key) {       
            parent = trav;
            if (key < trav.key)
                trav = trav.left;
            else
                trav = trav.right;
        }

        // Store the element and delete its Node
        Node deletedNode = trav;;                       
        deleteNode(trav, parent);
        return deletedNode;
    }
    
    // Helper method to handle the specific cases of deletion after searching
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left == null || toDelete.right == null) {
            // toDelete has zero or no children
            Node toDeleteChild = null;                      
            if (toDelete.left != null)
                toDeleteChild = toDelete.left;              // check if the left child exists
            else
                toDeleteChild = toDelete.right;             // right child either exists or is null

            if (toDelete == this.root)
                this.root = toDeleteChild;                  // make delete's child the new root
            else if (toDelete.key < parent.key)
                parent.left = toDeleteChild;                // toDeleteChild must be less than parent due to BST rules
            else
                parent.right = toDeleteChild;
        } 
        
        // toDelete must then have 2 children
        else {                                            
            Node replacementParent = toDelete;              
            Node replacementNode = toDelete.right;
            while (replacementNode.left != null) {          // Find toDelete's inorder successor     
                replacementParent = replacementNode;
                replacementNode = replacementNode.left;
            }
            toDelete.key = replacementNode.key;             // Copy the data of the replacement node to the one to be deleted
            deleteNode(replacementNode, replacementParent); // Delete the copied node - will have either one or zero children
        }
    }

    /**
     * Finds the minimum key value in the binary search tree
     * 
     * @return the minimum int key
     * @throws IllegalStateException if the tree is empty
     */
    public int findMin() {
        if (this.root == null) {
            throw new IllegalStateException("Tree is empty"); // Handle empty tree case
        }

        // Traverse as far left as possible
        Node trav = this.root;
        while (trav.left != null) {
            trav = trav.left;
        }
        return trav.key;
    }

    /**
     * Finds the maximum key value in the binary search tree
     * 
     * @return the maximum int key
     * @throws IllegalStateException if the tree is empty
     */
    public int findMax() {
        if (this.root == null) {
            throw new IllegalStateException("Tree is empty"); // Handle empty tree case
        }

        // Traverse as far right as possible
        Node trav = this.root;
        while (trav.right != null) {
            trav = trav.right;
        }
        return trav.key;
    }

    /**
     * Finds the height of the binary search tree
     * 
     * @return the int height or depth of the tree
     */
    public int height() {
        return nodeHeight(this.root);
    }

    // Helper method to handle height of a tree
    private int nodeHeight(Node node) {
        // Node's height is -1 if it is null
        if (node == null)                               
            return -1;

        // Recursively call nodeHeight on both subtrees
        return 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
    }

    /**
     * Traverses the tree in in-order and prints the elements
     */
    public void inorder() {
        System.out.print("BST.inorder(): ");
        myInOrder(root);
        System.out.println();
    }

    // Recursive helper method to handle traversal
    private void myInOrder(Node root) {
        if (root == null)
            return;
        myInOrder(root.left);                               // L-N-R order
        System.out.print(root.key);                         // "In" order = print statement INSIDE (in between recursive calls)
        System.out.print(" ");
        myInOrder(root.right);
    }

    /**
     * Traverses the tree in pre-order and prints the elements
     */
    public void preorder() {
        System.out.print("BST.preorder(): ");
        myPreOrder(root);
        System.out.println();
    }

    // Recursive helper method to handle traversal
    private void myPreOrder(Node root) {
        if (root == null)
            return;
        System.out.print(root.key);                         // "pre" order = print statement FIRST (pre-recursion)
        System.out.print(" ");
        myPreOrder(root.left);                              // N-L-R order
        myPreOrder(root.right);
    }

    /**
     * Traverses the tree in post-order and prints the elements
     */
    public void postorder() {
        System.out.print("BST.postorder(): ");
        myPostOrder(root);
        System.out.println();
    }

    // Recursive helper method to handle traversal
    private void myPostOrder(Node root) {
        if (root == null)
            return;
        myPostOrder(root.left);                             // L-R-N order
        myPostOrder(root.right);
        System.out.print(root.key);                         // "post" order = print statement AFTER (post-recursion)
        System.out.print(" ");
        
    }

    /**
     * Resets the tree, setting the root node to have no children with a desired key.
     * 
     * @param key the int key to use for the new root
     */
    public void reset(int key) {
        this.root = new Node(key);
    }

    /**
     * A nested class to represent a node in the binary tree. Fields are to be
     * accessed and modified directly, there is no boilerplate :)
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class Node {
        /** The {@code key} of the node */
        private int key;

        /** A pointer to the node's {@code left} child */
        private Node left;

        /** A pointer to the node's {@code right} child */
        private Node right;

        /**
         * Creates a BST node with a given int and null children
         * 
         * @param key the int to have as the node's key
         */
        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node node, int space) {
        if (node == null) {
            return;
        }
        // Increase distance between levels
        space += 5;

        // Process right child first
        printTree(node.right, space);

        // Print current node after space count
        System.out.print("\n");
        for (int i = 5; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(node.key);

        // Process left child
        printTree(node.left, space);
    }
}
