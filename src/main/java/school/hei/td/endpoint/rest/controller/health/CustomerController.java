package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.CustomerDTO;
import school.hei.td.service.CustomerService;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService service;

  @GetMapping
  public List<CustomerDTO> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public CustomerDTO getById(@PathVariable Long id) {
    return service.getById(id);
  }

  @PostMapping
  public CustomerDTO create(@RequestBody CustomerDTO dto) {
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    service.deleteById(id);
    return "Customer deleted successfully";
  }
}
