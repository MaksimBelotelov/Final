package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<List<InventoryItem>> findInventoryItemsByInventoryId(Long inventoryId);
}
