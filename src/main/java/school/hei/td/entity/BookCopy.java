package school.hei.td.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "bookcopy")
@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_copy_id", nullable = false, unique = true)
    private Long idBookCopy;

    @Column(name = "barcode", nullable = false, unique = true)
    private String barcode;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    private Format format;

    @Column(name = "acquired_date", nullable = false)
    private LocalDate acquiredDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
