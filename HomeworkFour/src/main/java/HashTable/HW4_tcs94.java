package HashTable;

/**
 * A class to implement a Hash Table for HW4. It resolves collisions using both
 * linear probing and double hashing without using built-in hash table
 * libraries.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class HW4_tcs94 {
    /** The underlying array-based hash table to operate on. */
    private Integer[] ht;

    /**
     * Creates a new hash table with a specified size. The table is empty upon
     * initialization. The table is inaccessible, and all values being as null.
     * 
     * @param size the int size of the hash table to create
     */
    public HW4_tcs94(int size) {
        this.ht = new Integer[size];
    }

    /**
     * Inserts the given key into the hash table using linear probing for collision
     * resolution. The state of the table is printed to the console after each
     * insertion.
     * 
     * @param key the desired int key to input into the table
     */
    public void linearProbingInsert(int key) {
        
    }

    /**
     * Inserts the given key into the hash table using double hashing for collision
     * resolution. The first hash is based off of the size only. The second hash
     * makes use of the prime. The state of the table is printed to the console
     * after each insertion.
     * 
     * @param key   the int key to insert into the table
     * @param prime the prime to use for the second hashing function to resolve
     *              collisions
     */
    public void doubleHashingInsert(int key, int prime) {

    }

    /**
     * Prints the current state of the hash table. The table is printed as
     * comma-separated list of elements, with null being printed in place of empty
     * slots.
     */
    public void printTable() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < ht.length; i++) {
            sb.append(ht[i]).append((i != ht.length - 1) ? ", " : "]");
        }
        System.out.println(sb.toString());
    }
}
