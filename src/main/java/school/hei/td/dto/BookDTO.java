package school.hei.td.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookDTO {
  private Long idBook;
  private String title;
  private String isbn;
  private LocalDate publicationDate;
  private String author;
  private String publisher;
  private String language;
  private String category;
  private String description;
  private List<BookCopyDTO> bookCopyDTO;
}
