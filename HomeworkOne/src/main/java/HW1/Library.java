package HW1;

import java.util.ArrayList;

/**
 * A class to work with and manipulate Book Objects.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class Library {
    // Needed attribute
    private ArrayList<Book> books;

    // Boilerplate
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    /**
     * Adds a book to the library (array list)
     * 
     * @param book The Book to be added
     */
    public void addBook(Book book) {

    }

    /**
     * Removes a book from the library based off its isbn.
     * 
     * @param isbn The String isbn value of the book
     * @return A boolean value indicated if removal was successful
     */
    public boolean removeBook(String isbn) {
        return true;
    }

    /**
     * Prints all of the books in teh library with their details.
     */
    public void printBooks() {
        
    }
}
