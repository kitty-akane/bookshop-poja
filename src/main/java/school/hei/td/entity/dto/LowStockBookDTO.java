package school.hei.td.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class LowStockBookDTO {
  private BookDTO bookDTO;
  private int availableQuantity;
}
