package school.hei.td.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.entity.dto.LowStockBookDTO;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.enums.Format;
import school.hei.td.repository.BookCopyRepository;

@Service
@AllArgsConstructor
public class BookStockService {
  private final BookCopyRepository bookCopyRepository;
  private final BookService bookService;
  private final StockService stockService;

  public StockDTO getStockByBook(Long bookId) {
    bookService.getBookById(bookId);
    List<BookCopy> copies = bookCopyRepository.findByBook_IdBook(bookId);
    return stockService.calculateStock(copies);
  }

  public StockDTO getStockByBookAndFormat(Long bookId, Format format) {
    bookService.getBookById(bookId);
    List<BookCopy> copies =
        bookCopyRepository.findByBook_IdBook(bookId).stream()
            .filter(copy -> copy.getFormat() == format)
            .toList();
    return stockService.calculateStock(copies);
  }

  public List<LowStockBookDTO> getLowStockBooks(int threshold) {
    List<BookDTO> books = bookService.getAllBooks();
    return books.stream()
        .map(this::toLowStockBookDTO)
        .filter(dto -> dto.getAvailableQuantity() <= threshold)
        .toList();
  }

  private LowStockBookDTO toLowStockBookDTO(BookDTO book) {
    List<BookCopy> copies = bookCopyRepository.findByBook_IdBook(book.getIdBook());
    int available = stockService.calculateStock(copies).getAvailableQuantity();
    return LowStockBookDTO.builder().bookDTO(book).availableQuantity(available).build();
  }
}
