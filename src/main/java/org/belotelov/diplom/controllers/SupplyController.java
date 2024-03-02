package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.services.MarketService;
import org.belotelov.diplom.services.SupplyItemService;
import org.belotelov.diplom.services.SupplyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/supply")
public class SupplyController {
    private final SupplyService supplyService;
    private final MarketService marketService;
    private final SupplyItemService supplyItemService;

    @GetMapping()
    public String showAllSupplies(Model model) {
        model.addAttribute("supplies", supplyService.getAllSupplies());
        return "supplies";
    }

    @GetMapping("/new")
    public String showNewSupplyForm(Model model) {
        List<Market> markets = marketService.getAllMarkets();
        model.addAttribute("marketsList", markets);
        model.addAttribute("supply", new Supply());
        return "new-supply";
    }

    @PostMapping("/new")
    public String createNewSupply(@ModelAttribute("supply") Supply supply) {
        supplyService.addSupply(supply);
        return "redirect:/supply";
    }

    @GetMapping("/open/{id}")
    public String openSupply(@PathVariable("id") Integer id, Model model) {
        List<SupplyItem> listOfItems = supplyItemService.getSupplyItemsBySupplyId(id.longValue());
        model.addAttribute("items",
                listOfItems);
        return "current-supply";
    }
}
