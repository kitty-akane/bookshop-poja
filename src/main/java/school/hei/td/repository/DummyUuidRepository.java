package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.td.PojaGenerated;
import school.hei.td.repository.model.DummyUuid;

import java.util.List;

@PojaGenerated
@Repository
public interface DummyUuidRepository extends JpaRepository<DummyUuid, String> {
    @Override
    List<DummyUuid> findAllById(Iterable<String> ids);
}
