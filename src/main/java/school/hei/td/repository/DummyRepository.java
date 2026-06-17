package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.td.PojaGenerated;
import school.hei.td.repository.model.Dummy;

import java.util.List;

@PojaGenerated
@Repository
public interface DummyRepository extends JpaRepository<Dummy, String> {

    @Override
    List<Dummy> findAll();
}
