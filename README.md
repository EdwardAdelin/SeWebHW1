Recipe Recommendation System (Semantic Web Project)
===================================================

Project Overview
----------------

This is a Java-based web application designed to recommend recipes to users based on their cooking skills and cuisine preferences. The project demonstrates the core principles of the **Semantic Web**, utilizing **XML** for data storage, **XSD** for validation, and **XPath/XSL** for data manipulation and presentation.

**Public Repository Link:** 
https://github.com/EdwardAdelin/SeWebHW1.git

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

*   **Automated Scraping:** Recipes are dynamically pulled from _BBC Good Food_ to populate the initial database.
    
*   **Smart Recommendations:** \* Filter by **Cooking Skill Level** (XPath).
    
    *   Filter by **Skill Level + Cuisine Preference**.

*   **Dynamic UI:** Recipes are rendered via **XSL**.
    
    *   🟩 **Green Background:** General recipes.
        
    *   🟨 **Yellow Background:** Recipes matching your specific skill level.
        
*   **Data Management:** Full Data Management capability to add recipes and users directly into the XML storage with real-time validation.
    
*   **Detailed View:** Specific recipe lookup using filtered XPath queries.
    

Tech Stack
--------------

*   **Language:** Java
    
*   **Data Format:** XML
    
*   **Validation:** XSD (XML Schema Definition)
    
*   **Querying:** XPath 
    
*   **Styling/Transformation:** XSL (Extensible Stylesheet Language)
    
*   **Frontend:** HTML5, CSS3 (Bootstrap for responsiveness)
    

Project Structure
--------------------

*   /src: Java source code for the web application and scraping logic.
    
*   /data: Contains recipes.xml and users.xml.
    
*   /schema: Contains schema.xsd for data validation.
    
*   /web: XSL files and web interface components.
    

Grading Checklist (Internal Reference)
-----------------------------------------

*   \[ \] Scraping from BBC Good Food (1.5 pts)
    
*   \[ \] XSD/DTD Validation (0.5 pts)
    
*   \[ \] Recipe Display via XSL (1.5 pts)
    
*   \[ \] Recipe & User Addition Forms (1.5 pts)
    
*   \[ \] XPath Recommendation Logic (2.0 pts)
    
*   \[ \] Filter by Cuisine & Detailed View (2.0 pts)
    
*   \[ \] Intuitive GUI (1.0 pt)
    

How to Run
--------------

1.  Clone the repository: git clone https://github.com/EdwardAdelin/SeWebHW1.git
    
2.  Import the project into your IDE (IntelliJ/Eclipse).
    
3.  Ensure you have a Java Web Server (like Tomcat) configured.
    
4.  Run the application and navigate to http://localhost:8080.
