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
    private ArrayList<Book> books = new ArrayList<>();

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
        this.getBooks().add(book);
    }

    /**
     * Removes a book from the library based off its isbn.
     * 
     * @param isbn The String isbn value of the book
     * @return A boolean value indicated if removal was successful
     */
    public boolean removeBook(String isbn) {
        ArrayList<Book> library = this.getBooks();
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getISBN().equals(isbn)) {
                library.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all of the books in the library with their details.
     */
    public void printBooks() {
        // Iterates through the library and prints each book's details
        for (Book book : this.getBooks()) {
            System.out.println(book.getDetails());
        }
    }
}
