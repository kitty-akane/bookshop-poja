package school.hei.td.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@Table(name = "stock")
public class Stock {
  @Id private Long idStock;

  @ManyToOne
  @JoinColumn(name = "bookcopy_id")
  private BookCopy bookCopy;

  private int totalQuantity;
  private int availableQuantity;
  private int borrowerQuantity;
  private int lostOrDamagedQuantity;
  private int reservedQuantity;
}
