package HashTable;

import HashTable.HW4_tcs94.ProbingError;

/**
 * A class to test the Hash Table implementation for HW4.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class HW4_tcs94_Test {
    /**
     * Tests the linear probing method using the array:<p>
     * ! [10, 3, 17, 14, 18, 3, 8, 1, 18, 11]
     * 
     * @param size the int size to use
     */
    public static void linearProbingInsertTest(int size){
        System.out.println("Testing the linear probing method with size " + size + ":");
        HW4_tcs94 testOne = new HW4_tcs94(size);
        int[] arr = {10, 3, 17, 14, 18, 3, 8, 1, 18, 11};
        try {
            for (int key : arr) { testOne.linearProbingInsert(key); }
        } catch (ProbingError e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the double hashing method using the array:<p>
     * ! [7, 17, 27, 37, 47, 57, 67, 77, 87, 97]
     * <p> and the prime:<p>
     * ! prime = 7
     * 
     * @param size the int size to use
     */
    public static void doubleHashingInsertTest(int size) {
        System.out.println("Testing the double hashing method with size " + size + ":");
        HW4_tcs94 testTwo = new HW4_tcs94(size);
        int[] arr = {7, 17, 27, 37, 47, 57, 67, 77, 87, 97};
        try {
            for (int key : arr) { testTwo.doubleHashingInsert(key, 7); }
        } catch (ProbingError e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ProbingError {
        // Test 1
        linearProbingInsertTest(10);
        System.out.println();
        linearProbingInsertTest(13);
        System.out.println();
        linearProbingInsertTest(20);
        System.out.println();
        linearProbingInsertTest(9);
        System.out.println();

        // Test 2
        doubleHashingInsertTest(10);
        System.out.println();
        doubleHashingInsertTest(11);
        System.out.println();
        doubleHashingInsertTest(13);
        System.out.println();
        doubleHashingInsertTest(12);
    }
}
