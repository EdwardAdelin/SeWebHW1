package com.semantic.recipe_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.semantic.recipe_app.service.DataInitializer;

@SpringBootApplication
public class RecipeAppApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(RecipeAppApplication.class, args);


		// Uncomment to run scraper and create XML on startup
		DataInitializer init = context.getBean(DataInitializer.class);
		try {
			init.scrapeAndSaveData();
			System.out.println("SUCCESS: XML file created with scraped recipes!");
		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}

