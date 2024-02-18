package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NomenclatureRepo extends JpaRepository<Nomenclature, Integer> {
}
