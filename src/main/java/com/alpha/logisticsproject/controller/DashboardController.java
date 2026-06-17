package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alpha.logisticsproject.service.DashboardService;

@Controller
public class DashboardController {

    @Autowired private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showConsole(Model model, 
                              @RequestParam(defaultValue = "Guest") String username, 
                              @RequestParam(defaultValue = "USER") String role) {
        model.addAttribute("dashboard", dashboardService.getDashboardData());
        model.addAttribute("sessionUser", username);
        model.addAttribute("sessionRole", role.toUpperCase());
        return "index";
    }
}