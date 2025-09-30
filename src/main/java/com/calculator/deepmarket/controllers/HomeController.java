package com.calculator.deepmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

class HomeController {
    // get mapping se utiliza en las funciones que mostran una vista
    // post mapping se utiliza para enviar datos

    @GetMapping("/home")
    public String Home(Model model) {
        model.addAttribute("view", "Home/HomeView");
        return "Layout/layout";
    }

    @GetMapping("/AcercaDe")
    public String AcercaDe(Model model){
        model.addAttribute("view", "Home/AcercaDe");
        return "Layout/layout";
    }
}
