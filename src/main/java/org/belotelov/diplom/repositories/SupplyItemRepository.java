package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplyItemRepository extends JpaRepository<SupplyItem, Long> {
    Optional<List<SupplyItem>> findSupplyItemsBySupplyId(Long supplyId);
    Optional<SupplyItem> findSupplyItemBySupplyAndNomenclature(Supply supply, Nomenclature nomenclature);
}
