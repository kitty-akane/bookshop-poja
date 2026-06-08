package school.hei.td.mapper;

import org.springframework.stereotype.Component;
import school.hei.td.dto.StockMovementDTO;
import school.hei.td.entity.StockMovement;

@Component
public class StockMovementMapper {

  public StockMovementDTO toDTO(StockMovement entity) {
    if (entity == null) {
      return null;
    }
    return StockMovementDTO.builder()
        .id(entity.getId())
        .bookFormatId(entity.getBookFormat().getId())
        .type(entity.getType().toString())
        .quantity(entity.getQuantity())
        .movementDate(entity.getMovementDate())
        .reason(entity.getReason())
        .build();
  }

  public StockMovement toEntity(StockMovementDTO dto) {
    if (dto == null) {
      return null;
    }
    return StockMovement.builder()
        .id(dto.getId())
        .type(StockMovement.MovementType.valueOf(dto.getType()))
        .quantity(dto.getQuantity())
        .movementDate(dto.getMovementDate())
        .reason(dto.getReason())
        .build();
  }
}
