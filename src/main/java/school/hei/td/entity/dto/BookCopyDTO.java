package school.hei.td.entity.dto;

import java.time.LocalDate;
import lombok.*;
import school.hei.td.enums.Format;
import school.hei.td.enums.Status;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class BookCopyDTO {
  private Long idBookCopy;
  private String barcode;
  private Status status;
  private Double price;
  private Format format;
  private LocalDate acquiredDate;
  private BookDTO bookDTO;
}
