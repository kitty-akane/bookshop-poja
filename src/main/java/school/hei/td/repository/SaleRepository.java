package school.hei.td.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.hei.td.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {}
