package school.hei.td.repository.model;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private int id;
    private String name;
    private String address;
    private List<Stock> stocks;

    public Library(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stocks = new ArrayList<>();
    }

    public void addBook(Book book) {
        Stock newStock = new Stock(stocks.size() + 1, book, this, 0);
        stocks.add(newStock);
    }

    public void removeBook(Book book) {
        stocks.removeIf(stock -> stock.getBook().equals(book));
    }

    public List<Book> searchBook(String title) {
        List<Book> results = new ArrayList<>();
        for (Stock stock : stocks) {
            if (stock.getBook().getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(stock.getBook());
            }
        }
        return results;
    }

    public Stock getStock(Book book) {
        for (Stock stock : stocks) {
            if (stock.getBook().equals(book)) {
                return stock;
            }
        }
        return null;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public List<Stock> getStocks() { return stocks; }
}
