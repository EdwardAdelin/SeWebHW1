package com.semantic.recipe_app.service;

import com.semantic.recipe_app.model.Recipe;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    public void addRecipeToFile(String title, String cuisine1, String cuisine2, String difficulty) {
        try {
            File inputFile = new File(XML_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Find the <recipes> parent node
            Node recipesNode = doc.getElementsByTagName("recipes").item(0);

            // 2. Create the new <recipe> element
            Element newRecipe = doc.createElement("recipe");

            // Create Title
            Element titleNode = doc.createElement("title");
            titleNode.setTextContent(title);
            newRecipe.appendChild(titleNode);

            // Create Cuisines Container
            Element cuisinesNode = doc.createElement("cuisines");
            Element c1 = doc.createElement("cuisine");
            c1.setTextContent(cuisine1);
            Element c2 = doc.createElement("cuisine");
            c2.setTextContent(cuisine2);
            cuisinesNode.appendChild(c1);
            cuisinesNode.appendChild(c2);
            newRecipe.appendChild(cuisinesNode);

            // Create Difficulty
            Element diffNode = doc.createElement("difficulty");
            diffNode.setTextContent(difficulty);
            newRecipe.appendChild(diffNode);

            // 3. Append the new recipe to the list
            recipesNode.appendChild(newRecipe);

            // 4. SAVE the modified XML back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // This line makes the XML look "pretty" with indentations
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_PATH));
            transformer.transform(source, result);

            System.out.println("SUCCESS: New recipe added to XML and saved to disk.");

        } catch (Exception e) {
            System.err.println("Error adding recipe: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
