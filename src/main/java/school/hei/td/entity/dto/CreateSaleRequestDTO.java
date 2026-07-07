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
public class CreateSaleRequestDTO {
  private Long bookCopyId;
  private LocalDate saleDate;
}
