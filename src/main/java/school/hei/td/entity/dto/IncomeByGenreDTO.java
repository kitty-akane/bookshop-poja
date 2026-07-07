package school.hei.td.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class IncomeByGenreDTO {
  private String genre;
  private Double totalIncome;
  private Long salesCount;
}
