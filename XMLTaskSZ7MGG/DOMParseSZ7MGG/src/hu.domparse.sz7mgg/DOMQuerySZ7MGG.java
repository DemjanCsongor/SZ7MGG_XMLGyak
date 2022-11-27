package hu.domparse.sz7mgg;

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

public class DOMQuerySZ7MGG {


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //file nev megadasa
        File file = new File("XMLSZ7MGG.xml");
        //parse
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        //gyokerelem kiiratasa
        System.out.print("Root element: ");
        System.out.println(doc.getDocumentElement().getNodeName());
        //ruhak mentese listaba
        NodeList nList = doc.getElementsByTagName("ruha");
        System.out.println("----------------------------");

        //ruhak es gyerek elemeik megjelenitese a consolon
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            System.out.println("\nCurrent Element : " + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                System.out.println(elem.getAttribute("ruhaId"));
                NodeList nList2 = elem.getChildNodes();
                for (int j = 0; j < nList2.getLength(); j++) {
                    Node node2 = nList2.item(j);
                    if (node2.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println(node2.getNodeName() + " : " + node2.getTextContent());
                    }
                }
            }
        }
    }
}
