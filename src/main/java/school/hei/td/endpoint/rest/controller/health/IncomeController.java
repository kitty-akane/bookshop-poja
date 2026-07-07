package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.td.entity.dto.IncomeByGenreDTO;
import school.hei.td.service.IncomeService;

@AllArgsConstructor
@RestController
@RequestMapping("/income")
public class IncomeController {

  private final IncomeService IncomeService;

  @GetMapping("/by-genre")
  public List<IncomeByGenreDTO> IncomeByGenre() {
    return IncomeService.getIncomeByGenre();
  }
}
