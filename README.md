Recipe Recommendation System (Semantic Web Project)
===================================================

Project Overview
----------------

This is a Java-based web application designed to recommend recipes to users based on their cooking skills and cuisine preferences. The project demonstrates the core principles of the **Semantic Web**, utilizing **XML** for data storage, **XSD** for validation, and **XPath/XQuery/XSL** for data manipulation and presentation.

**Public Repository Link:** 
https://github.com/EdwardAdelin/SeWebHW1.git

Team Members & Contributions
-------------------------------

**JERCĂU Hadasa Ștefana**

Backend Logic, XML Scraping (BBC Good Food), XPath/XQuery implementation, XSD Schema design.

**FINICHIU Eduard Adelin**

UI/UX Design, XSL Transformations, Recipe CRUD operations, Input Validation logic.

Key Features
---------------

*   **Automated Scraping:** Recipes are dynamically pulled from _BBC Good Food_ to populate the initial database.
    
*   **Smart Recommendations:** \* Filter by **Cooking Skill Level** (XPath).
    
    *   Filter by **Skill Level + Cuisine Preference** (XQuery).
        
*   **Dynamic UI:** Recipes are rendered via **XSL**.
    
    *   🟩 **Green Background:** General recipes.
        
    *   🟨 **Yellow Background:** Recipes matching your specific skill level.
        
*   **Data Management:** Full CRUD capability to add recipes and users directly into the XML storage with real-time validation.
    
*   **Detailed View:** Specific recipe lookup using filtered XPath queries.
    

Tech Stack
--------------

*   **Language:** Java
    
*   **Data Format:** XML
    
*   **Validation:** XSD (XML Schema Definition)
    
*   **Querying:** XPath & XQuery
    
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
    
*   \[ \] XPath/XQuery Recommendation Logic (2.0 pts)
    
*   \[ \] Filter by Cuisine & Detailed View (2.0 pts)
    
*   \[ \] Intuitive GUI (1.0 pt)
    

How to Run
--------------

1.  Clone the repository: git clone https://github.com/EdwardAdelin/SeWebHW1.git
    
2.  Import the project into your IDE (IntelliJ/Eclipse).
    
3.  Ensure you have a Java Web Server (like Tomcat) configured.
    
4.  Run the application and navigate to http://localhost:8080.
