package school.hei.td.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  List<Author> findByLastNameContaining(String lastName);
}
