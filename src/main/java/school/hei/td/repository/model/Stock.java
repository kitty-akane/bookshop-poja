package school.hei.td.repository.model;

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

  public void increaseStock(int qty) {
    this.quantity += qty;
  }

  public void decreaseStock(int qty) {
    if (qty > this.quantity) {
      System.out.println("Not enough stock to decrease by " + qty);
      return;
    }
    this.quantity -= qty;
  }

  public int getAvailableQuantity() {
    return quantity;
  }

  public int getId() {
    return id;
  }

  public Book getBook() {
    return book;
  }

  public Library getLibrary() {
    return library;
  }
}
