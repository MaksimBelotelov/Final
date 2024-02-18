package org.belotelov.diplom.repositories;

import org.belotelov.diplom.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
