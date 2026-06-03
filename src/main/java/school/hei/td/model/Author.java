package school.hei.td.model;

import java.util.List;

public class Author {

    private int id;
    private String name;
    private String nationality;
    private List<Book> books;

    public Author(int id, String name, String nationality, List<Book> books) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
