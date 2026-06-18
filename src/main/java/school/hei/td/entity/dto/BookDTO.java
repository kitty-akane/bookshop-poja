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
}
