package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Author;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByLastNameContaining(String lastName);
}
