package school.hei.td.mapper;

import java.util.List;
import school.hei.td.dto.BookCopyDTO;
import school.hei.td.dto.BookDTO;
import school.hei.td.entity.Book;

public class BookMapper {

  public static BookDTO toDTO(Book book) {
    BookDTO dto = new BookDTO();
    dto.setIdBook(book.getIdBook());
    dto.setTitle(book.getTitle());
    dto.setIsbn(book.getIsbn());
    dto.setPublicationDate(book.getPublicationDate());
    dto.setAuthor(book.getAuthor());
    dto.setPublisher(book.getPublisher());
    dto.setLanguage(book.getLanguage());
    dto.setCategory(book.getCategory());
    dto.setDescription(book.getDescription());

    List<BookCopyDTO> copies = book.getBookCopies().stream().map(BookCopyMapper::toDTO).toList();
    dto.setBookCopyDTO(copies);
    return dto;
  }

  public static Book toEntity(BookDTO dto) {
    if (dto == null) {
      return null;
    }

    Book book = new Book();

    book.setIdBook(dto.getIdBook());
    book.setTitle(dto.getTitle());
    book.setIsbn(dto.getIsbn());
    book.setPublicationDate(dto.getPublicationDate());
    book.setAuthor(dto.getAuthor());
    book.setPublisher(dto.getPublisher());
    book.setLanguage(dto.getLanguage());
    book.setCategory(dto.getCategory());
    book.setDescription(dto.getDescription());

    return book;
  }
}
