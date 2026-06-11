package school.hei.td.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.dto.BookCopyDTO;
import school.hei.td.mapper.BookCopyMapper;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.repository.BookRepository;

@Service
public class BookCopyService {

  private final BookCopyRepository repository;

  public BookCopyService(BookCopyRepository repository, BookRepository bookRepository) {
    this.repository = repository;
  }

  public List<BookCopyDTO> getAllBookCopies() {
    return repository.findAll().stream().map(BookCopyMapper::toDTO).toList();
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repository.deleteById(id);
  }
}
