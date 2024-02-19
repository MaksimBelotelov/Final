package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Integer> {
}
