package HW1;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * A test class to test the methods of the book and library classes.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class BookLibraryTest {
    // Helper method to create 100 books
    private static Book[] createBooks() {
        Book[] books = new Book[100];
        for (int i = 0; i < 100; i++) {
            books[i] = new Book("Book #" + i, "TS", Integer.toString(i));
        }
        return books;
    }

    // Helper method to generate details
    private static String myDetails(Book book) {
        return String.format("Book Details >> Title: \"%s\", by %s with isbn: %s",
                book.getTitle(), book.getAuthor(), book.getISBN());
    }

    @Test
    // Test the details method to ensure proper printing
    public void booksDetailsTest() {
        Library testLibrary = new Library();
        Book[] books = createBooks();
        testLibrary.addBook(books[0]);

        // Ensure detail retrieval is correct
        assertEquals(myDetails(testLibrary.getBooks().get(0)),
                testLibrary.getBooks().get(0).getDetails());
    }

    @Test
    // Test according to the assignments guidelines
    public void libraryTest() {
        // Create the test object and generate model books
        Library testLibrary = new Library();
        Book[] books = createBooks();

        // Add the rest of the books and print them out
        for (Book book : books) {
            testLibrary.addBook(book);
        }
        
        testLibrary.printBooks();

        // Try some removals, then reprint
        assertFalse(testLibrary.removeBook("1000"));
        assertTrue(testLibrary.removeBook("2"));
        assertTrue(testLibrary.removeBook("50"));
        testLibrary.printBooks();
    }
}
