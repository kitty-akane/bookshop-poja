package school.hei.td.entity.dto;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class SaleDTO {
  private Long idSale;
  private BookCopyDTO bookCopyDTO;
  private LocalDate saleDate;
  private Double amount;
}
