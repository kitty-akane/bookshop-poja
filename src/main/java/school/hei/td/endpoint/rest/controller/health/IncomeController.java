package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.hei.td.entity.dto.RevenueByGenreDTO;
import school.hei.td.service.RevenueService;

@AllArgsConstructor
@RestController
@RequestMapping("/income")
public class IncomeController {

  private final RevenueService revenueService;

  @GetMapping("/by-genre")
  public List<RevenueByGenreDTO> revenueByGenre() {
    return revenueService.getRevenueByGenre();
  }
}
