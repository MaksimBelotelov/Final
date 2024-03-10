package org.belotelov.diplom.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.*;
import org.belotelov.diplom.repositories.StockRepository;
import org.belotelov.diplom.repositories.WriteOffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WriteOffService {

    private WriteOffRepository writeOffRepository;
    private StockRepository stockRepository;

    public List<WriteOff> getAllWriteOffs() {
        return writeOffRepository.findAll();
    }

    public void saveWriteOff(WriteOff writeOff) {
        writeOffRepository.save(writeOff);
    }

    @Transactional
    public void processWriteOff(WriteOff writeOff) {
        Stock stock = stockRepository.findByMarketAndNomenclature(writeOff.getMarket(), writeOff.getNomenclature())
                .orElse(null);
        if (stock != null) {
            stock.setQuantity(stock.getQuantity() - 1);
            stockRepository.save(stock);
        } else {
            System.out.println("Товар не найден");
        }
        writeOffRepository.save(writeOff);
    }
}
