package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
    Optional<List<SaleItem>> findSaleItemsBySaleId(Long saleId);
    Optional<SaleItem> findSaleItemBySaleAndNomenclature(Sale sale, Nomenclature nomenclature);
}
