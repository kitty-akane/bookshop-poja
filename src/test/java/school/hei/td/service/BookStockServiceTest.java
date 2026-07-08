package school.hei.td.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.enums.Format;
import school.hei.td.enums.Status;
import school.hei.td.repository.BookCopyRepository;

@ExtendWith(MockitoExtension.class)
class BookStockServiceTest {

  @Mock private BookCopyRepository bookCopyRepository;
  @Mock private BookService bookService;
  private final StockService stockService = new StockService();

  private BookStockService bookStockService;

  private BookDTO sampleBook() {
    return BookDTO.builder()
        .idBook(1L)
        .title("Le Petit Prince")
        .isbn("978-2070612758")
        .publicationDate(LocalDate.of(1943, 4, 6))
        .author("Antoine de Saint-Exupéry")
        .publisher("Gallimard")
        .language("Français")
        .category("Conte philosophique")
        .description("Un jeune prince decouvre les relations humaines.")
        .build();
  }

  @Test
  void getStockByBook_shouldAggregateAllFormats() {
    bookStockService = new BookStockService(bookCopyRepository, bookService, stockService);
    when(bookService.getBookById(1L)).thenReturn(sampleBook());
    when(bookCopyRepository.findByBook_IdBook(1L))
        .thenReturn(
            List.of(
                BookCopy.builder().status(Status.AVAILABLE).format(Format.SOFT_COVER).build(),
                BookCopy.builder().status(Status.SOLD).format(Format.HARD_COVER).build()));

    StockDTO stock = bookStockService.getStockByBook(1L);

    assertEquals(2, stock.getTotalQuantity());
    assertEquals(1, stock.getAvailableQuantity());
    assertEquals(1, stock.getSoldQuantity());
  }

  @Test
  void getStockByBookAndFormat_shouldFilterByFormat() {
    bookStockService = new BookStockService(bookCopyRepository, bookService, stockService);
    when(bookService.getBookById(1L)).thenReturn(sampleBook());
    when(bookCopyRepository.findByBook_IdBook(1L))
        .thenReturn(
            List.of(
                BookCopy.builder().status(Status.AVAILABLE).format(Format.SOFT_COVER).build(),
                BookCopy.builder().status(Status.SOLD).format(Format.HARD_COVER).build()));

    StockDTO stock = bookStockService.getStockByBookAndFormat(1L, Format.SOFT_COVER);

    assertEquals(1, stock.getTotalQuantity());
    assertEquals(1, stock.getAvailableQuantity());
  }

  @Test
  void getLowStockBooks_shouldOnlyReturnBooksUnderThreshold() {
    bookStockService = new BookStockService(bookCopyRepository, bookService, stockService);
    when(bookService.getAllBooks()).thenReturn(List.of(sampleBook()));
    when(bookCopyRepository.findByBook_IdBook(1L))
        .thenReturn(List.of(BookCopy.builder().status(Status.AVAILABLE).build()));

    var result = bookStockService.getLowStockBooks(3);

    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getAvailableQuantity());
  }
}
