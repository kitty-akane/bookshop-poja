package school.hei.td.repository.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {

  private int id;
  private Date date;
  private double totalAmount;
  private List<BookCopy> items;
  private Customer customer;

  public Sale(int id, Customer customer) {
    this.id = id;
    this.date = new Date();
    this.customer = customer;
    this.items = new ArrayList<>();
    this.totalAmount = 0;
  }

  public double calculateTotal() {
    double total = 0;
    for (BookCopy copy : items) {
      total += copy.getBook().getPrice();
    }
    this.totalAmount = total;
    return total;
  }

  public void addBookCopy(BookCopy copy) {
    items.add(copy);
    copy.markAsSold();
    calculateTotal();
  }

  public void removeBookCopy(BookCopy copy) {
    items.remove(copy);
    copy.markAsAvailable();
    calculateTotal();
  }

  public List<BookCopy> getItems() {
    return items;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public Customer getCustomer() {
    return customer;
  }
}
