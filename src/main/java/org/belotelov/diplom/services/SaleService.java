package org.belotelov.diplom.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Account;
import org.belotelov.diplom.models.Sale;
import org.belotelov.diplom.models.SaleItem;
import org.belotelov.diplom.models.Stock;
import org.belotelov.diplom.repositories.AccountRepository;
import org.belotelov.diplom.repositories.SaleRepository;
import org.belotelov.diplom.repositories.StockRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final StockRepository stockRepository;
    private final AccountRepository accountRepository;

    public void addSale(Sale sale) {
        saleRepository.save(sale);
    }

    public Sale getSaleById(Long id) { return saleRepository.findSaleById(id); }

    @Transactional
    public void processSale(Sale sale) {
        double total = 0.0;
        for(SaleItem item : sale.getSaleItems()) {
            Stock stock = stockRepository.findByMarketAndNomenclature(sale.getMarket(), item.getNomenclature())
                    .orElse(null);
            if (stock != null) {
                stock.setQuantity(stock.getQuantity() - item.getQuantity());
                total += item.getQuantity() * item.getNomenclature().getPrice();
                stockRepository.save(stock);
            }
            else {
                System.out.println("Товар не найден");
            }
        }
        sale.setTotal(total);
        Account account = sale.getMarket().getAccount();
        account.setBalance(account.getBalance() + total);
        accountRepository.save(account);
        sale.setProcessed(true);
        saleRepository.save(sale);
    }
}