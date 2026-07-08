package school.hei.td.endpoint.rest.controller.health;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import school.hei.td.entity.dto.LowStockBookDTO;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.enums.Format;
import school.hei.td.service.BookStockService;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookStockController {

  private final BookStockService bookStockService;

  @GetMapping("/low-stock")
  public List<LowStockBookDTO> getLowStockBooks(@RequestParam(defaultValue = "3") int threshold) {
    return bookStockService.getLowStockBooks(threshold);
  }

  @GetMapping("/{bookId}/stock")
  public StockDTO getBookStock(@PathVariable Long bookId) {
    return bookStockService.getStockByBook(bookId);
  }

  @GetMapping("/{bookId}/stock/{format}")
  public StockDTO getBookStockByFormat(@PathVariable Long bookId, @PathVariable Format format) {
    return bookStockService.getStockByBookAndFormat(bookId, format);
  }
}
