package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.CreateSaleRequestDTO;
import school.hei.td.entity.dto.SaleDTO;
import school.hei.td.service.SaleService;

@AllArgsConstructor
@RestController
@RequestMapping("/sales")
public class SaleController {

  private final SaleService saleService;

  @GetMapping
  public List<SaleDTO> sales() {
    return saleService.getAllSales();
  }

  @PostMapping
  public SaleDTO create(@RequestBody CreateSaleRequestDTO request) {
    return saleService.createSale(request.getBookCopyId(), request.getSaleDate());
  }
}
