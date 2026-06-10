package school.hei.td.mapper;

import school.hei.td.dto.BookCopyDTO;
import school.hei.td.entity.BookCopy;

public class BookCopyMapper {

  public static BookCopyDTO toDTO(BookCopy bookCopy) {
    BookCopyDTO dto = new BookCopyDTO();
    dto.setIdBookCopy(bookCopy.getIdBookCopy());
    dto.setBarcode(bookCopy.getBarcode());
    dto.setStatus(bookCopy.getStatus());
    dto.setPrice(bookCopy.getPrice());
    dto.setFormat(bookCopy.getFormat());
    dto.setAcquiredDate(bookCopy.getAcquiredDate());

    if (bookCopy.getBook() != null) {
      dto.setIdBook(bookCopy.getBook().getIdBook());
    }

    return dto;
  }

  public static BookCopy toEntity(BookCopyDTO dto) {
    BookCopy bookCopy = new BookCopy();

    bookCopy.setBarcode(dto.getBarcode());
    bookCopy.setStatus(dto.getStatus());
    bookCopy.setPrice(dto.getPrice());
    bookCopy.setFormat(dto.getFormat());
    bookCopy.setAcquiredDate(dto.getAcquiredDate());

    return bookCopy;
  }
}
