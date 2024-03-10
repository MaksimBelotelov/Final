package org.belotelov.diplom.controllers;

import lombok.AllArgsConstructor;
import org.belotelov.diplom.models.Market;
import org.belotelov.diplom.models.Nomenclature;
import org.belotelov.diplom.models.WriteOff;
import org.belotelov.diplom.services.MarketService;
import org.belotelov.diplom.services.NomenclatureService;
import org.belotelov.diplom.services.WriteOffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("writeoff")
@AllArgsConstructor
public class WriteOffController {
    private WriteOffService writeOffService;
    private MarketService marketService;
    private NomenclatureService nomenclatureService;

    @GetMapping()
    public String showWriteOffs(Model model) {
        model.addAttribute("writeOffs", writeOffService.getAllWriteOffs());
        return "writeoffs";
    }

    @GetMapping("/new")
    public String showWriteOffForm(Model model) {
        List<Market> marketList = marketService.getAllMarkets();
        List<Nomenclature> nomenclatureList = nomenclatureService.getAllNomenclatures();

        model.addAttribute("nomenclatureList", nomenclatureList);
        model.addAttribute("marketlist", marketList);
        model.addAttribute("writeoff", new WriteOff());

        return "new-write-off";
    }

    @PostMapping("/new")
    public String createNewWriteOff(@ModelAttribute("writeoff") WriteOff writeOff) {
        writeOffService.processWriteOff(writeOff);
        return "redirect:/writeoff";
    }
}
