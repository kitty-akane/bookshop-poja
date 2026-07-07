package school.hei.td.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.td.entity.Sale;
import school.hei.td.entity.dto.IncomeByGenreDTO;
import school.hei.td.repository.SaleRepository;

@Service
@AllArgsConstructor
public class IncomeService {
  private final SaleRepository saleRepository;

  public List<IncomeByGenreDTO> getIncomeByGenre() {
    List<Sale> sales = saleRepository.findAll();

    Map<String, List<Sale>> salesByGenre =
        sales.stream()
            .collect(Collectors.groupingBy(sale -> sale.getBookCopy().getBook().getCategory()));

    return salesByGenre.entrySet().stream()
        .map(
            entry ->
                IncomeByGenreDTO.builder()
                    .genre(entry.getKey())
                    .totalIncome(entry.getValue().stream().mapToDouble(Sale::getAmount).sum())
                    .salesCount((long) entry.getValue().size())
                    .build())
        .sorted(Comparator.comparingDouble(IncomeByGenreDTO::getTotalIncome).reversed())
        .toList();
  }
}
