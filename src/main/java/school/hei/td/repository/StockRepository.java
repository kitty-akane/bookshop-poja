package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {}
