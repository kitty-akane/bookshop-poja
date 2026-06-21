package school.hei.td.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.entity.dto.CustomerDTO;
import school.hei.td.mapper.CustomerMapper;
import school.hei.td.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper customerMapper;

  public List<CustomerDTO> getAll() {
    return repository.findAll().stream().map(customerMapper::toDTO).toList();
  }

  public CustomerDTO getById(Long id) {
    return repository
        .findById(id)
        .map(customerMapper::toDTO)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id));
  }

  public CustomerDTO create(CustomerDTO dto) {
    return customerMapper.toDTO(repository.save(customerMapper.toEntity(dto)));
  }

  public CustomerDTO update(Long id, CustomerDTO dto) {
    var customer =
        repository
            .findById(id)
            .orElseThrow(
                () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id));
    customer.setFirstName(dto.getFirstName());
    customer.setLastName(dto.getLastName());
    customer.setEmail(dto.getEmail());
    customer.setPhone(dto.getPhone());
    customer.setAddress(dto.getAddress());
    customer.setBirthDate(dto.getBirthDate());
    return customerMapper.toDTO(repository.save(customer));
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id);
    }
    repository.deleteById(id);
  }
}
