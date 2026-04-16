package com.semantic.recipe_app.controller;

import com.semantic.recipe_app.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import com.semantic.recipe_app.model.Recipe;

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

// Task 5: Add user to XML
    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUserSubmit(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("skillLevel") String skillLevel,
            @RequestParam("preferredCuisine") String preferredCuisine) {
        
        recipeService.addUserToXml(name, surname, skillLevel, preferredCuisine);
        
        return "redirect:/recipes"; 
    }

// Task 6: Recommend recipes based on user's skill level
    @GetMapping("/recommendations/skill")
    public String showSkillRecommendations(Model model) {
        // Fetch the recommended recipes from the service
        List<Recipe> recipes = recipeService.recommendBySkillLevel();
        
        // Pass the list to the frontend template under the name "recommendedRecipes"
        model.addAttribute("recommendedRecipes", recipes);
        
        return "recommendations"; // Maps to recommendations.html
    }
}