package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    Supply findSupplyById(Long id);
}
