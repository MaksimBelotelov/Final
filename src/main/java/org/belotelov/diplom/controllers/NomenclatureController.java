package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Nomenclature;
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
}
