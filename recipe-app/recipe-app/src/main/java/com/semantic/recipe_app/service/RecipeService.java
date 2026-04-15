package com.semantic.recipe_app.service;

import com.semantic.recipe_app.model.Recipe;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    private final String XML_PATH = "recipe-app/src/main/resources/xml/data.xml";

    public List<Recipe> getAllRecipesFromXml() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            File inputFile = new File(XML_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("recipe");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    String difficulty = eElement.getElementsByTagName("difficulty").item(0).getTextContent();

                    // Handle multiple cuisines
                    List<String> cuisines = new ArrayList<>();
                    NodeList cuisineNodes = eElement.getElementsByTagName("cuisine");
                    for (int j = 0; j < cuisineNodes.getLength(); j++) {
                        cuisines.add(cuisineNodes.item(j).getTextContent());
                    }

                    recipes.add(new Recipe(title, cuisines, difficulty));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
