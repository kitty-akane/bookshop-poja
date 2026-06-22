package school.hei.td.endpoint.rest.controller.health;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.exception.GlobalExceptionHandler;
import school.hei.td.exception.NotFoundException;
import school.hei.td.service.BookService;

@WebMvcTest({BookController.class, GlobalExceptionHandler.class})
class BookControllerTest {
  @Autowired private MockMvc mockMvc;
  private BookDTO lePetitPrince;
  @MockBean private BookService bookService;
  private BookController bookController;

  @BeforeEach
  void setUpd() {
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
            .description(
                "Un jeune prince venu d'une autre planète découvre les relations humaines et la"
                    + " valeur de l'amitié.")
            .build();
  }

  @Test
  void getById_shouldReturn_200_when_BookExists() throws Exception {
    when(bookService.getBookById(lePetitPrince.getIdBook())).thenReturn(lePetitPrince);
    mockMvc.perform(get("/books/" + lePetitPrince.getIdBook())).andExpect(status().isOk());
  }

  @Test
  void getById_shouldReturn404_when_BookNotExists() throws Exception {
    when(bookService.getBookById(2L))
        .thenThrow(
            new NotFoundException("Book with id " + 2L + " not found)", HttpStatus.NOT_FOUND));
    mockMvc.perform(get("/books/" + 2L)).andExpect(status().isNotFound());
  }

  @Test
  void getById_shouldReturn400_when_bookInvalid() throws Exception {
    mockMvc.perform(get("/books/abc")).andExpect(status().isBadRequest());
  }
}
