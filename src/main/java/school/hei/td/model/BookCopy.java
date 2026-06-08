package school.hei.td.model;

public class BookCopy {

  private int id;
  private Book book;
  private String barcode;
  private Status status;

  public BookCopy(int id, Book book, String barcode, Status status) {
    this.id = id;
    this.book = book;
    this.barcode = barcode;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public Book getBook() {
    return book;
  }

  public String getBarcode() {
    return barcode;
  }

  public Status getStatus() {
    return status;
  }

  public void isSold() {
    this.status = Status.SOLD;
  }

  public void isBorrowed() {
    this.status = Status.BORROWED;
  }

  public void isReserved() {
    this.status = Status.RESERVED;
  }

  public boolean isAvailable() {
    return this.status == Status.AVAILABLE;
  }
}
