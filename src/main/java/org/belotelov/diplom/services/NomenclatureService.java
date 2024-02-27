package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.repositories.NomenclatureRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NomenclatureService {
    private NomenclatureRepo nomenclatureRepo;
    public List<Nomenclature> getAllNomenclatures() {
        return nomenclatureRepo.findAll();
    }

    public List<Nomenclature> getNomenclaturesByCategory(Long categoryId) {
        return nomenclatureRepo.findNomenclaturesByCategory_Id(categoryId).orElse(null);
    }
}
