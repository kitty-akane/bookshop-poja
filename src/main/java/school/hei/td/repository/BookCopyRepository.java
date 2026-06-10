package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.BookCopy;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {}
