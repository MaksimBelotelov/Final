package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.Supply;
import org.belotelov.diplom.models.SupplyItem;
import org.belotelov.diplom.services.MarketService;
import org.belotelov.diplom.services.NomenclatureService;
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
    private final NomenclatureService nomenclatureService;

    @GetMapping()
    public String showAllSupplies(Model model) {
        model.addAttribute("supplies", supplyService.getAllSupplies());
        return "supplies";
    }

    @GetMapping("/stock")
    public String showAllStocks(Model model) {
        model.addAttribute("stocks", supplyService.getAllStocks());
        return "stocks";
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
        Double total = 0.0;
        Supply supply = supplyService.getSupplyById(id.longValue());
        List<SupplyItem> listOfItems = supplyItemService.getSupplyItemsBySupplyId(id.longValue());
        for(SupplyItem item : listOfItems)
            total += item.getQuantity() * item.getNomenclature().getOptPrice();
        model.addAttribute("items", listOfItems);
        model.addAttribute("total", total);
        model.addAttribute("idOfSupply", id);
        model.addAttribute("supply", supply);

        return "current-supply";
    }

    @PostMapping("/open/add/{idofsupply}")
    public String addNomToSupply(@PathVariable("idofsupply") Long idOfSupply,
                                 @RequestParam("code") Integer code) {
        Nomenclature nom = nomenclatureService.getNomenclatureByCode(code);
        if (nom != null) {
            Supply supply = supplyService.getSupplyById(idOfSupply);
            if(supply != null ) {
                supplyItemService.addItemToSupply(supply, nom);
            }
        }
        return "redirect:/supply/open/" + idOfSupply;
    }

    @GetMapping("/provide/{idofsupply}")
    public String provideSupply(@PathVariable("idofsupply") Long idOfSupply) {
        Supply supply = supplyService.getSupplyById(idOfSupply);
        if(supply != null) {
            supplyService.processSupply(supply);
        }
        return "redirect:/supply";
    }


}
