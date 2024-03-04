package org.belotelov.diplom.services;

import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.repositories.NomenclatureRepo;
import org.belotelov.diplom.repositories.SupplyItemRepository;
import org.belotelov.diplom.repositories.SupplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(SupplyItemService.class)
public class SupplyItemServiceIntegrationTest {
    @Autowired
    private SupplyItemService supplyItemService;

    @Autowired
    private SupplyItemRepository supplyItemRepository;

    @Autowired
    private SupplyRepository supplyRepository; // Предполагается, что у вас есть репозиторий для Supply

    @Autowired
    private NomenclatureRepo nomenclatureRepository;

    @Test
    public void addItemToSupplyIntegrationTest() {
        Supply supply = supplyRepository.findSupplyById(3L);

        Nomenclature nomenclature = nomenclatureRepository.findNomenclatureByCode(1234);

        supplyItemService.addItemToSupply(supply, nomenclature);

        SupplyItem result = supplyItemRepository.findSupplyItemBySupplyAndNomenclature(supply, nomenclature).orElse(null);

        assertThat(result.getNomenclature()).isEqualTo(nomenclature);
        assertThat(result.getQuantity()).isEqualTo(1);
    }
}
