package school.hei.td.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.hei.td.entity.Book;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Sale;
import school.hei.td.entity.dto.IncomeByGenreDTO;
import school.hei.td.repository.SaleRepository;

@ExtendWith(MockitoExtension.class)
class IncomeServiceTest {

  @Mock private SaleRepository saleRepository;

  @InjectMocks private IncomeService incomeService;

  @Test
  void getIncomeByGenre_shouldGroupAndSumByCategory() {
    Book fantasy = Book.builder().idBook(1L).category("Fantasy").build();
    Book philosophy = Book.builder().idBook(2L).category("Conte philosophique").build();

    Sale sale1 =
        Sale.builder()
            .bookCopy(BookCopy.builder().book(fantasy).build())
            .saleDate(LocalDate.now())
            .amount(20.0)
            .build();
    Sale sale2 =
        Sale.builder()
            .bookCopy(BookCopy.builder().book(fantasy).build())
            .saleDate(LocalDate.now())
            .amount(15.0)
            .build();
    Sale sale3 =
        Sale.builder()
            .bookCopy(BookCopy.builder().book(philosophy).build())
            .saleDate(LocalDate.now())
            .amount(10.0)
            .build();

    when(saleRepository.findAll()).thenReturn(List.of(sale1, sale2, sale3));

    List<IncomeByGenreDTO> result = incomeService.getIncomeByGenre();

    assertEquals(2, result.size());
    assertEquals("Fantasy", result.get(0).getGenre());
    assertEquals(35.0, result.get(0).getTotalIncome());
    assertEquals(2L, result.get(0).getSalesCount());
    assertEquals("Conte philosophique", result.get(1).getGenre());
    assertEquals(10.0, result.get(1).getTotalIncome());
  }
}
