package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findInventoryById(Long id);
}
