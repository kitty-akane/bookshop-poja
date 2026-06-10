package school.hei.td.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "book")
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "book_id", nullable = false, unique = true)
  private Long idBook;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "isbn", nullable = false, unique = true)
  private String isbn;

  @Column(name = "publication_date", nullable = false)
  private LocalDate publicationDate;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "publisher", nullable = false)
  private String publisher;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "category", nullable = false)
  private String category;

  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @OneToMany(mappedBy = "book")
  private List<BookCopy> bookCopies = new ArrayList<>();
}
