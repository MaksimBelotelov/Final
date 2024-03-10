package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByMarketAndNomenclature(Market market, Nomenclature nomenclature);
    List<Stock> findByMarket(Market market);
}
