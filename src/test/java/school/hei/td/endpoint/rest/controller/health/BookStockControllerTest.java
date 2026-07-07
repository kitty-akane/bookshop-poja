package school.hei.td.endpoint.rest.controller.health;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import school.hei.td.entity.dto.StockDTO;
import school.hei.td.enums.Format;
import school.hei.td.exception.GlobalExceptionHandler;
import school.hei.td.service.BookStockService;

@WebMvcTest({BookStockController.class, GlobalExceptionHandler.class})
class BookStockControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookStockService bookStockService;

  @Test
  void getBookStock_shouldReturnAggregatedStock() throws Exception {
    when(bookStockService.getStockByBook(1L))
        .thenReturn(StockDTO.builder().totalQuantity(2).availableQuantity(1).build());

    mockMvc
        .perform(get("/books/1/stock"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalQuantity").value(2));
  }

  @Test
  void getBookStockByFormat_shouldFilterByFormat() throws Exception {
    when(bookStockService.getStockByBookAndFormat(1L, Format.SOFT_COVER))
        .thenReturn(StockDTO.builder().totalQuantity(1).availableQuantity(1).build());

    mockMvc
        .perform(get("/books/1/stock/SOFT_COVER"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalQuantity").value(1));
  }
}
