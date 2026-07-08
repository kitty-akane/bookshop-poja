package school.hei.td.service;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.Sale;
import school.hei.td.entity.dto.SaleDTO;
import school.hei.td.enums.Status;
import school.hei.td.exception.NotFoundException;
import school.hei.td.mapper.SaleMapper;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.repository.SaleRepository;

@Service
@AllArgsConstructor
public class SaleService {
  private final SaleRepository saleRepository;
  private final BookCopyRepository bookCopyRepository;
  private final SaleMapper saleMapper;

  public List<SaleDTO> getAllSales() {
    return saleRepository.findAll().stream().map(saleMapper::toDTO).toList();
  }

  public SaleDTO createSale(Long bookCopyId, LocalDate saleDate) {
    BookCopy bookCopy =
        bookCopyRepository
            .findById(bookCopyId)
            .orElseThrow(
                () ->
                    new NotFoundException(
                        "BookCopy not found: " + bookCopyId, HttpStatus.NOT_FOUND));

    bookCopy.setStatus(Status.SOLD);
    bookCopyRepository.save(bookCopy);

    Sale sale =
        Sale.builder().bookCopy(bookCopy).saleDate(saleDate).amount(bookCopy.getPrice()).build();

    return saleMapper.toDTO(saleRepository.save(sale));
  }
}
