package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.AuthorDTO;
import school.hei.td.service.AuthorService;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

  private final AuthorService service;

  @GetMapping
  public List<AuthorDTO> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public AuthorDTO getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @PostMapping
  public AuthorDTO create(@RequestBody AuthorDTO dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public AuthorDTO update(@PathVariable Long id, @RequestBody AuthorDTO dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    service.deleteById(id);
    return "Author deleted successfully";
  }
}
