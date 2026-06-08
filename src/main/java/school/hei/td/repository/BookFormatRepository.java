package school.hei.td.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.td.entity.BookFormat;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, Long> {
  List<BookFormat> findByBookId(Long bookId);
}
