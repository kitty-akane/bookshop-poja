package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Format;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.service.BookCopyService;
import school.hei.td.service.BookService;
import school.hei.td.service.StockService;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;
  private final BookCopyService bookCopyService;
  private final BookCopyRepository bookCopyRepository;
  private final StockService stockService;

  @GetMapping
  public List<BookDTO> books() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{id}")
  public BookDTO getBookById(@PathVariable Long id) {
    return bookService.getBookById(id);
  }

  @PutMapping("/{id}")
  public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
    return bookService.updateBook(id, dto);
  }

  @DeleteMapping("/{id}")
  public String deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return "Book copy deleted successfully";
  }

  @PostMapping
  public BookDTO create(@RequestBody BookDTO bookDTO) {
    return bookService.createBook(bookDTO);
  }

  @GetMapping("/search/{value}")
  public List<BookDTO> getAllBooksSearch(@PathVariable String value) {
    return bookService.getAllBooksSearch(value);
  }

  // Stock global du livre, toutes editions confondues
  @GetMapping("/{bookId}/stock")
  public StockDTO getBookStock(@PathVariable Long bookId) {
    bookService.getBookById(bookId);
    List<BookCopy> copies = bookCopyRepository.findByBook_IdBook(bookId);
    return stockService.calculateStock(copies);
  }

  // Stock d'une edition precise (format) du livre
  @GetMapping("/{bookId}/stock/{format}")
  public StockDTO getBookStockByFormat(@PathVariable Long bookId, @PathVariable Format format) {
    bookService.getBookById(bookId);
    List<BookCopy> copies =
        bookCopyRepository.findByBook_IdBook(bookId).stream()
            .filter(copy -> copy.getFormat() == format)
            .toList();
    return stockService.calculateStock(copies);
  }
}
