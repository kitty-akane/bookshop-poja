package school.hei.td.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import school.hei.td.entity.Sale;
import school.hei.td.entity.dto.SaleDTO;

@Component
@AllArgsConstructor
public class SaleMapper {
  private final BookCopyMapper bookCopyMapper;

  public SaleDTO toDTO(Sale sale) {
    return SaleDTO.builder()
        .idSale(sale.getIdSale())
        .bookCopyDTO(bookCopyMapper.toDTO(sale.getBookCopy()))
        .saleDate(sale.getSaleDate())
        .amount(sale.getAmount())
        .build();
  }
}
