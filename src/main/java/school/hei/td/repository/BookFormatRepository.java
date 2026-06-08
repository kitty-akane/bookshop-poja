package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.td.entity.BookFormat;

import java.util.List;

@Repository
public interface BookFormatRepository extends JpaRepository<BookFormat, Long> {
    List<BookFormat> findByBookId(Long bookId);
}
