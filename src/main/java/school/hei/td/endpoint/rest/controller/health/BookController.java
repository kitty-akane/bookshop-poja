package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import school.hei.td.dto.BookDTO;
import school.hei.td.service.BookService;

@AllArgsConstructor
@RestController
public class BookController {
  private final BookService bookService;

  @GetMapping(value = "/books")
  public List<BookDTO> books() {
    return bookService.getAllBooks();
  }

  @DeleteMapping(value = "/books/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/books/{value}")
  public List<BookDTO> getAllBooksSearch(@PathVariable String value) {
    return bookService.getAllBooksSearch(value);
  }
}
