package com.semantic.recipe_app.model;

import java.util.List;

public class Recipe {
    public String title;
    public List<String> cuisines; // Italian, Asian (or other two types of cuisine)
    public String difficulty;     // Beginner, Intermediate, Advanced

    public Recipe(String title, List<String> cuisines, String difficulty) {
        this.title = title;
        this.cuisines = cuisines;
        this.difficulty = difficulty;
    }

    // needed for 3rd task
    public String getTitle() { return title; }
    public List<String> getCuisines() { return cuisines; }
    public String getDifficulty() { return difficulty; }
}