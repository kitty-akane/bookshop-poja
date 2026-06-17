package school.hei.td.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
