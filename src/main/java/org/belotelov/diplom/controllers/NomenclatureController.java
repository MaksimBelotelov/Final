package org.belotelov.diplom.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.services.CategoryService;
import org.belotelov.diplom.services.NomenclatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        List<Nomenclature> listOfNoms = nomenclatureService.getNomenclaturesByCategory(id.longValue());
        model.addAttribute("nomenclatures",
                listOfNoms);
        return "noms";
    }

    @GetMapping("/new")
    public String showNewNomenclatureForm(Model model) {
        model.addAttribute("nomenclature", new Nomenclature());
        return "new-nom";
    }

    @PostMapping("/new")
    public String createNewNomenclature(@ModelAttribute("nomenclature") @Valid Nomenclature nomenclature,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return "new-nom";
        }
        nomenclatureService.addNewNomenclature(nomenclature);
        return "redirect:/nom";
    }

    @GetMapping("/update/{code}")
    public String showNomenclatureUpdateForm(@PathVariable("code") Integer code, Model model) {
        Nomenclature nomenclature = nomenclatureService.getNomenclatureByCode(code);
        model.addAttribute("nomenclature", nomenclature);
        return "update-nom";
    }

    @PostMapping("/update")
    public String updateNomenclature(@ModelAttribute("nomenclature") Nomenclature nomenclature) {
        nomenclatureService.updateNomenclature(nomenclature);
        return "redirect:/nom";
    }

    @GetMapping("/delete/{code}")
    public String deleteNomenclature(@PathVariable("code") Integer code) {
        nomenclatureService.deleteNomenclatureByCode(code);
        return "redirect:/nom";
    }

    @ModelAttribute("categoriesList")
    public List<Category> getListOfCats() {
        return categoryService.getAllCategories();
    }
}
