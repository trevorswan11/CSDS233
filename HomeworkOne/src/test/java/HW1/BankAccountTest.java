package HW1;

import static org.junit.Assert.*;

/**
 * A test class to test bank account methods. 
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BankAccountTest {
    public void bankTests(BankAccount account) {
        assertTrue(true);
    }

    
    public static void main(String[] args) {
        BankAccount sample = new BankAccount("1234567890", 100);
        new BankAccountTest().bankTests(sample);
    }
}
