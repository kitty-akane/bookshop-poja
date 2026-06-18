package school.hei.td.mapper;

import org.springframework.stereotype.Component;
import school.hei.td.entity.Book;
import school.hei.td.entity.dto.BookDTO;

@Component
public class BookMapper {

  public BookDTO toDTO(Book book) {
    return BookDTO.builder()
        .idBook(book.getIdBook())
        .title(book.getTitle())
        .isbn(book.getIsbn())
        .publicationDate(book.getPublicationDate())
        .author(book.getAuthor())
        .publisher(book.getPublisher())
        .language(book.getLanguage())
        .category(book.getCategory())
        .description(book.getDescription())
        .build();
  }

  public Book toEntity(BookDTO dto) {
    return Book.builder()
        .idBook(dto.getIdBook())
        .title(dto.getTitle())
        .isbn(dto.getIsbn())
        .publicationDate(dto.getPublicationDate())
        .author(dto.getAuthor())
        .publisher(dto.getPublisher())
        .language(dto.getLanguage())
        .category(dto.getCategory())
        .description(dto.getDescription())
        .build();
  }
}
