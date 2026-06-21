package school.hei.td.mapper;

import org.springframework.stereotype.Component;
import school.hei.td.entity.Customer;
import school.hei.td.entity.dto.CustomerDTO;

@Component
public class CustomerMapper {

  public CustomerDTO toDTO(Customer customer) {
    return CustomerDTO.builder()
        .idCustomer(customer.getIdCustomer())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .phone(customer.getPhone())
        .address(customer.getAddress())
        .birthDate(customer.getBirthDate())
        .build();
  }

  public Customer toEntity(CustomerDTO dto) {
    return Customer.builder()
        .idCustomer(dto.getIdCustomer())
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .email(dto.getEmail())
        .phone(dto.getPhone())
        .address(dto.getAddress())
        .birthDate(dto.getBirthDate())
        .build();
  }
}
