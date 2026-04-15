package com.semantic.recipe_app.service;

import org.springframework.stereotype.Service;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringReader;

@Service
public class XmlValidator {

    private final String XSD_PATH = "src/main/resources/xml/schema.xsd";

    public boolean validateRecipeXml(String xmlFragment) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(XSD_PATH));
            Validator validator = schema.newValidator();

            // We wrap the fragment in a temporary root to check it against the schema
            validator.validate(new StreamSource(new StringReader(xmlFragment)));
            return true;
        } catch (Exception e) {
            System.out.println("Validation Error: " + e.getMessage());
            return false;
        }
    }
}
