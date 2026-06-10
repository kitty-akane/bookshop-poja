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

  public BookCopyService(BookCopyRepository repository) {
    this.repository = repository;
  }

  public List<BookCopyDTO> getAllBookCopies() {
    return repository.findAll().stream().map(BookCopyMapper::toDTO).toList();
  }

  public BookCopyDTO createBookCopy(BookCopyDTO dto) {
    Book book =
        repository
            .findById(dto.getIdBook())
            .orElseThrow(() -> new RuntimeException("Book not found"));
    BookCopy bookCopy = BookCopyMapper.toEntity(dto);
    bookCopy.setBook(book);
    BookCopy savedBookCopy = repository.save(bookCopy);
    return BookCopyMapper.toDTO(savedBookCopy);
  }

  public BookCopyDTO updateBookCopy(Long id, BookCopyDTO dto) {
    BookCopy existingBookCopy =
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("BookCopy with id: " + id + " not found"));

    Book book =
        bookRepository
            .findById(dto.getIdBook())
            .orElseThrow(
                () -> new RuntimeException("Book with id: " + dto.getIdBook() + " not found"));

    existingBookCopy.setBarcode(dto.getBarcode());
    existingBookCopy.setStatus(dto.getStatus());
    existingBookCopy.setPrice(dto.getPrice());
    existingBookCopy.setFormat(dto.getFormat());
    existingBookCopy.setAcquiredDate(dto.getAcquiredDate());
    existingBookCopy.setBook(book);

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
