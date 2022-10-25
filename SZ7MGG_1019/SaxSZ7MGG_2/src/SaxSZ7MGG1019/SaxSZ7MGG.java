package SaxSZ7MGG1019;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxSZ7MGG {

    public static void main(String[] args) {
        try {
            /*Dokumentumolvasó létrehozása, a gyár objektumot a SAXParserFactory osztály newInstance metódusa
        	segítségével készítjük el. */

            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            //példányosítja a SAX értelmezőt, a visszakapott gyár állítja elő a SAX elemzőt.
            SAXParser saxParser = saxParserFactory.newSAXParser();

            //saját eseménykezelő objektum létrehozása
            SaxHandler handler = new SaxHandler();

            //dokumentum értelmezési folyamatának elindítása,
            //A parse metódus első paramétere a beolvasandó XML fájl neve, a második paramétere a kezelőobjektum
            saxParser.parse(new File("DCS_kurzusfelvetel.xml"), handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

//Tartalomkezelő keret létrehozása, valamint az esemény- és hibakezelő metódusok definiálása

//a DefaultHandler-ből való leszármaztatással és a megfelelő metódusainak definiálásával történik.

class SaxHandler extends DefaultHandler {

    private int indent = 0;

    private String formatAttributes(Attributes attributes) {
        int attrLength = attributes.getLength();
        if (attrLength == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(", {");
        for (int i = 0; i < attrLength; i++) {
            sb.append(attributes.getLocalName(i) + "=" + attributes.getValue(i));
            if (i < attrLength - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void indent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }

//Esemény kezelő metódusok létrehozása, startElement metódus.

    //elem kezdete és vége
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        indent++;   // behúz mindent
        indent();   //start húzza be
        System.out.println(qName + formatAttributes(attributes) + " start");
    }

    //endElement metódust újraimplementáljuk.
    @Override
    public void endElement(String uri, String localName, String qName) {
        indent();    //end húzza be
        indent--;    // behúzás csökkentése
        System.out.println(qName + " end");
    }

    //Szövegelem feldolgozása, characters metódust újraimplementáljuk.
    @Override
    public void characters(char ch[], int start, int length) {  //szövegtartalmat egy tömbbe tároljuk le
        String chars = new String(ch, start, length).trim();
        if (!chars.isEmpty()) {
            indent++;        // behúz minden
            indent();        // karakter behúzása
            indent--;        // behúzás csökkentése
            System.out.println(chars);
        }
    }
}