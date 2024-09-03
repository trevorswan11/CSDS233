package Recursion;

import java.util.Arrays;

public class ArrayManipulation {
    static void myReverse(int[] arr, int left, int right) {
        // Base case
        if (left >= right)
            return;

        // Swap the "ends"
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        // Reverse the "middle"
        myReverse(arr, left + 1, right - 1);
    }
    static void reverse(int[] arr) {
        myReverse(arr, 0, arr.length - 1);
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        reverse(arr);
        System.err.println(Arrays.toString(arr));
    }
}
