package school.hei.td.repository.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Arrival {

  private int id;
  private Date arrivalDate;
  private String supplierName;
  private List<BookCopy> copies;

  public Arrival(int id, Date arrivalDate, String supplierName) {
    this.id = id;
    this.arrivalDate = arrivalDate;
    this.supplierName = supplierName;
    this.copies = new ArrayList<>();
  }

  public void addBookCopy(BookCopy copy) {
    copies.add(copy);
  }

  public int getTotalCopies() {
    return copies.size();
  }

  public int getId() {
    return id;
  }

  public Date getArrivalDate() {
    return arrivalDate;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public List<BookCopy> getCopies() {
    return copies;
  }
}
