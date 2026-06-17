package school.hei.td.dto;

import java.time.LocalDate;
import lombok.*;
import school.hei.td.entity.Format;
import school.hei.td.entity.Status;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookCopyDTO {
  private Long idBookCopy;
  private String barcode;
  private Status status;
  private Double price;
  private Format format;
  private LocalDate acquiredDate;
  private Long idBook;
}
