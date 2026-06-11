package school.hei.td.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.hei.td.dto.BookCopyDTO;
import school.hei.td.entity.Book;
import school.hei.td.entity.BookCopy;
import school.hei.td.mapper.BookCopyMapper;
import school.hei.td.repository.BookCopyRepository;
import school.hei.td.repository.BookRepository;

@Service
public class BookCopyService {

  private final BookCopyRepository repository;
  private final BookRepository bookRepository;

  public BookCopyService(BookCopyRepository repository, BookRepository bookRepository) {
    this.repository = repository;
    this.bookRepository = bookRepository;
  }

  public List<BookCopyDTO> getAllBookCopies() {
    return repository.findAll().stream().map(BookCopyMapper::toDTO).toList();
  }

  public BookCopyDTO createBookCopy(BookCopyDTO dto) {
    Book book =
        bookRepository
            .findById(dto.getIdBookCopy())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    BookCopy bookCopy = new BookCopy();
    bookCopy.setAvailable(dto.getAvailable());
    bookCopy.setBook(book);
    BookCopy savedBookCopy = repository.save(bookCopy);
    return BookCopyMapper.toDTO(savedBookCopy);
  }

  public BookCopyDTO updateBookCopy(Long id, BookCopyDTO dto) {
    BookCopy existingBookCopy =
        repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    existingBookCopy.setAvailable(dto.getAvailable());
    BookCopy updatedBookCopy = repository.save(existingBookCopy);
    return BookCopyMapper.toDTO(updatedBookCopy);
  }

  public void deleteById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    repository.deleteById(id);
  }
}
