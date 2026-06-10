package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameContaining(String search);
}

