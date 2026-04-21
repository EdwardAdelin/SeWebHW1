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

import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/")
    public String showHomePage() {
        return "index"; 
    }

    @GetMapping("/add-recipe")
    public String showAddRecipeForm() {
        return "add-recipe"; 
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@RequestParam String title,
                            @RequestParam String cuisine1,
                            @RequestParam String cuisine2,
                            @RequestParam String difficulty) {

        // VALIDATION ( Task 4)
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

// Task 7: Recommend recipes based on user's skill level AND preferred cuisine
    @GetMapping("/recommendations/advanced")
    public String showAdvancedRecommendations(Model model) {
        List<Recipe> recipes = recipeService.recommendBySkillAndCuisine();
        model.addAttribute("recommendedRecipes", recipes);
        
        // We will create a new HTML file for this to keep things organized
        return "recommendations-advanced"; 
    }

// Task 8: XSLT View
    @GetMapping(value = "/recipes-xsl", produces = "text/html")
    @ResponseBody
    public String viewRecipesViaXsl(@RequestParam(value = "userName", defaultValue = "John") String userName) {
        return recipeService.generateHtmlFromXsl(userName);
    }

    // Task 11 UI for Task 8
    @GetMapping("/select-user-xsl")
    public String selectUserXsl() {
        return "select-user-xsl";
    }

    // Task 9: Recipe Details endpoint
    @GetMapping("/recipe-details")
    public String getRecipeDetails(@RequestParam("title") String title, Model model) {
        Recipe recipe = recipeService.getRecipeDetails(title);
        model.addAttribute("recipe", recipe);
        return "recipe-details";
    }

    // Task 10: Cuisine search form
    @GetMapping("/search-cuisine")
    public String searchCuisineForm() {
        return "search-cuisine";
    }

    // Task 10: Cuisine search results
    @GetMapping("/recipes/cuisine")
    public String recipesByCuisine(@RequestParam("cuisine") String cuisine, Model model) {
        model.addAttribute("recipes", recipeService.getRecipesByCuisine(cuisine));
        model.addAttribute("selectedCuisine", cuisine);
        return "recipes"; // Reusing the existing recipes.html to display results!
    }
}