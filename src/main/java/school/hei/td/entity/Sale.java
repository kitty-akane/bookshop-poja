package school.hei.td.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "sale")
@Entity
@Builder
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "sale_id", nullable = false, unique = true)
  private Long idSale;

  @OneToOne
  @JoinColumn(name = "book_copy_id", nullable = false, unique = true)
  private BookCopy bookCopy;

  @Column(name = "sale_date", nullable = false)
  private LocalDate saleDate;

  @Column(name = "amount", nullable = false)
  private Double amount;
}
