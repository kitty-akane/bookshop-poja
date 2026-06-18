package school.hei.td.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.BookCopyDTO;

@Component
@AllArgsConstructor
public class BookCopyMapper {
  private final BookMapper bookMapper;

  public BookCopyDTO toDTO(BookCopy bookCopy) {
    return BookCopyDTO.builder()
        .idBookCopy(bookCopy.getIdBookCopy())
        .barcode(bookCopy.getBarcode())
        .status(bookCopy.getStatus())
        .price(bookCopy.getPrice())
        .format(bookCopy.getFormat())
        .acquiredDate(bookCopy.getAcquiredDate())
        .bookDTO(bookMapper.toDTO(bookCopy.getBook()))
        .build();
  }

  public BookCopy toEntity(BookCopyDTO dto) {
    return BookCopy.builder()
        .idBookCopy(dto.getIdBookCopy())
        .barcode(dto.getBarcode())
        .status(dto.getStatus())
        .price(dto.getPrice())
        .format(dto.getFormat())
        .acquiredDate(dto.getAcquiredDate())
        .book(bookMapper.toEntity(dto.getBookDTO()))
        .build();
  }
}
