package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.repositories.MarketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;
    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }
    public void addNewMarket(Market market) {
        marketRepository.save(market);
    }
}
