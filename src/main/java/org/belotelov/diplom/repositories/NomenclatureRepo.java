package org.belotelov.diplom.repositories;

import jakarta.transaction.Transactional;
import org.belotelov.diplom.models.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NomenclatureRepo extends JpaRepository<Nomenclature, Integer> {
    Optional<List<Nomenclature>> findNomenclaturesByCategory_Id(Long categoryId);
    @Transactional
    void removeNomenclatureByCode(Integer code);
}
