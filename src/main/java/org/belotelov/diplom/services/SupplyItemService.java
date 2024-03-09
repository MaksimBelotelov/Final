package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.repositories.SupplyItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplyItemService {
    private final SupplyItemRepository supplyItemRepository;

    public List<SupplyItem> getSupplyItemsBySupplyId(Long supplyId) {
        return supplyItemRepository.findSupplyItemsBySupplyId(supplyId).orElse(null);
    }
    public void addItemToSupply(Supply supply, Nomenclature nomenclature) {
        SupplyItem existingNom = supplyItemRepository.findSupplyItemBySupplyAndNomenclature(supply, nomenclature)
                .orElse(null);
        if(existingNom == null) {
            SupplyItem supplyItem = new SupplyItem();
            supplyItem.setSupply(supply);
            supplyItem.setNomenclature(nomenclature);
            supplyItem.setQuantity(1);
            supplyItemRepository.save(supplyItem);
        }
        else {
            existingNom.setQuantity(existingNom.getQuantity() + 1);
            supplyItemRepository.save(existingNom);

        }
    }
}
