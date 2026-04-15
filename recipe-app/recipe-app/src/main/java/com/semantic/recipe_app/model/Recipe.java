package com.semantic.recipe_app.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Recipe {
    public String title;
    public List<String> cuisines; // Italian, Asian (or other two types of cuisine)
    public String difficulty;     // Beginner, Intermediate, Advanced
    //private final String XML_PATH = "recipe-app/src/main/resources/xml/data.xml";

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