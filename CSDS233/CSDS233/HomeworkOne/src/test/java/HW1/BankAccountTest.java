package HW1;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A test class to test bank account methods. 
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BankAccountTest {
    // Sample account with a starting balance of $100
    BankAccount sampleAccount = new BankAccount("1234567890", 100);

    @Test
    // Test the general methods 
    public void bankTests() {
        // Test the constructor
        assertEquals("1234567890", sampleAccount.getAccountNumber());
        assertEquals(100, sampleAccount.getBalance(), 0);

        // Test the withdrawal method
        assertTrue(sampleAccount.withdraw(100));
        assertEquals(0, sampleAccount.getBalance(), 0);
        assertFalse(sampleAccount.withdraw(10));
        assertTrue(sampleAccount.withdraw(0));
        assertEquals(0, sampleAccount.getBalance(), 0);

        // Test the deposit method
        double balance = 0;
        sampleAccount.deposit(150.35);
        assertEquals(balance += 150.35, sampleAccount.getBalance(), 0);

        // Add random double values (not valid monetary amounts)
        double amount;
        for (int i = 0; i < 100; i++) {
            amount = Math.random() * (150 - 100) + 150;
            sampleAccount.deposit(amount);
            assertEquals(balance += amount, sampleAccount.getBalance(), 0);

            // Withdraw if the index is divisible by 3
            if (i % 3 == 0) {
                amount = Math.random() * (150 - 100) + 150;
                sampleAccount.withdraw(amount);
                assertEquals(balance -= amount, sampleAccount.getBalance(), 0);
            }
        }

        // Print the final account balance as requested
        System.out.println("The final balance of the account is $" + balance);
    }
}
