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
import school.hei.td.entity.dto.BookCopyDTO;
import school.hei.td.entity.dto.BookDTO;
import school.hei.td.enums.Format;
import school.hei.td.enums.Status;
import school.hei.td.exception.GlobalExceptionHandler;
import school.hei.td.exception.NotFoundException;
import school.hei.td.service.BookCopyService;

@WebMvcTest({BookCopyController.class, GlobalExceptionHandler.class})
class BookCopyControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookCopyService bookCopyService;

  private BookCopyDTO bookCopyDTO;

  @BeforeEach
  void setUp() {

    BookDTO lePetitPrince =
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

    bookCopyDTO =
        BookCopyDTO.builder()
            .idBookCopy(1L)
            .barcode("BC-000001")
            .status(Status.AVAILABLE)
            .price(25000.0)
            .format(Format.SOFT_COVER)
            .acquiredDate(LocalDate.of(2025, 8, 8))
            .bookDTO(lePetitPrince)
            .build();
  }

  @Test
  void getById_shouldReturn200_when_BookCopyExists() throws Exception {

    when(bookCopyService.getById(bookCopyDTO.getIdBookCopy())).thenReturn(bookCopyDTO);

    mockMvc.perform(get("/bookcopies/" + bookCopyDTO.getIdBookCopy())).andExpect(status().isOk());
  }

  @Test
  void getById_shouldReturn404_when_BookCopyNotExists() throws Exception {

    when(bookCopyService.getById(2L))
        .thenThrow(new NotFoundException("BookCopy with id 2 not found", HttpStatus.NOT_FOUND));

    mockMvc.perform(get("/bookcopies/2")).andExpect(status().isNotFound());
  }

  @Test
  void getById_shouldReturn400_when_IdInvalid() throws Exception {

    mockMvc.perform(get("/bookcopies/abc")).andExpect(status().isBadRequest());
  }
}
