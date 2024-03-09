package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.*;
import org.belotelov.diplom.repositories.SaleItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleItemService {
    private SaleItemRepository saleItemRepository;
    public List<SaleItem> getSaleItemsBySaleId(Long id) {
        return saleItemRepository.findSaleItemsBySaleId(id).orElse(null);
    }

    public void addItemToSale(Sale sale, Nomenclature nomenclature) {
        SaleItem existingNom = saleItemRepository.findSaleItemBySaleAndNomenclature(sale, nomenclature)
                .orElse(null);
        if(existingNom == null) {
            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setNomenclature(nomenclature);
            saleItem.setQuantity(1);
            saleItemRepository.save(saleItem);
        }
        else {
            existingNom.setQuantity(existingNom.getQuantity() + 1);
            saleItemRepository.save(existingNom);
        }
    }
}