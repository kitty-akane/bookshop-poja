package school.hei.td.repository.model;

import java.util.ArrayList;
import java.util.List;

public class Author {

  private int id;
  private String name;
  private String nationality;
  private List<Book> books;

  public Author(int id, String name, String nationality) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
    this.books = new ArrayList<>();
  }

  // Returns all books written by this author
  public List<Book> getBooks() {
    return books;
  }

  // Adds a book to this author's list
  public void addBook(Book book) {
    books.add(book);
  }

  // Updates the author's name and nationality
  public void updateInfo(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getNationality() {
    return nationality;
  }
}
