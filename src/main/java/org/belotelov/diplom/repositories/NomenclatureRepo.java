package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NomenclatureRepo extends JpaRepository<Nomenclature, Integer> {
    Optional<List<Nomenclature>> findNomenclaturesByCategory_Id(Long categoryId);
}
