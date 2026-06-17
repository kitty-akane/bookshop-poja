package school.hei.td.service;

import static school.hei.td.mapper.BookCopyMapper.toDTO;

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

  private final BookRepository bookRepository;
  private final BookCopyRepository repository;

  public BookCopyService(BookCopyRepository repository, BookRepository bookRepository) {
    this.repository = repository;
    this.bookRepository = bookRepository; // ← manquait cette ligne !
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

  public BookCopyDTO getById(Long id) {
    BookCopy bookCopy =
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("BookCopy not found: " + id));
    return toDTO(bookCopy);
  }

  public BookCopyDTO updateBookCopy(Long id, BookCopyDTO bookCopyDTO) {
    BookCopy bookCopy =
        repository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("BookCopy not found: " + id));
    bookCopy.setBarcode(bookCopyDTO.getBarcode());
    bookCopy.setStatus(bookCopyDTO.getStatus());
    bookCopy.setPrice(bookCopyDTO.getPrice());
    bookCopy.setFormat(bookCopyDTO.getFormat());
    bookCopy.setAcquiredDate(bookCopyDTO.getAcquiredDate());
    return toDTO(repository.save(bookCopy));
  }

  public BookCopyDTO create(BookCopyDTO dto) {
    // Récupérer le Book depuis la base
    Book book =
        bookRepository
            .findById(dto.getIdBook())
            .orElseThrow(() -> new RuntimeException("Book not found: " + dto.getIdBook()));

    BookCopy bookCopy = new BookCopy();
    bookCopy.setBarcode(dto.getBarcode());
    bookCopy.setStatus(dto.getStatus());
    bookCopy.setPrice(dto.getPrice());
    bookCopy.setFormat(dto.getFormat());
    bookCopy.setAcquiredDate(dto.getAcquiredDate());
    bookCopy.setBook(book); // ← lien avec le Book
    return toDTO(repository.save(bookCopy));
  }
}
