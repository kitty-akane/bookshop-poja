package school.hei.td.service;

import java.util.List;
import org.springframework.stereotype.Service;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.StockDTO;

@Service
public class StockService {
  public StockDTO calculateStock(List<BookCopy> copies) {
    int available = 0;
    int borrowed = 0;
    int lost = 0;
    int reserved = 0;

    for (BookCopy copy : copies) {

      switch (copy.getStatus()) {
        case AVAILABLE -> available++;

        case BORROWED -> borrowed++;

        case LOST -> lost++;

        case RESERVED -> reserved++;
      }
    }

    return StockDTO.builder()
        .totalQuantity(copies.size())
        .availableQuantity(available)
        .borrowerQuantity(borrowed)
        .lostOrDamagedQuantity(lost)
        .reservedQuantity(reserved)
        .build();
  }
}
