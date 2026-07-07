package school.hei.td.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.enums.Status;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
  @InjectMocks private StockService stockService;

  @Test
  void should_calculate_stock() {

    List<BookCopy> copies =
        List.of(
            BookCopy.builder().barcode("B1").status(Status.AVAILABLE).build(),
            BookCopy.builder().barcode("B2").status(Status.AVAILABLE).build(),
            BookCopy.builder().barcode("B3").status(Status.SOLD).build(),
            BookCopy.builder().barcode("B5").status(Status.RESERVED).build());

    StockDTO stock = stockService.calculateStock(copies);

    assertEquals(5, stock.getTotalQuantity());
    assertEquals(2, stock.getAvailableQuantity());
    assertEquals(1, stock.getSoldQuantity());
    assertEquals(1, stock.getReservedQuantity());
  }
}
