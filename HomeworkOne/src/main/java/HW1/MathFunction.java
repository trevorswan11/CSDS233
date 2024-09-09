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
        // Base case: 2 * 1^1 = 2, negative yields non-integer
        if (x == 1 || x < 0) {
            return 2;
        }

        // Recursive case: 2 * x^x
        return 2 * recursivePower(x, x);
    }

    private static int recursivePower(int base, int exponent) {
        // Base case: base^0 = 1 for all base
        if (exponent == 0) {
            return 1;
        }

        // Recursive case: base ^ exponent = base * base ^ (exponent - 1)
        return base * recursivePower(base, exponent - 1);
    }

    /**
     * Returns the square root of a value that is between two bounds.
     * 
     * @param x The value to take the square root of 1
     * @param low The lower bound for the square root
     * @param high The upper bound for the square root
     * @return The value (between bounds) for the square root
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
     * @return The value (between bounds) for the square root
     */
    public static int sqrtIterative(int x, int low, int high) {
        if (low > high || x < 0 || low < 0 || high < 0) {
            return -1;
        }

        // Start Binary search
        while (low <= high) {
            // Calculate the middle value and its square
            int mid = (low + high) / 2;
            int midSquared = mid * mid;

            // Compare the squared value to the inputted one
            if (midSquared == x) {
                return mid;
            }
            else if (midSquared > x) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        // Compare distance of squared value to input
        int distLow = Math.abs(low * low - x);
        int distHigh = Math.abs(high * high - x);
        
        // Return the closest value
        if (distHigh > distLow) {
            return low;
        } 
        else {
            return high;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(sqrtIterative(2, 2, 8));
    }
}
