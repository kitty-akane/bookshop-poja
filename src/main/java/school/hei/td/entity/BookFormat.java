package school.hei.td.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_format")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatType formatType;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    public enum FormatType {
        SMALL,
        MEDIUM,
        LARGE
    }
}
