package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.service.BookService;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

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
}
