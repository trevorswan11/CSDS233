package Sorting;

public class MySorter {
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int k = min(arr, i);
            swap(arr, i, k);
        }
    }

    private static int min(int[] arr, int start) {
        int min = start;
        for (int i = start + 1; i < arr.length; i++)
            min = arr[i] < arr[min] ? i : min;
        return min;
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j, k = arr[i];
            for (j = i; j > 0 && k < arr[j - 1]; j--)
                arr[j] = arr[j - 1];
            arr[j] = k;
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--)
            for (int j = 0; j < i; j++)
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
    }

    public static void mergeSort(int[] arr) {
        myMergeSort(arr, 0, arr.length - 1);
    }

    private static void myMergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        myMergeSort(arr, left, mid);
        myMergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1;
        while (i < mid + 1 && j < right + 1) {
            if (arr[i] <= arr[j]) i++;
            else {
                int temp = arr[j], k = j;
                while (k > i)
                    arr[k] = arr[k-- - 1];
                arr[i] = temp;
                i++; mid++; j++;
            }
        }
    }

    public static void quickSort(int[] arr) {
        myQuickSort(arr, 0, arr.length - 1);
    }

    private static void myQuickSort(int[] arr, int first, int last) {
        if (first >= last) return;
        int split = partition(arr, first, last);
        myQuickSort(arr, first, split);
        myQuickSort(arr, split + 1, last);
    }

    private static int partition(int[] arr, int first, int last) {
        int pivot = arr[(first + last) / 2];
        int i = first - 1, j = last + 1;
        while (true) {
            do i++; while (arr[i] < pivot);
            do j--; while (arr[j] > pivot);
            if (i < j)
                swap(arr, i, j);
            else
                return j;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        System.out.println(str(arr));
    }

    public static void print(int[] arr, String type) {
        System.out.println(type + ": " + str(arr));
    }

    public static void print(int[][] arr, String[] types) {
        for (int i = 0; i < arr.length; i++)
            print(arr[i], types[i]);
    }

    private static String str(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(i != array.length - 1 ? ", " : "]");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] arr = new int[5][];
        for (int i = 0; i < 5; i++)
            arr[i] = new int[] { 100, -3, 2, 3, 4, 5, 6, 7, 1, 12, 34, 65, 7, 2, 0 };
        String[] types = { 
                "Selection Sort",
                "Insertion Sort",
                "Bubble Sort",
                "Merge Sort",
                "Quick Sort"
        };
        selectionSort(arr[0]);
        insertionSort(arr[1]);
        bubbleSort(arr[2]);
        mergeSort(arr[3]);
        quickSort(arr[4]);
        print(arr, types);
    }
}
