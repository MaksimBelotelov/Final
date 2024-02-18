package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.repositories.NomenclatureRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/nom")
public class NomenclatureController {

    private NomenclatureRepo nomenclatureRepo;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("nomenclatures", nomenclatureRepo.findAll());
        return "noms";
    }
}
