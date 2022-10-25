package SaxSZ7MGG1019;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

public class SaxSZ7MGG {
    public static void main(String[] args) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //sajat esemenykezelo objektum
            SaxHandler handler = new SaxHandler();
            saxParser.parse(new File("DCS_kurzusfelvetel.xml"), handler);
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
        }
    }
}

class SaxHandler extends DefaultHandler {

    private int indent = 0;

    private String formatAttributes(Attributes attributes) {
        int attrLength = attributes.getLength();
        if(attrLength == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(", {");
        for(int i = 0; i < attrLength; i++) {
            sb.append(attributes.getLocalName(i) + "=" + attributes.getValue(i));
            if(i < attrLength - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    private void indent() {
        for(int i = 0; i < indent; i++) {
            System.out.println("   ");
        }
    }
    //Esemeny kezelo metodusok letrehozasa startElement metodus.

    //elem kezdete es vege
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        indent++; //behuz mindent
        indent(); //start huzza be
        System.out.println(qName + formatAttributes(attributes) + " start");
    }
    //endElement metodust ujraimplementaljuk
    @Override
    public void endElement(String uri, String localName, String qName) {
        indent(); //end huzza be
        indent--; //behuzas csokkentese
        System.out.println(qName + " end");
    }
    //szovegelem feldolgozasa, characters metodust ujraimplementaljuk.
    @Override
    public void characters(char ch[], int start, int length) {
        String chars = new String(ch, start, length).trim();
        if(!chars.isEmpty()) {
            indent++;   //behuz mindent
            indent();   //karakter behuzasa
            indent--;   //behuzas csokkentese
            System.out.println(chars);
        }
    }

}