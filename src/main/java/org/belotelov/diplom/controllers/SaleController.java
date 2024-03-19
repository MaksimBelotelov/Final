package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.*;
import org.belotelov.diplom.services.MarketService;
import org.belotelov.diplom.services.NomenclatureService;
import org.belotelov.diplom.services.SaleItemService;
import org.belotelov.diplom.services.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/sale")
public class SaleController {
    private final MarketService marketService;
    private final SaleService saleService;
    private final SaleItemService saleItemService;
    private final NomenclatureService nomenclatureService;


    @GetMapping("/new")
    public String createNewSaleForm(Model model) {
        List<Market> markets = marketService.getAllMarkets();
        model.addAttribute("sale", new Sale());
        model.addAttribute("marketList", markets);
        return "new-sale";
    }

    @PostMapping("/new")
    public String createNewSale(@ModelAttribute("sale") Sale sale) {
        saleService.addSale(sale);
        return "redirect:/sale/new/" + sale.getId();
    }

    @GetMapping("/new/{id}")
    public String openSale(@PathVariable("id") Long id, Model model) {
        Double total = 0.0;
        List<SaleItem> listOfItems = saleItemService.getSaleItemsBySaleId(id);
        for(SaleItem item : listOfItems) total += item.getQuantity() * item.getNomenclature().getPrice();
        model.addAttribute("items", listOfItems);
        model.addAttribute("idOfSale", id);
        model.addAttribute("total", total);

        return "kassa-screen";
    }

    @PostMapping("/new/{idofsale}")
    public String addNomToSale(@PathVariable("idofsale") Long idOfSale,
                                 @RequestParam("code") Integer code) {
        Nomenclature nom = nomenclatureService.getNomenclatureByCode(code);
        if (nom != null) {
            Sale sale = saleService.getSaleById(idOfSale);
            if(sale != null ) {
                saleItemService.addItemToSale(sale, nom);
            }
        }
        return "redirect:/sale/new/" + idOfSale;
    }

    @GetMapping("/process/{idofsale}")
    public String processSale(@PathVariable("idofsale") Long idOfSale) {
        Sale sale = saleService.getSaleById(idOfSale);
        if(sale != null && !sale.getSaleItems().isEmpty())
            saleService.processSale(sale);
        else
            saleService.removeSale(sale);

        return "redirect:/sale/new";
    }
}