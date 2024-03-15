package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Sale findSaleById(Long id);
    List<Sale> findSalesBySaledate(LocalDate date);
}
