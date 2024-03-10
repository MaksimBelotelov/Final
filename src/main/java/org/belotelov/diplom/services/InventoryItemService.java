package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.InventoryItem;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryItemService {
    private final InventoryItemRepository inventoryItemRepository;

    public List<InventoryItem> getInventoryItemsByInventoryId(Long inventoryId) {
        return inventoryItemRepository.findInventoryItemsByInventoryId(inventoryId).orElse(null);
    }
    public InventoryItem getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id).orElse(null);
    }

    public void saveInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }
}
