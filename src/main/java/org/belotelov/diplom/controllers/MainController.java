package org.belotelov.diplom.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Account;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Sale;
import org.belotelov.diplom.services.DashboardService;
import org.belotelov.diplom.services.MarketService;
import org.belotelov.diplom.services.SaleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping()
public class MainController {
    SaleService saleService;
    MarketService marketService;
    @GetMapping()
    public String getMainPage(Model model) {
        Double revenue = 0.0;
        List<Market> listOfMarkets = marketService.getAllMarkets();
        List<Sale> listOfSales = saleService.getSalesByDate(LocalDate.now());
        for(Sale sale : listOfSales) revenue += sale.getTotal();

        model.addAttribute("markets", listOfMarkets);
        model.addAttribute("sales", listOfSales);
        model.addAttribute("revenue", revenue);

        return "index";
    }

    @GetMapping("warehouse")
    public String redirectToWarehousePage() {
        return "warehouse-page";
    }

    @GetMapping("accessdenided")
    public String redirectToNoAccessPage() { return "no-access"; }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            request.getSession().invalidate();
        return "redirect:/";
    }


}
