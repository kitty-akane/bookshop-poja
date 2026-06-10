package school.hei.td.dto;

import lombok.*;
import school.hei.td.entity.Format;
import school.hei.td.entity.Status;

import java.time.LocalDate;

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
