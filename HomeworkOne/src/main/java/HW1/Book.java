package HW1;

/**
 * A class to represent a book with its details.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class Book {
    // Needed attributes
    private String title;
    private String author;
    private String isbn;

    // Boilerplate
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return this.isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Creates a new book with all attributes given.
     * 
     * @param title The book's title
     * @param author The book's author
     * @param isbn The book's isbn
     */
    public Book(String title, String author, String isbn) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setISBN(isbn);
    }

    /**
     * Returns a string with the book's details.
     * 
     * @return A formatted String describing the book.
     */
    public String getDetails() {
        return String.format("Book Details >> Title: \"%s\", by %s with isbn: %s",
                title, author, isbn);
    }
}
