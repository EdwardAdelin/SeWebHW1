Recipe Recommendation System (Semantic Web Project)
===================================================

Project Overview
----------------

This is a Java-based web application designed to recommend recipes to users based on their cooking skills and cuisine preferences. The project demonstrates the core principles of the **Semantic Web**, utilizing **XML** for data storage, **XSD** for validation, and **XPath/XSL** for data manipulation and presentation.

**Public Repository Link:** https://github.com/EdwardAdelin/SeWebHW1.git

Team Members & Contributions
-------------------------------

**JERCĂU Hadasa Ștefana**

* **Core Data Management (Tasks 3, 4 & 5):** Managed the DOM parsing logic to read data into memory for UI display, and built the interactive forms (with validation) allowing users to add new recipes and user profiles directly into the XML storage.
* **Recommendation Engine (Tasks 6 & 7):** Wrote the XPath queries to build the recommendation system, filtering recipes based strictly on the user's cooking skill level, as well as providing advanced filtering based on both skill level and preferred cuisine.
* **XSL Transformations (Task 8):** Implemented the XSLT logic to dynamically render the HTML list of recipes, applying conditional formatting (yellow/green backgrounds) to highlight recipes that match the selected user's profile.

**FINICHIU Eduard Adelin**

* **Data Generation & Validation (Tasks 1 & 2):** Implemented the automated web scraping logic using JSoup to populate the initial XML database and designed the XSD schema to enforce data validation rules.
* **Targeted Data Retrieval (Tasks 9 & 10):** Developed the XPath querying logic necessary to filter and display the full details of specific recipes, as well as the functionality to search and retrieve recipes matching a chosen cuisine type.
* **Frontend & UI Design (Task 11):** Designed the intuitive graphical user interface (GUI) and navigation for the web application, including the specific interface for dynamically selecting users to trigger XSL views.

Key Features
---------------

* **Automated Scraping:** Recipes are dynamically pulled from _BBC Good Food_ using JSoup to populate the initial database.
* **Smart Recommendations:** * Filter by **Cooking Skill Level** (XPath).
    * Filter by **Skill Level + Cuisine Preference** (XPath).
* **Dynamic UI:** Recipes are rendered via **XSL**.
    * 🟩 **Green Background:** General recipes.
    * 🟨 **Yellow Background:** Recipes matching your specific skill level.
* **Data Management:** Form-based capabilities to add recipes and users directly into the XML storage with real-time validation against our XSD schema.
* **Detailed View:** Specific recipe lookup and cuisine filtering using targeted XPath queries.

Tech Stack
--------------

* **Framework:** Java Spring Boot
* **Data Format:** XML
* **Validation:** XSD (XML Schema Definition)
* **Querying:** XPath
* **Styling/Transformation:** XSLT (Extensible Stylesheet Language Transformations)
* **Frontend:** Thymeleaf, HTML5, CSS3 (Bootstrap for responsiveness)
* **Web Scraping:** JSoup

Project Structure
--------------------

* `/src/main/java/.../`: Contains all Java source code (Controllers, Services, Scraper).
* `/src/main/resources/xml/`: Contains our data storage (`data.xml`), validation schema (`schema.xsd`), and XSL stylesheet (`recipes.xsl`).
* `/src/main/resources/templates/`: Contains all the Thymeleaf HTML views for the web interface.

Grading Checklist (Internal Reference)
-----------------------------------------

* [x] Scraping from BBC Good Food (1.5 pts)
* [x] XSD/DTD Validation (0.5 pts)
* [x] Recipe Display via XSL (1.5 pts)
* [x] Recipe & User Addition Forms (1.5 pts)
* [x] XPath Recommendation Logic (2.0 pts)
* [x] Filter by Cuisine & Detailed View (2.0 pts)
* [x] Intuitive GUI (1.0 pt)

How to Run
--------------

1. Clone the repository: `git clone https://github.com/EdwardAdelin/SeWebHW1.git`
2. Open the project in your preferred IDE (IntelliJ IDEA or Eclipse).
3. Let Maven download the required dependencies.
4. Run the `RecipeAppApplication.java` main class. (Since it is a Spring Boot app, it has an embedded web server, so no external Tomcat setup is needed!)
5. Open your browser and navigate to `http://localhost:8080`.