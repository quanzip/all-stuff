package com.example.backend.web.controller;

import com.example.backend.entity.Pet;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Controller for user, using thymeleaf", description = "minor template FE platform: thymeleaf")
public class PetController {
    @GetMapping(value = "/pet")
    public String showUserPage(Model model){
        Pet pet = new Pet(null, 8, "Ant");
        model.addAttribute("Mypet", pet);
        return "pet";
    }
}
