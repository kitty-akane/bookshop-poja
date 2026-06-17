package school.hei.td.repository.model;
import java.util.ArrayList;
import java.util.List;

public class Genre {

    private int id;
    private String name;
    private String description;
    private List<Book> books;

    public Genre(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}
