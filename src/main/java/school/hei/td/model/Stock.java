package school.hei.td.model;

import org.apache.tomcat.jni.Library;

public class Stock {
    private int id;
    private int quantity;
    private Book book;
    private Library library;

    public Stock(int id, Book book, Library library, int initialQuantity) {
        this.id = id;
        this.book = book;
        this.library = library;
        this.quantity = initialQuantity;
    }
    public void increaseStock(int quantity) {
        this.quantity += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity > this.quantity) {
            throw new IllegalArgumentException("Not enough stock available");
        }
        this.quantity -= quantity;
    }

    public int getAvailableQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
