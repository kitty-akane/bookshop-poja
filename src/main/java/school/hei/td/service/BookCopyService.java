package school.hei.td.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.entity.BookCopy;
import school.hei.td.entity.dto.BookCopyDTO;
import school.hei.td.exception.NotFoundException;
import school.hei.td.mapper.BookCopyMapper;
import school.hei.td.repository.BookCopyRepository;

@Service
@AllArgsConstructor
public class BookCopyService {
  private final BookCopyRepository repository;
  private final BookCopyMapper bookCopyMapper;

  public List<BookCopyDTO> getAllBookCopies() {
    return repository.findAll().stream().map(bookCopyMapper::toDTO).toList();
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repository.deleteById(id);
  }

  public BookCopyDTO getById(Long id) {
    BookCopy bookCopy =
        repository
            .findById(id)
            .orElseThrow(
                () -> new NotFoundException("BookCopy not found: " + id, HttpStatus.NOT_FOUND));
    return bookCopyMapper.toDTO(bookCopy);
  }

  public BookCopyDTO updateBookCopy(Long id, BookCopyDTO bookCopyDTO) {
    BookCopy bookCopy =
        repository
            .findById(id)
            .orElseThrow(
                () -> new NotFoundException("BookCopy not found: " + id, HttpStatus.NOT_FOUND));
    bookCopy.setBarcode(bookCopyDTO.getBarcode());
    bookCopy.setStatus(bookCopyDTO.getStatus());
    bookCopy.setPrice(bookCopyDTO.getPrice());
    bookCopy.setFormat(bookCopyDTO.getFormat());
    bookCopy.setAcquiredDate(bookCopyDTO.getAcquiredDate());
    return bookCopyMapper.toDTO(repository.save(bookCopy));
  }

  public BookCopyDTO create(BookCopyDTO dto) {
    return bookCopyMapper.toDTO(repository.save(bookCopyMapper.toEntity(dto)));
  }
}
