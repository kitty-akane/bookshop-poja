package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.BookCopyDTO;
import school.hei.td.service.BookCopyService;

@RestController
@AllArgsConstructor
@RequestMapping("/bookcopies")
public class BookCopyController {

  private final BookCopyService service;

  @GetMapping()
  public List<BookCopyDTO> bookCopies() {
    return service.getAllBookCopies();
  }

  @GetMapping("/{id}")
  public BookCopyDTO getOne(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("/{id}")
  public BookCopyDTO update(@PathVariable Long id, @RequestBody BookCopyDTO dto) {
    return service.updateBookCopy(id, dto);
  }

  @PostMapping
  public BookCopyDTO create(@RequestBody BookCopyDTO dto) {
    return service.create(dto);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    service.deleteById(id);
    return "Book copy deleted successfully";
  }
}
