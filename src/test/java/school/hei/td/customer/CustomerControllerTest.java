package school.hei.td.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.endpoint.rest.controller.health.CustomerController;
import school.hei.td.entity.dto.CustomerDTO;
import school.hei.td.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService service;

    private static final Long EXISTING_ID = 27394810L;
    private static final Long NON_EXISTING_ID = 99873541L;

    private CustomerDTO sampleDTO() {
        return CustomerDTO.builder()
            .idCustomer(EXISTING_ID)
            .firstName("Jean")
            .lastName("Dupont")
            .email("jean.dupont@email.com")
            .phone("034 12 345 67")
            .address("Lot 123 Antananarivo")
            .birthDate(LocalDate.of(1990, 5, 15))
            .build();
    }

    @Test
    void getById_when_customerExists_shouldReturn200() throws Exception {
        when(service.getById(EXISTING_ID)).thenReturn(sampleDTO());
        mockMvc.perform(get("/customers/{id}", EXISTING_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Jean"))
            .andExpect(jsonPath("$.email").value("jean.dupont@email.com"));
    }

    @Test
    void getById_when_customerDoesNotExist_shouldThrow404() throws Exception {
        when(service.getById(NON_EXISTING_ID))
            .thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer not found: " + NON_EXISTING_ID));
        mockMvc.perform(get("/customers/{id}", NON_EXISTING_ID))
            .andExpect(status().isNotFound());
    }

    @Test
    void getById_withInvalidId_shouldThrow400() throws Exception {
        mockMvc.perform(get("/customers/{id}", "not-a-number"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAll_shouldReturn200() throws Exception {
        when(service.getAll()).thenReturn(List.of(sampleDTO()));
        mockMvc.perform(get("/customers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].lastName").value("Dupont"));
    }

    @Test
    void create_withValidBody_shouldReturn200() throws Exception {
        var dto = sampleDTO();
        when(service.create(any())).thenReturn(dto);
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email").value("jean.dupont@email.com"));
    }

    @Test
    void create_withMissingFirstName_shouldThrow400() throws Exception {
        var dto = CustomerDTO.builder().lastName("Dupont").email("test@email.com").build();
        when(service.create(any()))
            .thenThrow(new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "firstName cannot be null"));
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void create_withDuplicateEmail_shouldThrow400() throws Exception {
        var dto = sampleDTO();
        when(service.create(any()))
            .thenThrow(new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "email already exists"));
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void update_withExistingId_shouldReturn200() throws Exception {
        var dto = sampleDTO();
        when(service.update(eq(EXISTING_ID), any())).thenReturn(dto);
        mockMvc.perform(put("/customers/{id}", EXISTING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Jean"));
    }

    @Test
    void update_withNonExistingId_shouldThrow404() throws Exception {
        when(service.update(eq(NON_EXISTING_ID), any()))
            .thenThrow(new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Customer not found: " + NON_EXISTING_ID));
        mockMvc.perform(put("/customers/{id}", NON_EXISTING_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO())))
            .andExpect(status().isNotFound());
    }

    @Test
    void update_withInvalidId_shouldThrow400() throws Exception {
        mockMvc.perform(put("/customers/{id}", "not-a-number")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void delete_withExistingId_shouldReturn200() throws Exception {
        doNothing().when(service).deleteById(EXISTING_ID);
        mockMvc.perform(delete("/customers/{id}", EXISTING_ID))
            .andExpect(status().isOk());
    }

    @Test
    void delete_withNonExistingId_shouldThrow404() throws Exception {
        doThrow(new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Customer not found: " + NON_EXISTING_ID))
            .when(service).deleteById(NON_EXISTING_ID);
        mockMvc.perform(delete("/customers/{id}", NON_EXISTING_ID))
            .andExpect(status().isNotFound());
    }
}
