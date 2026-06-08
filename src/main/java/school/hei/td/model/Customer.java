package school.hei.td.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  private int id;
  private String name;
  private String email;
  private String phone;
  private List<Sale> purchaseHistory;

  public Customer(int id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.purchaseHistory = new ArrayList<>();
  }

  public Sale buyBook() {
    Sale sale = new Sale(purchaseHistory.size() + 1, this);
    purchaseHistory.add(sale);
    return sale;
  }

  public List<Sale> viewPurchaseHistory() {
    return purchaseHistory;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Sale> getPurchaseHistory() {
    return purchaseHistory;
  }

  public void setPurchaseHistory(List<Sale> purchaseHistory) {
    this.purchaseHistory = purchaseHistory;
  }
}
