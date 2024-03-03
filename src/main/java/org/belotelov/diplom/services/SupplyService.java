package org.belotelov.diplom.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Stock;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.repositories.StockRepository;
import org.belotelov.diplom.repositories.SupplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SupplyService {
    private SupplyRepository supplyRepository;
    private StockRepository stockRepository;

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    public Supply getSupplyById(Long id) { return supplyRepository.findSupplyById(id); }

    public void addSupply(Supply supply) {
        supplyRepository.save(supply);
    }

    @Transactional
    public void processSupply(Supply supply) {
        supplyRepository.save(supply);

        for (SupplyItem item : supply.getSupplyItems()) {
            Stock stock = stockRepository.findByMarketAndNomenclature(supply.getMarket(), item.getNomenclature())
                    .orElse(new Stock());
            if (stock.getId() == null) {
                stock.setMarket(supply.getMarket());
                stock.setNomenclature(item.getNomenclature());
                stock.setQuantity(0);
            }
            stock.setQuantity(stock.getQuantity() + item.getQuantity());
            stockRepository.save(stock);
        }
    }
}
