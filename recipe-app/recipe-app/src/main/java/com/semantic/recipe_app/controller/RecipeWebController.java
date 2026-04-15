package com.semantic.recipe_app.controller;

import com.semantic.recipe_app.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeWebController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public String getRecipes(Model model) {
        // Task 3: Read in memory
        model.addAttribute("recipes", recipeService.getAllRecipesFromXml());
        // Task 3: Show in UI
        return "recipes";
    }
}