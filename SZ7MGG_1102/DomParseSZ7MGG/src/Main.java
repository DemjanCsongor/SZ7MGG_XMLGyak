import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SAXException,
        IOException, ParserConfigurationException {
        //XML file megnyitasa
        File xmlFile = new File("DomParseSZ7MGG/src/domsz7mgg/usersSZ7MGG.xml");

        //peldanyositjuk a DocumentBuilderFactory osztalyt a statikus newInstance
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //DOM fa eloallitasa
        Document doc = dBuilder.parse(xmlFile);
        //A parse() metodus elemzi az XML fajlt a Document.

        doc.getDocumentElement().normalize();
        //a dokumentum normalizalasa segit a helyes eredmenyek elereseben.

        System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
        //kiirjuk a dokumentum gyokerelemet

        //a fa megadott nevvel (user name) rendelkezo csomopontajaink osszegyujtese
        //csomopontook halmaza, adattagja es egy metodusa van.
        NodeList nList = doc.getElementsByTagName("user");

        for(int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent Element: "+ nNode.getNodeName());

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String uid = elem.getAttribute("id");
                Node node1 = elem.getElementsByTagName("firstname").item(0);
                String fname = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("lastname").item(0);
                String lname = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("profession").item(0);
                String pname = node3.getTextContent();

                System.out.println("User id: "+ uid);
                System.out.println("Firstname: "+ fname);
                System.out.println("Lastname: "+ lname);
                System.out.println("Profession: "+ pname);
            }
        }
    }
}