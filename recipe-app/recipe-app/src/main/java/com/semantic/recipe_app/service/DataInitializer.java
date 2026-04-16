package com.semantic.recipe_app.service;

import com.semantic.recipe_app.model.Recipe;
import com.semantic.recipe_app.model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Service
public class DataInitializer {

    private final String XML_PATH = "src/main/resources/xml/data.xml";
    private final Random random = new Random();

    // Predefined values for random assignment
    private final List<String> CUISINES = Arrays.asList("Italian", "Asian", "French", "Mexican", "Indian", "Mediterranean");
    private final List<String> DIFFICULTIES = Arrays.asList("Beginner", "Intermediate", "Advanced");

    public void scrapeAndSaveData() throws IOException {
        List<Recipe> recipes = new ArrayList<>();
        Set<String> seenTitles = new HashSet<>();

        // 1. Scrape titles from BBC Good Food
        Document doc = Jsoup.connect("https://www.bbcgoodfood.com/recipes/collection/budget-autumn").get();
        Elements titles = doc.select("h2.heading-4, h4.heading-4, a.link.d-block"); // recipe titles
        System.out.println("Found " + titles.size() + " potential recipe titles. Will select 20 for XML.");

        for (Element titleElement : titles) {
            if (recipes.size() >= 20) break;

            String title = titleElement.text().trim();
            if (title.isEmpty() || title.contains("premium piece of content") ||
                    seenTitles.contains(title)) {
                continue;
            }

            seenTitles.add(title);

            // pick 2 distinct cuisines randomly
            String c1 = CUISINES.get(random.nextInt(CUISINES.size()));
            String c2 = CUISINES.get(random.nextInt(CUISINES.size()));
            while (c1.equals(c2)) c2 = CUISINES.get(random.nextInt(CUISINES.size()));

            // random difficulty
            String diff = DIFFICULTIES.get(random.nextInt(DIFFICULTIES.size()));

            recipes.add(new Recipe(title, Arrays.asList(c1, c2), diff));
        }

        // 2. Create 1 default user
        User defaultUser = new User("John", "Doe", "Beginner", "Italian");

        // 3. Save to XML
        saveToXml(recipes, defaultUser);
    }

    private void saveToXml(List<Recipe> recipes, User user) throws IOException {
        // This part ensures the directory exists!
        java.io.File file = new java.io.File(XML_PATH);
        java.io.File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); // This creates the src/main/resources/xml folder structure
        }

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<data xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"schema.xsd\">");

        // Save User
        xml.append("  <users>\n");
        xml.append("    <user>\n");
        xml.append("      <name>").append(user.name).append("</name>\n");
        xml.append("      <surname>").append(user.surname).append("</surname>\n");
        xml.append("      <skill>").append(user.cookingSkillLevel).append("</skill>\n");
        xml.append("      <preferredCuisine>").append(user.preferredCuisine).append("</preferredCuisine>\n");
        xml.append("    </user>\n");
        xml.append("  </users>\n");

        // Save Recipes
        xml.append("  <recipes>\n");
        for (Recipe r : recipes) {
            xml.append("    <recipe>\n");
            xml.append("      <title>").append(r.title.replace("&", "&amp;")).append("</title>\n");
            xml.append("      <cuisines>\n");
            for (String c : r.cuisines) xml.append("        <cuisine>").append(c).append("</cuisine>\n");
            xml.append("      </cuisines>\n");
            xml.append("      <difficulty>").append(r.difficulty).append("</difficulty>\n");
            xml.append("    </recipe>\n");
        }
        xml.append("  </recipes>\n");
        xml.append("</data>");

        try (FileWriter writer = new FileWriter(XML_PATH)) {
            writer.write(xml.toString());
        }
    }
}
