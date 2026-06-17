package school.hei.td.repository.model;
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

    public void updateInfo(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
