package Sorting;

import java.util.LinkedList;

/**
 * Required sorting methods for Assignment 5
 * Possibly Tweaked for Final Exam Prep
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
     * ? Number of comparisons: n(n-1)/2 -> O(n^2) total comparisons. Each iteration of the
     * ? outer loop depends on the number of elements less than toInsert
     * <p>
     * ? Number of Swaps: n(n-1)/2 + (n - 1) -> O(n^2) total swaps (shifts here). Each
     * ? iteration of the inner loop involves a shift operation, ending with the final
     * ? assignment
     * <p>
     * ? Number of Accesses: n(n-1)/2 + 2 x ( n(n-1)/2 + (n - 1) ) + (n - 1) -> O(n^2) . There
     * ? are a total of n(n-1)/2 reads for comparisons, 2 x ( n(n-1)/2 + (n - 1) ) for
     * ? reads and writes for swaps, and an additional n - 1 writes
     * 
     * @param array The array to be sorted
     */
    public void insertionSort(int[] array) {
        if (array == null || array.length <= 1)
            return;
        // Loop through whole array, starts comparison with the 1st and 0th index
        int n = array.length;
        int swapNumber = 0;
        for (int i = 1; i < n; i++) {
            int toInsert = array[i], j;

            // Move elements of array[0..i-1] that are less than toInsert one position ahead
            for (j = i - 1; j >= 0 && array[j] < toInsert; j--) {
                array[j + 1] = array[j];
                System.out.println("State after swap " + swapNumber++ + ": " + str(array));
            }
            array[j + 1] = toInsert;
            System.out.println("State after swap " + swapNumber++ + ": " + str(array));

        }

        // Print the sorted array
        System.out.println("Insertion Sort Result: " + str(array) + "\n");
    }

    /**
     * ! Question 2 - Merge Sort
     * <p>
     * 
     * Sorts an array into descending order using the Merge Sort algorithm. The
     * state of the array is printed after each merge operation.
     * 
     * ? Number of comparisons: On the order of O(nlogn). Each element needs to be
     * ? compared at least once at every level, for n total elements. There are a
     * ? total of logn levels of recursion as the array is split each time
     * <p>
     * ? Number of Swaps: On the order of O(nlogn). Since there are n elements at
     * ? logn levels of recursion, there also ends up being approximately nlogn swaps
     * ? during merging
     * <p>
     * ? Number of Accesses: On the order of O(nlogn). Two array accesses are made
     * ?(one for array[i] and one for array[j]). For shifting, there are two accesses
     * ?for indices k and k - 1. One access occurs to place the element in the array.
     * ? This is a linear number of operations at logn levels of recursion
     * 
     * @param array The array to be sorted
     */
    public void mergeSort(int[] array) {
        if (array == null || array.length <= 1)
            return;
        myMergeSort(array, 0, array.length - 1);
        System.out.println("Merge Sort Result: " + str(array) + "\n");
    }

    /**
     * Recursively splits and sorts portions of the array in place
     * 
     * @param array The array to be sorted
     * @param left  the starting index of the portion to sort
     * @param right The ending index of the portion to sort
     */
    private void myMergeSort(int[] array, int left, int right) {
        // Base case
        if (left >= right)
            return;

        // Find the midpoint and recursively sort the parts
        int mid = left + (right - left) / 2;
        myMergeSort(array, left, mid);
        myMergeSort(array, mid + 1, right);

        // Merge the sorted parts
        merge(array, left, mid, right);
    }

    /**
     * Merges two sorted portions of the array in descending order in place.
     *
     * @param array The array to merge
     * @param left  The starting index of the first portion
     * @param mid   The ending index of the first portion
     * @param right The ending index of the second portion
     */
    private void merge(int[] array, int left, int mid, int right) {
        // Create pointers for the first and second portions
        int i = left;
        int j = mid + 1;

        // Loop through the portions to sort the array
        while (i <= mid && j <= right) {
            // Check if the left element is in the correct place
            if (array[i] >= array[j]) {
                i++;
            }

            // Otherwise, the right element is larger and a shift must occur
            else {
                int temp = array[j];
                int k = j;

                // Shift all elements from i to j one to the right
                while (k > i) {
                    array[k] = array[k - 1];
                    k--;
                }

                // Place the larger element in the correct position and adjust pointers
                array[i] = temp;
                i++;
                mid++;
                j++;
            }
        }
        System.out.println("Array State: " + str(array));
    }

    /**
     * ! Question 3 - Quick Sort
     * <p>
     * 
     * Sorts an array into descending order using the Quick Sort algorithm. The
     * state of the array is printed after each partitioning operation.
     * 
     * ? Number of comparisons: On the order of O(nlogn). There are n comparisons
     * ? per level of recursion in the while loop found in the partition method. This
     * ? is called at each level of recursion, which is logn.
     * <p>
     * ? Number of Swaps: On the order of O(nlogn). There are a total of n swaps
     * ? done for each partition call, as seen by the calling of the swap method. This
     * ? occurs logn times due to the total number of recursion levels.
     * <p>
     * ? Number of Accesses: On the order of O(nlogn). There are accesses occurring
     * ? at every level of recursion (logn levels) when comparing to the pivot in the
     * ? while loop and when the values are swapped. This results in a number of
     * ? accesses at one level on linear order.
     * <p>
     * ? Note: For other quickSort implementations, the above can tend towards
     * ? O(n^2) if the partitions are not created well (unbalanced).
     * 
     * @param array The array to be sorted
     * @param low   The lower index of the partition to sort
     * @param high  The upper index of the partition to sort
     */
    public void quickSort(int[] array, int low, int high) {
        if (array == null || array.length <= 1 || low >= high)
            return;
        int split = partition(array, low, high);

        // Print the state of the array after every partition
        System.out.println("Array State: " + str(array));
        quickSort(array, low, split);
        quickSort(array, split + 1, high);
    }

    /**
     * Partitions an array based on two given indices
     * 
     * @param array The array to partition
     * @param first The starting index of the portion to partition
     * @param last  The ending index of the portion to partition
     * @return The index of the pivot
     */
    private int partition(int[] array, int first, int last) {
        int pivot, i, j;
        pivot = array[(first + last) / 2];

        // i should be index from l to r, j should be r to l
        i = first - 1;
        j = last + 1;

        while (true) {
            do {
                i++;
            } while (array[i] > pivot);
            do {
                j--;
            } while (array[j] < pivot);
            if (i < j) {
                swap(array, i, j);
            } else {
                return j;
            }
        }
    }

    /**
     * Swaps two elements in an array in-place
     * 
     * @param array The array to operate on
     * @param i     The first index to swap
     * @param j     The second index to swap
     */
    private static void swap(int[] array, int i, int j) {
        // Swap using a temporary variable
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    };

    /**
     * ! Question 4 - Bucket Sort
     * <p>
     * 
     * Sorts an array into descending order using the Bucket Sort algorithm. The
     * state of the buckets and the array is printed after
     * distributing elements into buckets and after each bucket is sorted and
     * combined.
     * 
     * ? Number of comparisons: On the order of O(n^2). The buckets are created and
     * ? distributed using (num * size) / (max + 1) as a comparison, which is done for
     * ? each element in the array. There is then a bubble sort operation done on the
     * ? buckets as the buckets are small and the method is easy to implement. This
     * ? algorithm is on the order of O(n^2), reducing from n - 1 to 1 through the
     * ? loops.
     * <p>
     * ? Number of Swaps: On the order of O(n^2). This is more specifically O(n x
     * ? (n/size)^2) with even distribution, as the swapping is done with a bubble
     * ? sort algorithm.
     * <p>
     * ? Number of Accesses: On the order of O(n^2). There is a linear number of
     * ? accesses made for distributing the elements, a quadratic number of accesses
     * ? for bubble sorting the buckets, and a linear number of accesses on the
     * ? buckets for recombining.
     * 
     * @param array The array to be sorted
     * @param size  The size of each bucket
     */
    public void bucketSort(int[] array, int size) {
        if (array == null || array.length <= 1 || size <= 0)
            return;

        // Find the maximum value in the array
        int max = array[0];
        for (int num : array) {
            max = num > max ? num : max;
        }

        // Create the buckets
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }

        // Add the elements to the buckets
        for (int num : array) {
            int bucketIndex = (num * size) / (max + 1);
            buckets[bucketIndex].add(num);
        }

        // Print the state of the buckets
        System.out.println("Bucket state after distribution:");
        for (int i = 0; i < size; i++) {
            System.out.println("Bucket " + i + ": " + str(buckets[i]));
        }

        // Sort the buckets in descending order
        for (LinkedList<Integer> bucket : buckets) {
            for (int i = 0; i < bucket.size() - 1; i++) {
                for (int j = 0; j < bucket.size() - i - 1; j++) {
                    if (bucket.get(j) < bucket.get(j + 1)) {
                        // Swap elements
                        swap(bucket, j, j + 1);
                    }
                }
            }
        }

        // Print the state of the buckets
        System.out.println("Bucket state after sorting:");
        for (int i = 0; i < size; i++) {
            System.out.println("Bucket " + i + ": " + str(buckets[i]));
        }

        // Recombine the buckets and print array state
        int index = 0;
        for (int i = size - 1; i > -1; i--) {
            for (int num : buckets[i])
                array[index++] = num;
            System.out.println("Combination " + (size - i) + ": " + str(array));
        }

        // Print the sorted array
        System.out.println("Bucket Sort Result: " + str(array) + "\n");
    }

    /**
     * Swaps two elements in an list
     * 
     * @param list The list to operate on
     * @param i    The first index to swap
     * @param j    The second index to swap
     */
    private static void swap(LinkedList<Integer> list, int i, int j) {
        // Swap using a temporary variable
        int temp = list.get(j);
        list.set(j, list.get(i));
        list.set(i, temp);
    };

    /**
     * Returns the string representation of an array
     * 
     * @param array The array to show
     * @return The string representation
     */
    public static String str(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(i != array.length - 1 ? ", " : "]");
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of an array
     * 
     * @param list the list to show
     * @return The string representation
     */
    public static String str(LinkedList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(i != list.size() - 1 ? " -> " : "");
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
        int[] arr = { 26, 13, 72, 3, 17, 37, 0, 17, 73, 45 };
        System.out.println("Initial Array: " + str(arr) + "\n");

        // Test Insertion Sort
        System.out.println("Begin Insertion Sort\n--------------");
        new Sort().insertionSort(arr.clone());

        // Test Merge Sort
        System.out.println("Begin Merge Sort\n--------------");
        new Sort().mergeSort(arr.clone());

        // Test Quick Sort
        System.out.println("Begin Quick Sort\n--------------");
        int[] arrCopy = arr.clone();
        new Sort().quickSort(arrCopy, 0, arr.length - 1);
        System.out.println("Quick Sort Result: " + str(arrCopy) + "\n");

        // Test Bucket Sort
        System.out.println("Begin Bucket Sort\n--------------");
        new Sort().bucketSort(arr.clone(), 3);
    }
}

/**
 * ! Analysis
 * 
 * Algorithm with Fewest Steps: Merge Sort and Quick Sort both are on the order
 * of O(nlogn) in the average case.
 * 
 * Efficiency: That being said, Merge Sort is the more efficient algorithm
 * compared to Quick Sort and the other methods implemented above. This is
 * because Quick Sort can tend to more Quadratic number of steps as there can be
 * issues that arise with improper partitioning. In the best, worse, and average
 * case, Merge Sort is overall the most efficient in terms of steps and
 * complexity.
 */