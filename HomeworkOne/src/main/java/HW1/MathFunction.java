package HW1;

/**
 * A class containing recursive variations for given methods.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class MathFunction {
    /**
     * Calculates the value of 2 * x^x based on the value of x.
     * 
     * @param x the value to be used
     * @return the result
     */
    public static int multiply(int x) {
        int y = 2;
        for (int i = 0; i < x; i++) {
            y *= x;
        }
        return y;
    }

    /**
     * Calculates the value of 2 * x^x based on the value of x.
     * ! Acts recursively but returns same value as the 'multiply'
     * ! method
     * 
     * @param x the value to be used
     * @return the result
     */
    public static int multiplyRecursive(int x) {
        return 0;
    }

    /**
     * Returns the square root of a value that is between two bounds.
     * 
     * @param x The value to take the square root of
     * @param low The lower bound for the square root
     * @param high The upper bound for the square root
     * @return The value (between bounds) for teh square root
     */
    public static int sqrt(int x, int low, int high) {
        if (low > high || x < 0 || low < 0 || high < 0)
            return -1;
        int p = low + (high - low) / 2;
        if (p * p == x)
            return p;
        else if (p * p > x)
            return sqrt(x, low, p - 1);
        else
            return sqrt(x, p + 1, high);
    }

    /**
     * Returns the square root of a value that is between two bounds.
     * ! Acts recursively as well, but iterates to give same result as
     * ! the 'sqrt' method
     * 
     * @param x The value to take the square root of
     * @param low The lower bound for the square root
     * @param high The upper bound for the square root
     * @return The value (between bounds) for teh square root
     */
    public static int sqrtIterative(int x, int low, int high) {
        return 0;
    }
}
