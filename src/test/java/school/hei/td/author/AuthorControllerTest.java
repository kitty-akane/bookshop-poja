package school.hei.td.author;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.endpoint.rest.controller.health.AuthorController;
import school.hei.td.entity.dto.AuthorDTO;
import school.hei.td.service.AuthorService;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private AuthorService service;

  private static final Long EXISTING_ID = 15214632L;
  private static final Long NON_EXISTING_ID = 99873541L;

  private AuthorDTO sampleDTO() {
    return AuthorDTO.builder()
        .idAuthor(EXISTING_ID)
        .firstName("Victor")
        .lastName("Hugo")
        .nationality("French")
        .birthDate(LocalDate.of(1802, 2, 26))
        .build();
  }

  @Test
  void getById_withExistingId_shouldReturn200() throws Exception {
    when(service.getById(EXISTING_ID)).thenReturn(sampleDTO());
    mockMvc
        .perform(get("/authors/{id}", EXISTING_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Victor"))
        .andExpect(jsonPath("$.lastName").value("Hugo"));
  }

  @Test
  void getById_withNonExistingId_shouldThrow404() throws Exception {
    when(service.getById(NON_EXISTING_ID))
        .thenThrow(
            new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found: " + NON_EXISTING_ID));
    mockMvc.perform(get("/authors/{id}", NON_EXISTING_ID)).andExpect(status().isNotFound());
  }

  @Test
  void getById_withInvalidId_shouldThrow400() throws Exception {
    mockMvc.perform(get("/authors/{id}", "not-a-number")).andExpect(status().isBadRequest());
  }

  @Test
  void getAll_shouldReturn200() throws Exception {
    when(service.getAll()).thenReturn(List.of(sampleDTO()));
    mockMvc
        .perform(get("/authors"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].lastName").value("Hugo"));
  }

  @Test
  void create_withValidBody_shouldReturn200() throws Exception {
    var dto = sampleDTO();
    when(service.create(any())).thenReturn(dto);
    mockMvc
        .perform(
            post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Victor"));
  }

  @Test
  void create_withMissingFirstName_shouldThrow400() throws Exception {
    var dto = AuthorDTO.builder().lastName("Hugo").build();
    when(service.create(any()))
        .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "firstName cannot be null"));
    mockMvc
        .perform(
            post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void update_withExistingId_shouldReturn200() throws Exception {
    var dto = sampleDTO();
    when(service.update(eq(EXISTING_ID), any())).thenReturn(dto);
    mockMvc
        .perform(
            put("/authors/{id}", EXISTING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastName").value("Hugo"));
  }

  @Test
  void update_withNonExistingId_shouldThrow404() throws Exception {
    when(service.update(eq(NON_EXISTING_ID), any()))
        .thenThrow(
            new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found: " + NON_EXISTING_ID));
    mockMvc
        .perform(
            put("/authors/{id}", NON_EXISTING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO())))
        .andExpect(status().isNotFound());
  }

  @Test
  void update_withInvalidId_shouldThrow400() throws Exception {
    mockMvc
        .perform(
            put("/authors/{id}", "not-a-number")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO())))
        .andExpect(status().isBadRequest());
  }

  @Test
  void delete_withExistingId_shouldReturn200() throws Exception {
    doNothing().when(service).deleteById(EXISTING_ID);
    mockMvc.perform(delete("/authors/{id}", EXISTING_ID)).andExpect(status().isOk());
  }

  @Test
  void delete_withNonExistingId_shouldThrow404() throws Exception {
    doThrow(
            new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author not found: " + NON_EXISTING_ID))
        .when(service)
        .deleteById(NON_EXISTING_ID);
    mockMvc.perform(delete("/authors/{id}", NON_EXISTING_ID)).andExpect(status().isNotFound());
  }
}
