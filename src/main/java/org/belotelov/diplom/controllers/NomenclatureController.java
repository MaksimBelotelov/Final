package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.services.CategoryService;
import org.belotelov.diplom.services.NomenclatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/nom")
public class NomenclatureController {
    private NomenclatureService nomenclatureService;
    private CategoryService categoryService;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("nomenclatures", nomenclatureService.getAllNomenclatures());
        return "noms";
    }

    @GetMapping("/{id}")
    public String showProductsByCat(@PathVariable("id") Integer id, Model model) {
        List<Nomenclature> listOfNoms = nomenclatureService.getNomenclaturesByCategory(id.longValue()); //Debug
        model.addAttribute("nomenclatures",
                listOfNoms);
        return "noms";
    }

    @GetMapping("/new")
    public String showNewNomenclatureForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categoriesList", categories);
        model.addAttribute("nomenclature", new Nomenclature());
        return "new-nom";
    }

    @PostMapping("/new")
    public String createNewNomenclature(@ModelAttribute("nomenclature") Nomenclature nomenclature) {
        nomenclatureService.addNewNomenclature(nomenclature);
        return "redirect:/nom";
    }

    @GetMapping("/delete/{code}")
    public String deleteNomenclature(@PathVariable("code") Integer code) {
        nomenclatureService.deleteNomenclatureByCode(code);
        return "redirect:/nom";
    }
}
