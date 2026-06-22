package school.hei.td.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {
  List<BookCopy> findByBook_IdBook(Long bookId);
}
