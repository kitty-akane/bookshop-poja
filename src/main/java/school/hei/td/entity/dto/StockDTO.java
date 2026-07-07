package school.hei.td.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class StockDTO {
  private int totalQuantity;
  private int availableQuantity;
  private int reservedQuantity;
  private int soldQuantity;
}
