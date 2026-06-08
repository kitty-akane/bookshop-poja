package school.hei.td.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.hei.td.dto.StockMovementDTO;
import school.hei.td.entity.BookFormat;
import school.hei.td.entity.StockMovement;
import school.hei.td.exception.InsufficientStockException;
import school.hei.td.repository.BookFormatRepository;
import school.hei.td.repository.StockMovementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockMovementRepository stockMovementRepository;
    private final BookFormatRepository bookFormatRepository;

    @Transactional
    public StockMovementDTO addStock(Long bookFormatId, Integer quantity, String reason) {
        BookFormat bookFormat = bookFormatRepository.findById(bookFormatId)
                .orElseThrow(() -> new IllegalArgumentException("Format non trouvé: " + bookFormatId));

        bookFormat.setStock(bookFormat.getStock() + quantity);
        bookFormatRepository.save(bookFormat);

        StockMovement movement = StockMovement.builder()
                .bookFormat(bookFormat)
                .type(StockMovement.MovementType.IN)
                .quantity(quantity)
                .movementDate(LocalDateTime.now())
                .reason(reason)
                .build();

        StockMovement saved = stockMovementRepository.save(movement);
        return mapToDTO(saved);
    }

    @Transactional
    public StockMovementDTO removeStock(Long bookFormatId, Integer quantity, String reason) {
        BookFormat bookFormat = bookFormatRepository.findById(bookFormatId)
                .orElseThrow(() -> new IllegalArgumentException("Format non trouvé: " + bookFormatId));

        if (bookFormat.getStock() < quantity) {
            throw new InsufficientStockException(
                    "Stock insuffisant pour le format. Disponible: " + bookFormat.getStock() + ", Demandé: " + quantity
            );
        }

        bookFormat.setStock(bookFormat.getStock() - quantity);
        bookFormatRepository.save(bookFormat);

        StockMovement movement = StockMovement.builder()
                .bookFormat(bookFormat)
                .type(StockMovement.MovementType.OUT)
                .quantity(quantity)
                .movementDate(LocalDateTime.now())
                .reason(reason)
                .build();

        StockMovement saved = stockMovementRepository.save(movement);
        return mapToDTO(saved);
    }

    @Transactional
    public StockMovementDTO sellBook(Long bookFormatId, Integer quantity) {
        return removeStock(bookFormatId, quantity, "Vente client");
    }

    @Transactional
    public StockMovementDTO correctStock(Long bookFormatId, Integer newQuantity) {
        BookFormat bookFormat = bookFormatRepository.findById(bookFormatId)
                .orElseThrow(() -> new IllegalArgumentException("Format non trouvé: " + bookFormatId));

        int oldQuantity = bookFormat.getStock();
        int difference = newQuantity - oldQuantity;
        bookFormat.setStock(newQuantity);
        bookFormatRepository.save(bookFormat);

        StockMovement movement = StockMovement.builder()
                .bookFormat(bookFormat)
                .type(StockMovement.MovementType.CORRECTION)
                .quantity(Math.abs(difference))
                .movementDate(LocalDateTime.now())
                .reason("Correction inventaire: " + oldQuantity + " -> " + newQuantity)
                .build();

        StockMovement saved = stockMovementRepository.save(movement);
        return mapToDTO(saved);
    }

    public List<StockMovementDTO> getMovementHistory(Long bookFormatId) {
        List<StockMovement> movements = stockMovementRepository
                .findByBookFormatIdOrderByMovementDateDesc(bookFormatId);
        return movements.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Integer getCurrentStock(Long bookFormatId) {
        return bookFormatRepository.findById(bookFormatId)
                .map(BookFormat::getStock)
                .orElseThrow(() -> new IllegalArgumentException("Format non trouvé: " + bookFormatId));
    }

    public boolean isStockAvailable(Long bookFormatId, Integer quantity) {
        Integer currentStock = getCurrentStock(bookFormatId);
        return currentStock >= quantity;
    }

    public List<BookFormat> getFormatsByBook(Long bookId) {
        return bookFormatRepository.findByBookId(bookId);
    }

    private StockMovementDTO mapToDTO(StockMovement movement) {
        return StockMovementDTO.builder()
                .id(movement.getId())
                .bookFormatId(movement.getBookFormat().getId())
                .type(movement.getType().toString())
                .quantity(movement.getQuantity())
                .movementDate(movement.getMovementDate())
                .reason(movement.getReason())
                .build();
    }
}
