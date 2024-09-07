package HW1;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A test class to ensure MathFunction consistency.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class MathFunctionTest {
    @Test
    // Test the multiply methods and ensure consistency
    public void multiplyTest() {
        for (int i = -20; i < 100; i++)
            assertEquals(MathFunction.multiply(i), MathFunction.multiplyRecursive(i));
    }

    @Test
    // Test the sqrt methods and ensure consistency
    public void sqrtTest() {
        // Ensure methods are consistent
        for (int i = 0; i < 100; i++) {
            int lower = (int) Math.sqrt(i) - 1;
            int upper = (int) Math.sqrt(i) + 1;
            assertEquals(MathFunction.sqrt(i, lower, upper), MathFunction.sqrtIterative(i, lower, upper));
        }
    }
}
