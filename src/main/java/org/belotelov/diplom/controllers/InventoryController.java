package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.*;
import org.belotelov.diplom.services.InventoryItemService;
import org.belotelov.diplom.services.InventoryService;
import org.belotelov.diplom.services.MarketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private InventoryService inventoryService;
    private MarketService marketService;
    private InventoryItemService inventoryItemService;

    @GetMapping()
    public String showAllInvents(Model model) {
        model.addAttribute("inventories", inventoryService.getAllInvents());
        return "invents";
    }

    @GetMapping("/new")
    public String showNewInventForm(Model model) {
        List<Market> markets = marketService.getAllMarkets();
        model.addAttribute("marketsList", markets);
        model.addAttribute("inventory", new Inventory());
        return "new-inventory";
    }

    @PostMapping("/new")
    public String createNewInventory(@ModelAttribute("inventory") Inventory inventory) {
        inventoryService.createNewInventory(inventory);
        inventoryService.saveInventory(inventory);
        return "redirect:/inventory";
    }

    @GetMapping("/open/{id}")
    public String openInventory(@PathVariable("id") Integer id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id.longValue());

        model.addAttribute("inventory", inventory);

        return "current-inventory";
    }

    @GetMapping("/update/{invItemId}")
    public String showInventoryItemForm(@PathVariable("invItemId") Long id, Model model) {
        InventoryItem inventoryItem = inventoryItemService.getInventoryItemById(id);
        model.addAttribute("inventoryItem", inventoryItem);
        return "new-quantity";
    }

    @PostMapping("/update")
    public String saveInventoryItem(@ModelAttribute("inventoryItem") InventoryItem inventoryItem) {
        inventoryItemService.saveInventoryItem(inventoryItem);
        return "redirect:/inventory/open/" + inventoryItem.getInventory().getId();
    }

    @GetMapping("/process/{id}")
    public String processInventory(@PathVariable("id") Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        inventoryService.processInventory(inventory);
        return "redirect:/inventory";
    }


}