package school.hei.td.model;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int id;
    private String title;
    private String isbn;
    private int publicationYear;
    private double price;
    private List<Author> authors;
    private Genre genre;
    private List<BookCopy> copies;

    public Book(int id, String title, String isbn, int publicationYear, double price) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.authors = new ArrayList<>();
        this.copies = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public int getAvailableCopies() {
        int count = 0;
        for (BookCopy copy : copies) {
            if (copy.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int calculateStock() {
        return copies.size();
    }
}
