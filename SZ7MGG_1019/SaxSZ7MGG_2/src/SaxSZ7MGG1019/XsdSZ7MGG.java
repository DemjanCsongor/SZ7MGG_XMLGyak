package SaxSZ7MGG1019;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XsdSZ7MGG {

public static void main(String[] args) {

    System.out.println("DCS_kurzusfelvetel.xml validates against DCS_kurzusfelvetel.xsd? "+
            validateXMLSchema("DCS_kurzusfelvetel.xsd", "DCS_kurzusfelvetel.xml"));
}

public static boolean validateXMLSchema(String xsdPath, String xmlPath){

    try {
        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
    } catch (IOException | SAXException e) {
        System.out.println("Exception: "+e.getMessage());
        return false;
    }
    return true;
}
}