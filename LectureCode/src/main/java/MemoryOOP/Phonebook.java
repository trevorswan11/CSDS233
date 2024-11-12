package MemoryOOP;

import java.util.LinkedList;

public class Phonebook {
    /**
     * Add and remove methods for an Unsorted LinkedList which is used for a phonebook
     */
    public class UnsortedLinkedList {
        private LinkedList<String> names = new LinkedList<>();
        private LinkedList<Integer> nums = new LinkedList<>();

        public void add(String name, int number) {
            names.add(name);
            nums.add(number);
        }

        public void remove(String name) {
            if (names.contains(name)) {
                names.remove(name);
                nums.remove(names.indexOf(name));
            }
        }

        public void remove(int num) {
            if (nums.contains(num)) {
                nums.remove(num);
                names.remove(nums.indexOf(num));
            }
        }
    }

    public class SortedArray {
        public String search(int number) {
            return null;
        }
    }
    public static void main(String[] args) {
        System.err.println("hello world");
    }
}
