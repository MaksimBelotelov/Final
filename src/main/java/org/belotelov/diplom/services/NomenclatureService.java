package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.repositories.NomenclatureRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void addNewNomenclature(Nomenclature nomenclature) {
        nomenclatureRepo.save(nomenclature);
    }

    public Nomenclature getNomenclatureByCode(Integer code) { return nomenclatureRepo.findNomenclatureByCode(code); }

    public void deleteNomenclatureByCode(Integer code) {
        nomenclatureRepo.removeNomenclatureByCode(code);
    }

    public void updateNomenclature(Nomenclature nomenclature) {
        nomenclatureRepo.save(nomenclature);
    }
}
