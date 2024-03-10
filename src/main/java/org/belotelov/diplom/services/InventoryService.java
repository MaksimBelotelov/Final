package org.belotelov.diplom.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Inventory;
import org.belotelov.diplom.models.InventoryItem;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Stock;
import org.belotelov.diplom.repositories.InventoryItemRepository;
import org.belotelov.diplom.repositories.InventoryRepository;
import org.belotelov.diplom.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private InventoryItemRepository inventoryItemRepository;
    private StockRepository stockRepository;

    public List<Inventory> getAllInvents() { return inventoryRepository.findAll(); }
    public Inventory getInventoryById(Long id) { return inventoryRepository.findInventoryById(id); }
    public void saveInventory(Inventory inventory) { inventoryRepository.save(inventory); }
    @Transactional
    public void createNewInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        List<Stock> stocksList = stockRepository.findByMarket(inventory.getMarket());
        inventory.setInventoryItems(new ArrayList<InventoryItem>());
        for(Stock stock : stocksList) {
            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setInventory(inventory);
            inventoryItem.setNomenclature(stock.getNomenclature());
            inventoryItem.setCurrentQuantity(stock.getQuantity());
            inventoryItem.setCollectedQuantity(0);
            inventory.getInventoryItems().add(inventoryItem);
            inventoryItemRepository.save(inventoryItem);
        }
    }

    @Transactional
    public void processInventory(Inventory inventory) {
        for(InventoryItem inventoryItem : inventory.getInventoryItems()) {
            Stock stock = stockRepository.findByMarketAndNomenclature(inventory.getMarket(), inventoryItem.getNomenclature())
                    .orElse(null);
            if(inventoryItem.getCollectedQuantity() > inventoryItem.getCurrentQuantity()) {
                if(inventoryItem.getCurrentQuantity() < 0)
                    stock.setQuantity(0);
                else {
                    // Supply
                }
            }
            else if(inventoryItem.getCollectedQuantity() < inventoryItem.getCurrentQuantity()) {
                // WriteOff
            }
            stock.setQuantity(inventoryItem.getCollectedQuantity());
            stockRepository.save(stock);
            inventory.setProcessed(true);
        }
    }
}
