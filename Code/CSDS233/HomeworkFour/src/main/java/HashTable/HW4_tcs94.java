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
    private Entry[] ht;

    /**
     * Creates a new hash table with a specified size. The table is empty upon
     * initialization. The table is inaccessible, and all values being as null.
     * 
     * @param size the int size of the hash table to create
     */
    public HW4_tcs94(int size) {
        this.ht = new Entry[size];
    }

    /**
     * Inserts the given key into the hash table using linear probing for collision
     * resolution. The state of the table is printed to the console after each
     * insertion.
     * 
     * @param key the desired int key to input into the table
     * @throws ProbingError if the insertion was unsuccessful
     */
    public void linearProbingInsert(int key) throws ProbingError {
        // Get the starting index by taking key in modulo
        int startingIndex = key % this.ht.length;

        // Try to find an open position
        int position = startingIndex;
        int i = 0;
        while (this.ht[position] != null && !this.ht[position].removed) {
            i++;
            position = (startingIndex + i) % this.ht.length;
            // Throw an error if the table has been fully cycled through
            if (i >= this.ht.length)
                throw new ProbingError("Insertion Failed. Table is full.");
        }

        // Insert the key at the found index and print the table
        this.ht[position] = new Entry(key);
        this.printTable();
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
     * @throws ProbingError if the insertion was unsuccessful
     */
    public void doubleHashingInsert(int key, int prime) throws ProbingError {
        /**
         * A nested local class used to create each of the two hash functions.
         * 
         * @author Trevor Swan
         * @version CSDS233 - Fall 2024
         */
        final class Hasher {
            // Upon instantiation, the size should be found
            private int size = HW4_tcs94.this.ht.length;

            /**
             * The first hash function to use in hopes of finding an open spot.
             * 
             * @return The int index to start looking for an open spot
             */
            public int h1() {
                return key % size;
            }

            /**
             * The second hash function to act as a step size for collision resolution.
             * 
             * @return The int step size to use for probing
             */
            public int h2() {
                int h2 = prime - (key % size);
                return h2 == 0 ? 1 : h2;
            }
        }

        // Create a new hasher for the table and perform the hash functions
        Hasher hasher = new Hasher();
        int startingIndex = hasher.h1();
        int stepSize = hasher.h2();

        // Try to find an open location in the hash table
        int i = 0;
        int position = startingIndex + i * stepSize;
        while (this.ht[position] != null && !this.ht[position].removed) {
            i++;
            position = (startingIndex + i * stepSize) % this.ht.length;
            // Throw an error if the table has been fully cycled through twice
            if (i >= 2 * this.ht.length)
                throw new ProbingError("Insertion Failed. Table may be full or consider using a prime table size.");
        }

        // Insert the key at the found index and print the table
        this.ht[position] = new Entry(key);
        this.printTable();
    }

    /**
     * Prints the current state of the hash table. The table is printed as
     * comma-separated list of elements, with null being printed in place of empty
     * slots.
     */
    public void printTable() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < ht.length; i++) {
            sb.append(ht[i] == null ? "null" : (ht[i].removed ? "null" : ht[i].key))
                    .append((i != ht.length - 1) ? ", " : "]");
        }
        System.out.println(sb.toString());
    }

    /**
     * A class to store the entries into the hash table. It contains a key and
     * removed flag, but could be expanded to use a generic type for more general
     * applications.
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    private class Entry {
        private int key;
        // private T data;
        private boolean removed = false;

        /**
         * Creates a new entry with a given key. The removed flag defaults to false;
         * 
         * @param key the integer key for the table entry
         */
        public Entry(int key) {
            this.key = key;
        }
    }
    /**
     * A custom exception to throw if the probing exceeds a certain limit.
     * 
     * @author Trevor Swan
     * @version CSDS233 - Fall 2024
     */
    public class ProbingError extends Exception {
        /**
         * Creates a new throwable based on a provided message. Will default to
         * "Insertion unsuccessful. Hash table may be full."
         * 
         * @param args A variable size String array for the message, only args[0] is
         *             considered
         */
        public ProbingError(String... args) {
            super(args.length == 0 ? "Insertion unsuccessful. Hash table may be full." : args[0]);
        }
    }
}