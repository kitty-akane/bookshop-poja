package school.hei.td.repository.model;

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

  public List<Author> getAuthors() {
    return authors;
  }

  public Genre getGenre() {
    return genre;
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

  public void updateInfo(String title, String isbn, double price) {
    this.title = title;
    this.isbn = isbn;
    this.price = price;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void addAuthor(Author author) {
    authors.add(author);
  }

  public void addCopy(BookCopy copy) {
    copies.add(copy);
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getIsbn() {
    return isbn;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public double getPrice() {
    return price;
  }

  public List<BookCopy> getCopies() {
    return copies;
  }
}
