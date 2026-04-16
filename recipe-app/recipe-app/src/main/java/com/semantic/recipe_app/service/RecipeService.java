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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList; // Just in case it's missing
import org.w3c.dom.Element; // Just in case it's missing

@Service
public class RecipeService {

    private final String XML_PATH = "src/main/resources/xml/data.xml";

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

// Task 5: Add user to XML
    public void addUserToXml(String name, String surname, String skillLevel, String preferredCuisine) {
    try {
        // Pointing to your specific XML file location
        File xmlFile = new File("src/main/resources/xml/data.xml");
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);

        // Get the root element (I am assuming it's <data> or <app> based on your single file setup)
        Element rootElement = doc.getDocumentElement();

        // Create new <user> element
        Element user = doc.createElement("user");

        // Create and append <name>
        Element nameElement = doc.createElement("name");
        nameElement.setTextContent(name);
        user.appendChild(nameElement);

        // Create and append <surname>
        Element surnameElement = doc.createElement("surname");
        surnameElement.setTextContent(surname);
        user.appendChild(surnameElement);

        // Create and append <skillLevel>
        Element skillElement = doc.createElement("skillLevel");
        skillElement.setTextContent(skillLevel);
        user.appendChild(skillElement);

        // Create and append <preferredCuisine>
        Element cuisineElement = doc.createElement("preferredCuisine");
        cuisineElement.setTextContent(preferredCuisine);
        user.appendChild(cuisineElement);

        // Append the whole user to the root of the XML
        // Note: If you have a specific <users> tag grouping them inside data.xml, 
        // you would use doc.getElementsByTagName("users").item(0).appendChild(user) instead.
        rootElement.appendChild(user);

        // Save the updated document back to the file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

//task 6: Recommend recipes based on user's skill level
    public List<Recipe> recommendBySkillLevel() {
    List<Recipe> recommendedRecipes = new ArrayList<>();
    try {
        File xmlFile = new File("src/main/resources/xml/data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        // 1. Get the first user's skill level using XPath
        // //user[1] selects the first user node in the entire document
        String skillExpression = "//user[1]/skillLevel/text()";
        String userSkill = (String) xPath.compile(skillExpression).evaluate(doc, XPathConstants.STRING);

        if (userSkill == null || userSkill.isEmpty()) {
            return recommendedRecipes; // No user found
        }

        // 2. Get recipes matching that skill level using XPath
        // Matches <recipe> nodes where the <difficulty> child equals the userSkill
        String recipeExpression = "//recipe[difficulty='" + userSkill + "']";
        NodeList recipeNodes = (NodeList) xPath.compile(recipeExpression).evaluate(doc, XPathConstants.NODESET);

        // 3. Convert XML nodes to Java Objects
        for (int i = 0; i < recipeNodes.getLength(); i++) {
            Node node = recipeNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                String title = "";
                String difficulty = "";
                List<String> cuisines = new ArrayList<>(); // Empty list to satisfy your constructor
                
                // Fetch the title
                Node titleNode = element.getElementsByTagName("title").item(0);
                if (titleNode != null) {
                    title = titleNode.getTextContent();
                }
                
                // Fetch the difficulty
                Node diffNode = element.getElementsByTagName("difficulty").item(0);
                if (diffNode != null) {
                    difficulty = diffNode.getTextContent(); 
                }

                // Use the constructor defined in your Recipe.java model
                Recipe recipe = new Recipe(title, cuisines, difficulty);
                
                recommendedRecipes.add(recipe);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return recommendedRecipes;
}

//task 7: Recommend recipes based on user's skill level AND preferred cuisine
public List<Recipe> recommendBySkillAndCuisine() {
    List<Recipe> recommendedRecipes = new ArrayList<>();
    try {
        File xmlFile = new File("src/main/resources/xml/data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        XPath xPath = XPathFactory.newInstance().newXPath();

        // 1. Get the first user's skill AND cuisine
        String skillExpression = "//user[1]/skillLevel/text()";
        String cuisineExpression = "//user[1]/preferredCuisine/text()";
        
        String userSkill = (String) xPath.compile(skillExpression).evaluate(doc, XPathConstants.STRING);
        String userCuisine = (String) xPath.compile(cuisineExpression).evaluate(doc, XPathConstants.STRING);

        if (userSkill == null || userSkill.isEmpty() || userCuisine == null || userCuisine.isEmpty()) {
            return recommendedRecipes; // Missing user data
        }

        // 2. Get recipes matching BOTH skill and at least one of their cuisines
        // This XPath checks if difficulty matches AND if any <cuisine> child of <cuisines> matches
        String recipeExpression = "//recipe[difficulty='" + userSkill + "' and cuisines/cuisine='" + userCuisine + "']";
        NodeList recipeNodes = (NodeList) xPath.compile(recipeExpression).evaluate(doc, XPathConstants.NODESET);

        // 3. Convert XML nodes to Java Objects
        for (int i = 0; i < recipeNodes.getLength(); i++) {
            Node node = recipeNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                String title = "";
                String difficulty = "";
                List<String> cuisines = new ArrayList<>(); // Empty list for constructor
                
                Node titleNode = element.getElementsByTagName("title").item(0);
                if (titleNode != null) title = titleNode.getTextContent();
                
                Node diffNode = element.getElementsByTagName("difficulty").item(0);
                if (diffNode != null) difficulty = diffNode.getTextContent(); 

                Recipe recipe = new Recipe(title, cuisines, difficulty);
                recommendedRecipes.add(recipe);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return recommendedRecipes;
}

}
