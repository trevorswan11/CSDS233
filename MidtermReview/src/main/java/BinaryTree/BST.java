package BinaryTree;

import Queue.*;

public class BST<T> {
    private Node<T> root;

    public BST() {
        this.root = null;                               // Tree should start as being empty and need to build up
    }

    public static <T> BST<T> treeify(int[] keys, T[] elements) {
        if (keys.length != elements.length)
            throw new ArrayStoreException("Number of keys must agree with number of elements.");
        BST<T> resultTree = new BST<>();
        for (int i = 0; i < keys.length; i++)
            resultTree.insert(keys[i], elements[i]);
        return resultTree;
    }

    public int size() {
        return root == null ? 0 : treeSize(this.root);
    }

    private int treeSize(Node<T> root) {
        if (root == null)
            return 0;

        return 1 + treeSize(root.left) + treeSize(root.right);
    }

    public int height() {
        return nodeHeight(this.root);
    }

    private int nodeHeight(Node<T> node) {
        if (node == null)                               // Node's height is -1 if it is null
            return -1;

        return 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
    }

    public Node<T> search(int key) {                    // Recursive implementation as search is done iteratively for the rest of this file
        Node<T> n = searchTree(root, key);
        return n == null ? null : n;
    }

    private Node<T> searchTree(Node<T> root, int key) {
        if (root == null)
            return null;                                // Base case 1: key is not found
        else if (key == root.key)
            return root;                                // Base case 2: key is found at current subtree root
        else if (key < root.key)
            return searchTree(root.left, key);          // Recursive call to appropriate subtree 
        else
            return searchTree(root.right, key);
    }

    public void insert(int key) {
        insert(key, null);                      // Insertion for testing, key is only important for now
    }

    public void insert(int key, T element) {
        Node<T> trav = root;
        Node<T> parent = null;
        while (trav != null) {                          // Find the node according to BST rules
            parent = trav;
            if (key < trav.key)
                trav = trav.left;
            else
                trav = trav.right;
        }
        if (parent == null)                             // Assumes tree starts as null
            this.root = new Node<T>(key, element);
        else if (key < parent.key)
            parent.left = new Node<T>(key, element); 
        else
            parent.right = new Node<T>(key, element);
    }

    public T delete(int key) {
        Node<T> trav = root;
        Node<T> parent = null;
        while (trav != null && trav.key != key) {       // Find the node with the key to delete
            parent = trav;
            if (key < trav.key)
                trav = trav.left;
            else
                trav = trav.right;
        }
        T deletedElement = trav.element;                // Store the element and delete its Node
        deleteNode(trav, parent);
        return deletedElement;
    }

    private void deleteNode(Node<T> toDelete, Node<T> parent) {
        if (toDelete.left == null || toDelete.right == null) {
            Node<T> toDeleteChild = null;                   // toDelete has zero or no children
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
        } else {                                            
            Node<T> replacementParent = toDelete;           // toDelete must then have 2 children
            Node<T> replacementNode = toDelete.right;
            while (replacementNode.left != null) {          // Find toDelete's inorder successor     
                replacementParent = replacementNode;
                replacementNode = replacementNode.left;
            }
            toDelete.key = replacementNode.key;             // Copy the data of the replacement node to the one to be deleted
            toDelete.element = replacementNode.element;
            deleteNode(replacementNode, replacementParent); // Delete the copied node - will have either one or zero children
        }
    }

    public void preorder() {
        System.out.print("BST.preorder(): ");
        myPreOrder(root);
        System.out.println();
    }

    private void myPreOrder(Node<T> root) {
        if (root == null)
            return;
        System.out.print(root.key);                         // "pre" order = print statement FIRST (pre-recursion)
        System.out.print(" ");
        myPreOrder(root.left);
        myPreOrder(root.right);
    }
    
    public void postorder() {
        System.out.print("BST.postorder(): ");
        myPostOrder(root);
        System.out.println();
    }

    private void myPostOrder(Node<T> root) {
        if (root == null)
            return;
        myPostOrder(root.left);
        myPostOrder(root.right);
        System.out.print(root.key);                         // "post" order = print statement AFTER (post-recursion)
        System.out.print(" ");
        
    }
    
    public void inorder() {
        System.out.print("BST.inorder(): ");
        myInOrder(root);
        System.out.println();
    }

    private void myInOrder(Node<T> root) {
        if (root == null)
            return;
        myInOrder(root.left);
        System.out.print(root.key);                         // "In" order = print statement INSIDE (in between recursive calls)
        System.out.print(" ");
        myInOrder(root.right);
    }

    public void levelorder() {
        System.out.print("BST.levelorder(): ");
        if (this.root == null) {                            // No queue operations can be done if tree is empty
            System.out.println();
            return;
        }
        CircularQueue<Node<T>> queue = new CircularQueue<>(this.size());
        queue.enqueue(this.root);                                
        while (!queue.isEmpty()) {
            Node<T> node = queue.dequeue();                 // Pull the next item
            if (node.left != null)
                queue.enqueue(node.left);                   // Enqueue the nodes from left to right
            if (node.right != null)
                queue.enqueue(node.right);
            System.out.print(node.key);                     // Print the item that was pulled
            System.out.print(" ");
        }
        System.out.println();
    }

    private class Node<E> {
        private int key;
        private E element;
        private Node<E> left, right;

        public Node(int key, E element) {
            this.element = element;
            this.key = key;
            this.left = null;                               // Children should always start as null
            this.left = null;
        }
    } 
    
    public void printTree() {
        printTree(root, 0);
        System.out.println();
    }

    private void printTree(Node<T> node, int space) {
        if (node == null) {
            return;
        }
        space += 5;                                         // Increase distance between levels
        printTree(node.right, space);                       // Process right child first
        System.out.print("\n");
        for (int i = 5; i < space; i++) {                   // Print number of spaces
            System.out.print(" ");
        }
        System.out.print(node.key);
        printTree(node.left, space);                        // Process left child now
    }

    public void data() {
        this.printTree();
        this.preorder();
        this.postorder();
        this.inorder();
        this.levelorder();
        System.out.print("BST.height(): ");
        System.out.println(this.height());
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.data();
        tree.insert(9);
        tree.insert(10);
        tree.insert(8);
        tree.insert(1);
        tree.insert(11);
        tree.insert(2);
        tree.insert(3);
        tree.insert(13);
        tree.insert(14);
        tree.insert(17);
        tree.insert(18);
        tree.insert(16);
        tree.insert(12);
        tree.data();
        tree.delete(16);
        tree.data();
        tree.insert(11);
        tree.delete(13);
        tree.data();
    }
}
