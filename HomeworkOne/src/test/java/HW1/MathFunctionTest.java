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
        for (int i = -20; i < 20; i++)
            assertEquals(MathFunction.multiply(i), MathFunction.multiplyRecursive(i));
    }

    @Test
    // Test the sqrt methods and ensure consistency
    public void sqrtTest() {
        for (int i = 0; i < 100; i++)
            assertEquals(MathFunction.sqrt(i, (int) Math.sqrt(i) - 1, (int) Math.sqrt(i) + 1),
                    MathFunction.sqrtIterative(i, (int) Math.sqrt(i) - 1, (int) Math.sqrt(i) + 1));
    }
}
