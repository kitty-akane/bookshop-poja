package school.hei.td.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Status;
import school.hei.td.entity.dto.StockDTO;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
  @InjectMocks private StockService stockService;

  @Test
  void should_calculate_stock() {

    List<BookCopy> copies =
        List.of(
            BookCopy.builder().barcode("B1").status(Status.AVAILABLE).build(),
            BookCopy.builder().barcode("B2").status(Status.AVAILABLE).build(),
            BookCopy.builder().barcode("B3").status(Status.BORROWED).build(),
            BookCopy.builder().barcode("B4").status(Status.LOST).build(),
            BookCopy.builder().barcode("B5").status(Status.RESERVED).build());

    StockDTO stock = stockService.calculateStock(copies);

    assertEquals(5, stock.getTotalQuantity());
    assertEquals(2, stock.getAvailableQuantity());
    assertEquals(1, stock.getBorrowerQuantity());
    assertEquals(1, stock.getLostOrDamagedQuantity());
  }
}
