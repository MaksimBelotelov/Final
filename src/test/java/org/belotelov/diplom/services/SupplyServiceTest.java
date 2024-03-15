package org.belotelov.diplom.services;

import org.belotelov.diplom.models.*;
import org.belotelov.diplom.repositories.AccountRepository;
import org.belotelov.diplom.repositories.StockRepository;
import org.belotelov.diplom.repositories.SupplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupplyServiceTest {
    @Mock
    private SupplyRepository supplyRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private SupplyService supplyService;
    @Captor
    private ArgumentCaptor<Stock> stockCaptor;
    private Supply supply;
    private Market market;
    private Account account;
    Stock existingStock;

    @BeforeEach
    public void init() {
        existingStock = new Stock();
        existingStock.setQuantity(10);
        existingStock.setId(1L);

        market = new Market();
        account = new Account();
        account.setBalance(100.0);
        market.setAccount(account);

        supply = new Supply();
        supply.setMarket(market);

        SupplyItem item1 = new SupplyItem();
        Nomenclature nomenclature1 = new Nomenclature();
        nomenclature1.setOptPrice(10.0);
        item1.setNomenclature(nomenclature1);
        item1.setQuantity(2);

        SupplyItem item2 = new SupplyItem();
        Nomenclature nomenclature2 = new Nomenclature();
        nomenclature2.setOptPrice(20.0);
        item2.setNomenclature(nomenclature2);
        item2.setQuantity(3);

        supply.setSupplyItems(Arrays.asList(item1, item2));
    }

    @Test
    public void testProcessSupplyMoney() {
        when(stockRepository.findByMarketAndNomenclature(any(Market.class), any(Nomenclature.class)))
                .thenReturn(Optional.empty());

        supplyService.processSupply(supply);

        verify(stockRepository, times(2)).save(any(Stock.class));

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());
        assertEquals(20.0, accountCaptor.getValue().getBalance(), 0.01);

        ArgumentCaptor<Supply> supplyCaptor = ArgumentCaptor.forClass(Supply.class);
        verify(supplyRepository).save(supplyCaptor.capture());
        assertTrue(supplyCaptor.getValue().getProcessed());
    }

    @Test
    public void testProcessSupplyStockIncreased() {
        supply = new Supply();
        market = new Market();
        account = new Account();
        account.setBalance(1000.0);
        market.setAccount(account);
        supply.setMarket(market);

        SupplyItem supplyItem = new SupplyItem();
        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setCode(9999);
        nomenclature.setOptPrice(50.0);
        existingStock.setNomenclature(nomenclature);
        supplyItem.setNomenclature(nomenclature);
        supplyItem.setQuantity(5);
        supply.setSupplyItems(List.of(supplyItem));

        when(stockRepository.findByMarketAndNomenclature(market, nomenclature))
                .thenReturn(Optional.of(existingStock));

        supplyService.processSupply(supply);

        verify(stockRepository).save(stockCaptor.capture());

        Stock updatedStock = stockCaptor.getValue();
        assertEquals(15, updatedStock.getQuantity());
    }
}
