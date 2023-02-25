package com.example.backend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/","/home"})
    public String showHome(Model model){
        model.addAttribute("name", "Quanzip");
        return "home";
    }
}
