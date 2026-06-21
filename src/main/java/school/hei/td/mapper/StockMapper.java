package school.hei.td.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.td.entity.Stock;
import school.hei.td.entity.dto.StockDTO;

@Component
@AllArgsConstructor
public class StockMapper {
  private final BookCopyMapper bookCopyMapper;

  public StockDTO toDTO(Stock stock) {
    return StockDTO.builder()
        .totalQuantity(stock.getTotalQuantity())
        .availableQuantity(stock.getAvailableQuantity())
        .borrowerQuantity(stock.getBorrowerQuantity())
        .lostOrDamagedQuantity(stock.getLostOrDamagedQuantity())
        .build();
  }

  public Stock toEntity(StockDTO stockDTO) {
    return Stock.builder()
        .totalQuantity(stockDTO.getTotalQuantity())
        .availableQuantity(stockDTO.getAvailableQuantity())
        .borrowerQuantity(stockDTO.getBorrowerQuantity())
        .lostOrDamagedQuantity(stockDTO.getLostOrDamagedQuantity())
        .build();
  }
}
