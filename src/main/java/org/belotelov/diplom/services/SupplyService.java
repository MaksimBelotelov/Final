package org.belotelov.diplom.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.*;
import org.belotelov.diplom.repositories.AccountRepository;
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
    private AccountRepository accountRepository;

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }

    public List<Stock> getAllStocks() { return stockRepository.findAll(); }

    public Supply getSupplyById(Long id) { return supplyRepository.findSupplyById(id); }

    public void addSupply(Supply supply) {
        supplyRepository.save(supply);
    }

    @Transactional
    public void processSupply(Supply supply) {
        double total = 0.0;
        for (SupplyItem item : supply.getSupplyItems()) {
            Stock stock = stockRepository.findByMarketAndNomenclature(supply.getMarket(), item.getNomenclature())
                    .orElse(new Stock());
            if (stock.getId() == null) {
                stock.setMarket(supply.getMarket());
                stock.setNomenclature(item.getNomenclature());
                stock.setQuantity(0);
            }
            stock.setQuantity(stock.getQuantity() + item.getQuantity());
            total += item.getQuantity()*item.getNomenclature().getOptPrice();
            stockRepository.save(stock);
        }
        Account account = supply.getMarket().getAccount();
        account.setBalance(account.getBalance() - total);
        accountRepository.save(account);
        supply.setProcessed(true);
        supplyRepository.save(supply);
    }
}
