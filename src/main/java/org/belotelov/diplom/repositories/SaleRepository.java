package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Sale findSaleById(Long id);
}
