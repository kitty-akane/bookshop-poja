package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.td.dto.BookCopyDTO;
import school.hei.td.service.BookCopyService;

@RestController
@RequestMapping("/api/bookcopies")
public class BookCopyController {

  private final BookCopyService service;

  public BookCopyController(BookCopyService service) {
    this.service = service;
  }

  @GetMapping(value = "/bookCopies")
  public List<BookCopyDTO> bookCopies() {
    return service.getAllBookCopies();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
