package school.hei.td.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.repository.BookCopyRepository;

@Service
public class BookCopyService {
  private final BookCopyRepository repository;

  public BookCopyService(BookCopyRepository repository) {
    this.repository = repository;
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repository.deleteById(id);
  }
}
