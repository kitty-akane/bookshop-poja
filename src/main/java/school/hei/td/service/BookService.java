package school.hei.td.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import school.hei.td.dto.BookDTO;
import school.hei.td.entity.Book;
import school.hei.td.mapper.BookMapper;
import school.hei.td.repository.BookRepository;

@Service
@AllArgsConstructor
public class BookService {
  private final BookRepository bookRepository;

  public List<BookDTO> getAllBooks() {
    return bookRepository.findAll().stream().map(BookMapper::toDTO).toList();
  }

  public BookDTO createBook(BookDTO dto) {
    Book book = BookMapper.toEntity(dto);
    Book savedBook = bookRepository.save(book);
    return BookMapper.toDTO(savedBook);
  }

  public BookDTO getBookById(Long id) {
    Book book =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    return BookMapper.toDTO(book);
  }

  public BookDTO updateBook(Long id, BookDTO dto) {
    Book existingBook =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

    if (dto.getTitle() != null) {
      existingBook.setTitle(dto.getTitle());
    }

    if (dto.getIsbn() != null) {
      existingBook.setIsbn(dto.getIsbn());
    }

    if (dto.getAuthor() != null) {
      existingBook.setAuthor(dto.getAuthor());
    }

    if (dto.getPublisher() != null) {
      existingBook.setPublisher(dto.getPublisher());
    }

    if (dto.getLanguage() != null) {
      existingBook.setLanguage(dto.getLanguage());
    }

    if (dto.getCategory() != null) {
      existingBook.setCategory(dto.getCategory());
    }

    if (dto.getDescription() != null) {
      existingBook.setDescription(dto.getDescription());
    }

    if (dto.getPublicationDate() != null) {
      existingBook.setPublicationDate(dto.getPublicationDate());
    }

    Book saved = bookRepository.save(existingBook);
    return BookMapper.toDTO(saved);
  }

  public void deleteBook(Long id) {
    Book existingBook =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    bookRepository.delete(existingBook);
  }

  public List<BookDTO> getAllBooksSearch(String value) {
    return bookRepository.findByTitleContaining(value).stream().map(BookMapper::toDTO).toList();
  }
}
