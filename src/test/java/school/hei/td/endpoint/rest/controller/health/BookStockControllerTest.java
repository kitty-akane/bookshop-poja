package school.hei.td.endpoint.rest.controller.health;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Format;
import school.hei.td.entity.Status;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.exception.GlobalExceptionHandler;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.service.BookCopyService;
import school.hei.td.service.BookService;
import school.hei.td.service.StockService;

@WebMvcTest({BookController.class, GlobalExceptionHandler.class})
class BookStockControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService bookService;
  @MockBean private BookCopyService bookCopyService;
  @MockBean private BookCopyRepository bookCopyRepository;
  @MockBean private StockService stockService;

  private BookDTO lePetitPrince;

  @BeforeEach
  void setUp() {
    lePetitPrince =
        BookDTO.builder()
            .idBook(1L)
            .title("Le Petit Prince")
            .isbn("978-2070612758")
            .publicationDate(LocalDate.of(1943, 4, 6))
            .author("Antoine de Saint-Exupéry")
            .publisher("Gallimard")
            .language("Français")
            .category("Conte philosophique")
            .description("Un jeune prince decouvre les relations humaines.")
            .build();

    when(stockService.calculateStock(any())).thenCallRealMethod();
  }

  @Test
  void getBookStock_shouldReturnAggregatedStock() throws Exception {
    when(bookService.getBookById(1L)).thenReturn(lePetitPrince);
    when(bookCopyRepository.findByBook_IdBook(1L))
        .thenReturn(
            List.of(
                BookCopy.builder().status(Status.AVAILABLE).format(Format.SOFT_COVER).build(),
                BookCopy.builder().status(Status.BORROWED).format(Format.HARD_COVER).build()));

    mockMvc
        .perform(get("/books/1/stock"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalQuantity").value(2));
  }

  @Test
  void getBookStockByFormat_shouldFilterByFormat() throws Exception {
    when(bookService.getBookById(1L)).thenReturn(lePetitPrince);
    when(bookCopyRepository.findByBook_IdBook(1L))
        .thenReturn(
            List.of(
                BookCopy.builder().status(Status.AVAILABLE).format(Format.SOFT_COVER).build(),
                BookCopy.builder().status(Status.BORROWED).format(Format.HARD_COVER).build()));

    mockMvc
        .perform(get("/books/1/stock/SOFT_COVER"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalQuantity").value(1));
  }
}
