package school.hei.td.conf;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.hei.td.entity.Book;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Format;
import school.hei.td.entity.Status;
import school.hei.td.entity.dto.BookCopyDTO;
import school.hei.td.mapper.BookCopyMapper;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.repository.BookRepository;
import school.hei.td.service.BookCopyService;

@ExtendWith(MockitoExtension.class)
public class BookCopyServiceTest {

  @Mock private BookCopyRepository repository;

  @Mock private BookCopyMapper bookCopyMapper;

  @Mock private BookRepository bookRepository;

  @InjectMocks private BookCopyService bookCopyService;

  // SAMPLE DATA
  private static final Long EXISTING_ID = 15214632L;
  private static final Long NON_EXISTING_ID = 99873541L;
  private static final Long BOOK_ID = 48291073L;

  private Book sampleBook() {
    return Book.builder()
        .idBook(BOOK_ID)
        .title("Harry Potter")
        .isbn("some-isbn")
        .author("J.K. Rowling")
        .publisher("Gallimard")
        .language("French")
        .category("Fantasy")
        .description("A wizard story")
        .publicationDate(LocalDate.of(1997, 6, 26))
        .build();
  }

  private BookCopy sampleBookCopy() {
    return BookCopy.builder()
        .idBookCopy(EXISTING_ID)
        .barcode("BC-001")
        .status(Status.AVAILABLE)
        .price(25.0)
        .format(Format.SOFT_COVER)
        .acquiredDate(LocalDate.of(2023, 1, 15))
        .book(sampleBook())
        .build();
  }

  private BookCopyDTO sampleDTO() {
    return BookCopyDTO.builder()
        .idBookCopy(EXISTING_ID)
        .barcode("BC-001")
        .status(Status.AVAILABLE)
        .price(25.0)
        .format(Format.SOFT_COVER)
        .acquiredDate(LocalDate.of(2023, 1, 15))
        .build();
  }

  @Test
  void testCreate() {

    var dto = sampleDTO();
    var entity = sampleBookCopy();

    when(bookCopyMapper.toEntity(dto)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(bookCopyMapper.toDTO(entity)).thenReturn(dto);

    var result = bookCopyService.create(dto);

    assertNotNull(result);
    assertEquals("BC-001", result.getBarcode());
    verify(repository, times(1)).save(entity);
  }

  @Test
  void testDeleteById() {

    when(repository.existsById(EXISTING_ID)).thenReturn(true);

    bookCopyService.deleteById(EXISTING_ID);

    verify(repository, times(1)).deleteById(EXISTING_ID);

    when(repository.existsById(NON_EXISTING_ID)).thenReturn(false);
    assertThrows(Exception.class, () -> bookCopyService.deleteById(NON_EXISTING_ID));
  }

  @Test
  void testGetAllBookCopies() {
    // GIVEN
    var copy1 = sampleBookCopy();
    var copy2 =
        BookCopy.builder()
            .idBookCopy(73829104L)
            .barcode("BC-002")
            .status(Status.BORROWED)
            .price(30.0)
            .format(Format.HARD_COVER)
            .acquiredDate(LocalDate.of(2022, 5, 10))
            .book(sampleBook())
            .build();

    when(repository.findAll()).thenReturn(List.of(copy1, copy2));
    when(bookCopyMapper.toDTO(copy1)).thenReturn(sampleDTO());
    when(bookCopyMapper.toDTO(copy2))
        .thenReturn(
            BookCopyDTO.builder()
                .idBookCopy(73829104L)
                .barcode("BC-002")
                .status(Status.BORROWED)
                .price(30.0)
                .format(Format.HARD_COVER)
                .acquiredDate(LocalDate.of(2022, 5, 10))
                .build());

    var result = bookCopyService.getAllBookCopies();

    assertEquals(2, result.size());
    assertEquals("BC-001", result.get(0).getBarcode());
    assertEquals("BC-002", result.get(1).getBarcode());
  }

  @Test
  void testGetById() {

    var entity = sampleBookCopy();
    var dto = sampleDTO();

    when(repository.findById(EXISTING_ID)).thenReturn(Optional.of(entity));
    when(bookCopyMapper.toDTO(entity)).thenReturn(dto);

    var result = bookCopyService.getById(EXISTING_ID);

    assertEquals(EXISTING_ID, result.getIdBookCopy());
    assertEquals("BC-001", result.getBarcode());

    when(repository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());
    var exception =
        assertThrows(RuntimeException.class, () -> bookCopyService.getById(NON_EXISTING_ID));
    assertEquals("BookCopy not found: " + NON_EXISTING_ID, exception.getMessage());
  }

  @Test
  void testUpdateBookCopy() {

    var existing = sampleBookCopy();
    var dto =
        BookCopyDTO.builder()
            .barcode("BC-UPDATED")
            .status(Status.BORROWED)
            .price(30.0)
            .format(Format.HARD_COVER)
            .acquiredDate(LocalDate.of(2023, 1, 15))
            .build();
    var expectedDTO =
        BookCopyDTO.builder()
            .idBookCopy(EXISTING_ID)
            .barcode("BC-UPDATED")
            .status(Status.BORROWED)
            .price(30.0)
            .format(Format.HARD_COVER)
            .acquiredDate(LocalDate.of(2023, 1, 15))
            .build();

    when(repository.findById(EXISTING_ID)).thenReturn(Optional.of(existing));
    when(repository.save(any())).thenReturn(existing);
    when(bookCopyMapper.toDTO(any())).thenReturn(expectedDTO);

    var result = bookCopyService.updateBookCopy(EXISTING_ID, dto);

    assertEquals("BC-UPDATED", result.getBarcode());
    assertEquals(Status.BORROWED, result.getStatus());

    when(repository.findById(NON_EXISTING_ID)).thenReturn(Optional.empty());
    var exception =
        assertThrows(
            RuntimeException.class, () -> bookCopyService.updateBookCopy(NON_EXISTING_ID, dto));
    assertEquals("BookCopy not found: " + NON_EXISTING_ID, exception.getMessage());
  }
}
