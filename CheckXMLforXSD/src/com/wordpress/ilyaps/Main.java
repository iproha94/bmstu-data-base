package com.wordpress.ilyaps;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class Main {

    /**
     * @param pathXml - путь к XML
     * @param pathXsd - путь к XSD
     */
    public static boolean checkXMLforXSD(String pathXml, String pathXsd) throws Exception {

        try {
            File xml = new File(pathXml);
            File xsd = new File(pathXsd);

            if (!xml.exists()) {
                System.out.println("Не найден XML " + pathXml);
                return false;
            }

            if (!xsd.exists()) {
                System.out.println("Не найден XSD " + pathXsd);
                return false;
            }

            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(pathXsd));
            Validator validator = schema.newValidator();

            ErrorHandler lenient = new ForgivingErrorHandler();
            validator.setErrorHandler(lenient);

            validator.validate(new StreamSource(pathXml));

            return true;
        } catch (SAXException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) throws Exception {
        boolean b = Main.checkXMLforXSD("ref.xml", "ref.xsd");
        System.out.println("XML соответствует XSD : " + b);

    }
}