package Sorting;

/**
 * Required sorting methods for Assignment 5
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class Sort {
    /**
     * ! Question 1 - Insertion Sort
     * <p>
     * 
     * Sorts an array into descending order using the Insertion Sort algorithm. The
     * state of the array is printed after each swap.
     * 
     * ? Number of comparisons:
     * <p>
     * ? Number of Swaps:
     * <p>
     * ? Number of Accesses:
     * 
     * @param array The array to be sorted
     */
    public void insertionSort(int[] array) {

    }

    /**
     * ! Question 2 - Merge Sort
     * <p>
     * 
     * Sorts an array into descending order using the Merge Sort algorithm. The
     * state of the array is printed after each merge operation.
     * 
     * ? Number of comparisons:
     * <p>
     * ? Number of Swaps:
     * <p>
     * ? Number of Accesses:
     * 
     * @param array The array to be sorted
     */
    public void mergeSort(int[] array) {

    }

    /**
     * ! Question 3 - Quick Sort
     * <p>
     * 
     * Sorts an array into descending order using the Quick Sort algorithm. The
     * state of the array is printed after each partitioning operation.
     * 
     * ? Number of comparisons:
     * <p>
     * ? Number of Swaps:
     * <p>
     * ? Number of Accesses:
     * 
     * @param array The array to be sorted
     * @param low   The lower index of the partition
     * @param high  The upper index of the partition
     */
    public void quickSort(int[] array, int low, int high) {

    }

    /**
     * ! Question 4 - Bucket Sort
     * <p>
     * 
     * Sorts an array into descending order using the Bucket Sort algorithm. The
     * state of the buckets and the array is printed after
     * distributing elements into buckets and after each bucket is sorted and
     * combined.
     * 
     * ? Number of comparisons:
     * <p>
     * ? Number of Swaps:
     * <p>
     * ? Number of Accesses:
     * 
     * @param array The array to be sorted
     * @param size  The size of each bucket
     */
    public void bucketSort(int[] array, int size) {

    }

    /**
     * Prints the elements in an array
     * 
     * @param array The array to print
     */
    public static String str(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(i != array.length - 1 ? ", " : "]");
        }
        return sb.toString();
    }

    /**
     * ! Testing
     * <p>
     * 
     * The entry method for this class to test the sorting methods.
     * <p>
     * 
     * ? [26, 13, 72, 3, 17, 37, 0, 17, 73, 45] is to be sorted in descending order
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        // The specified array to be sorted
        int[][] arr = {
                { 26, 13, 72, 3, 17, 37, 0, 17, 73, 45 },
                { 26, 13, 72, 3, 17, 37, 0, 17, 73, 45 },
                { 26, 13, 72, 3, 17, 37, 0, 17, 73, 45 },
                { 26, 13, 72, 3, 17, 37, 0, 17, 73, 45 }
        };

        // Test Insertion Sort and print result
        new Sort().insertionSort(arr[0]);
        System.out.println("Insertion Sort Result: " + str(arr[0]));

        // Test Merge Sort and print result
        new Sort().mergeSort(arr[1]);
        System.out.println("Merge Sort Result: " + str(arr[1]));

        // Test Quick Sort and print result
        new Sort().quickSort(arr[2], 0, arr[2].length);
        System.out.println("Quick Sort Result: " + str(arr[2]));

        // Test Bucket Sort and print result
        new Sort().bucketSort(arr[3], 3);
        System.out.println("Bucket Sort Result: " + str(arr[3]));
    }
}

/**
 * ! Analysis
 * 
 * Algorithm with Fewest Steps:
 * 
 * Efficiency:
 */