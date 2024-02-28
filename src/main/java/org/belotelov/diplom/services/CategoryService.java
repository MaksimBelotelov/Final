package org.belotelov.diplom.services;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.repositories.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    CategoryRepo categoryRepo;
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
