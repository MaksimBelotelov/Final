package org.belotelov.diplom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {
    @GetMapping()
    public String getMainPage() {
        return "index";
    }

    @GetMapping("warehouse")
    public String redirectToWarehousePage() {
        return "warehouse-page";
    }
}
