package school.hei.td.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFormatDTO {
  private Long id;
  private Long bookId;
  private String formatType;
  private Double price;
  private Integer stock;
}
