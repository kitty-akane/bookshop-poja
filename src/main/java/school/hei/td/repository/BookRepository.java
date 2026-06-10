package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

