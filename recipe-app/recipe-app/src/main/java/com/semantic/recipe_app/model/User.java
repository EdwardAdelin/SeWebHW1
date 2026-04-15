package com.semantic.recipe_app.model;

public class User {
    public String name;
    public String surname;
    public String cookingSkillLevel;
    public String preferredCuisine;

    public User(String name, String surname, String skill, String cuisine) {
        this.name = name;
        this.surname = surname;
        this.cookingSkillLevel = skill;
        this.preferredCuisine = cuisine;
    }
}
