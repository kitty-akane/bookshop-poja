package school.hei.td.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovementDTO {
  private Long id;
  private Long bookFormatId;
  private String type;
  private Integer quantity;
  private LocalDateTime movementDate;
  private String reason;
}
