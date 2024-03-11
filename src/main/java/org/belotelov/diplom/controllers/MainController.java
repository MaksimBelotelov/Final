package org.belotelov.diplom.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
