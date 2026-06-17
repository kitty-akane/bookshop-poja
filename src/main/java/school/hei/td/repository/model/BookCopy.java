package school.hei.td.repository.model;

public class BookCopy {

    public static final String AVAILABLE = "AVAILABLE";
    public static final String SOLD      = "SOLD";
    public static final String DAMAGED   = "DAMAGED";

    private int id;
    private String barcode;
    private String status;
    private Book book;

    public BookCopy(int id, String barcode, Book book) {
        this.id = id;
        this.barcode = barcode;
        this.book = book;
        this.status = AVAILABLE; 
    }

    public void markAsSold() {
        this.status = SOLD;
    }

    public void markAsAvailable() {
        this.status = AVAILABLE;
    }

    public boolean isAvailable() {
        return this.status.equals(AVAILABLE);
    }
    public int getId() { return id; }
    public String getBarcode() { return barcode; }
    public String getStatus() { return status; }
    public Book getBook() { return book; }
}
