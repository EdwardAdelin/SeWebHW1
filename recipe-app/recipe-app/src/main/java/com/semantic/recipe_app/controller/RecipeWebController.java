package com.semantic.recipe_app.controller;

import com.semantic.recipe_app.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/add-recipe")
    public String showAddRecipeForm() {
        return "add-recipe"; // This matches the filename add-recipe.html
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@RequestParam String title,
                            @RequestParam String cuisine1,
                            @RequestParam String cuisine2,
                            @RequestParam String difficulty) {

        // VALIDATION (Requirement 4)
        if (title.trim().isEmpty() || cuisine1.trim().isEmpty() || cuisine2.trim().isEmpty()) {
            return "redirect:/add-recipe?error=missingFields";
        }

        recipeService.addRecipeToFile(title, cuisine1, cuisine2, difficulty);
        return "redirect:/recipes";
    }
}