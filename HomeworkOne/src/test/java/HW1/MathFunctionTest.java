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
        assertEquals(-1, MathFunction.sqrtIterative(-2, 2, 8));
        assertEquals(-1, MathFunction.sqrtIterative(4, -3, 8));
        assertEquals(-1, MathFunction.sqrtIterative(4, 2, -8));
        assertEquals(-1, MathFunction.sqrtIterative(4, 5, 3));
        assertEquals(1, MathFunction.sqrtIterative(2, 1, 3));
        assertEquals(2, MathFunction.sqrtIterative(4, 2, 8));
        assertEquals(23, MathFunction.sqrtIterative(23 * 23, 22, 24));
    }
}
