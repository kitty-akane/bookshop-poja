package school.hei.td.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.hei.td.entity.StockMovement;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
  List<StockMovement> findByBookFormatIdOrderByMovementDateDesc(Long bookFormatId);
}
