package org.belotelov.diplom.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Category;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.repositories.MarketRepository;
import org.belotelov.diplom.services.MarketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/markets")
public class MarketController {
    private MarketRepository marketRepository;
    private MarketService marketService;
    @GetMapping()
    public String showMarkets(Model model) {
        model.addAttribute("markets", marketService.getAllMarkets());
        return "markets";
    }

    @GetMapping("/new")
    public String showNewMarketForm(Model model) {
        model.addAttribute("market", new Market());
        return "new-market";
    }

    @PostMapping("/new")
    public String createNewMarket(@ModelAttribute("market") @Valid Market market,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "new-market";
        marketService.addNewMarket(market);
        return "redirect:/markets";
    }
}
