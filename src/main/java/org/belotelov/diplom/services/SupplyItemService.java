package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Nomenclature;
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
    public void addItemToSupply() {}
}
