package school.hei.td.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.td.dto.StockMovementDTO;
import school.hei.td.service.StockService;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;

  @PostMapping("/add")
  public ResponseEntity<StockMovementDTO> addStock(
      @RequestParam Long bookFormatId,
      @RequestParam Integer quantity,
      @RequestParam String reason) {
    StockMovementDTO movement = stockService.addStock(bookFormatId, quantity, reason);
    return ResponseEntity.status(HttpStatus.CREATED).body(movement);
  }

  @PostMapping("/remove")
  public ResponseEntity<StockMovementDTO> removeStock(
      @RequestParam Long bookFormatId,
      @RequestParam Integer quantity,
      @RequestParam String reason) {
    StockMovementDTO movement = stockService.removeStock(bookFormatId, quantity, reason);
    return ResponseEntity.ok(movement);
  }

  @PostMapping("/sell")
  public ResponseEntity<StockMovementDTO> sellBook(
      @RequestParam Long bookFormatId, @RequestParam Integer quantity) {
    StockMovementDTO movement = stockService.sellBook(bookFormatId, quantity);
    return ResponseEntity.ok(movement);
  }

  @PostMapping("/correct")
  public ResponseEntity<StockMovementDTO> correctStock(
      @RequestParam Long bookFormatId, @RequestParam Integer newQuantity) {
    StockMovementDTO movement = stockService.correctStock(bookFormatId, newQuantity);
    return ResponseEntity.ok(movement);
  }

  @GetMapping("/history/{bookFormatId}")
  public ResponseEntity<List<StockMovementDTO>> getMovementHistory(
      @PathVariable Long bookFormatId) {
    List<StockMovementDTO> history = stockService.getMovementHistory(bookFormatId);
    return ResponseEntity.ok(history);
  }

  @GetMapping("/current/{bookFormatId}")
  public ResponseEntity<Integer> getCurrentStock(@PathVariable Long bookFormatId) {
    Integer stock = stockService.getCurrentStock(bookFormatId);
    return ResponseEntity.ok(stock);
  }

  @GetMapping("/available/{bookFormatId}/{quantity}")
  public ResponseEntity<Boolean> isAvailable(
      @PathVariable Long bookFormatId, @PathVariable Integer quantity) {
    boolean available = stockService.isStockAvailable(bookFormatId, quantity);
    return ResponseEntity.ok(available);
  }
}
