package school.hei.td.endpoint.rest.controller.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.td.dto.BookCopyDTO;
import school.hei.td.service.BookCopyService;

import java.util.List;

@RestController
@RequestMapping("/api/bookcopies")
public class BookCopyController {

    private final BookCopyService service;

    public BookCopyController(BookCopyService service) {
        this.service = service;
    }

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
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
