package org.belotelov.diplom.services;

import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.repositories.SupplyItemRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplyItemServiceTest {
    @Mock
    private SupplyItemRepository supplyItemRepository;
    @InjectMocks
    private SupplyItemService supplyItemService;
    private Supply supply;
    private Nomenclature nomenclature;


    @BeforeEach
    public void init() {
        supply = new Supply();
        nomenclature = new Nomenclature();
    }

    // Проверяем, что при добавлении в поставку существующей номенклатуры, увеличивается её количество
    @Test
    public void addExistingNomItemTest() {
        SupplyItem supplyItem = new SupplyItem();
        supplyItem.setQuantity(2);

        // Задаем поведение мок-репозитория
        when(supplyItemRepository.findSupplyItemBySupplyAndNomenclature(supply, nomenclature))
                .thenReturn(Optional.of(supplyItem));

        supplyItemService.addItemToSupply(supply, nomenclature);

        assertEquals(3, supplyItem.getQuantity());
        verify(supplyItemRepository).save(supplyItem);
    }


    // Проверяем, что при добавлении номенклатуры, которой ещё нет в текущей поставке, номенклатура заносится в базу.
    // Ожидаем, что сервис должен попытаться добавить новый элемент и сохранить его.
    @Test
    public void addItemToSupplyWhenItemIsNewTest() {
        supply = new Supply();
        nomenclature = new Nomenclature();

        // Задаем поведение mock-репозитория, который имитирует отсутствие элемента с переданными параметрами
        when(supplyItemRepository.findSupplyItemBySupplyAndNomenclature(supply, nomenclature))
                .thenReturn(Optional.empty());

        // Пробуем добавить элемент
        supplyItemService.addItemToSupply(supply, nomenclature);

        // Проверяем, что вызывался метод save
        verify(supplyItemRepository).save(any(SupplyItem.class));
    }
}