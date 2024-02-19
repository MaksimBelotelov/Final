package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.repositories.MarketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/markets")
public class MarketController {

    private MarketRepository marketRepository;
    @GetMapping()
    public String showMarkets(Model model) {
        model.addAttribute("markets", marketRepository.findAll());
        return "markets";
    }
}
