package hu.domparse.sz7mgg;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifySZ7MGG {

    public static void main(String[] args) {

        try {
            //file nev megadasa, parse
            File inputFile = new File("XMLSZ7MGG_toModified.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            //az elso es a masodik ruha adatainak mentese
            Node ruha = doc.getElementsByTagName("ruha").item(0);
            Node ruha1 = doc.getElementsByTagName("ruha").item(1);
            //gyokerelem mentese
            Node boltom = doc.getFirstChild();

            //elso ruhaId modositasa
            NamedNodeMap attr = ruha.getAttributes();
            Node nodeAttr = attr.getNamedItem("ruhaId");
            nodeAttr.setTextContent("5");

            //az elso ruha kategoriajanak modositasa hoodie-ről pulover-re
            NodeList list = ruha.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    if ("kategoria".equals(elem.getNodeName())) {
                        if("T-Shirts".equals(elem.getTextContent())) {
                            elem.setTextContent("Polo");
                        }
                    }
                }
            }

            //a masodik ruha nevenek modositasa DamnTheBestHoodie-rol KenyelmesPulcsi-re
            NodeList list1 = ruha1.getChildNodes();
            for (int i = 0; i < list1.getLength(); i++) {
                Node node1 = list1.item(i);
                if (node1.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem1 = (Element) node1;
                    if ("nev".equals(elem1.getNodeName())) {
                        if("DamnTheBestHoodie".equals(elem1.getTextContent())) {
                            elem1.setTextContent("KenyelmesPulcsi");
                        }
                    }
                }
            }

            //szamlak torlese
            NodeList childNodes = boltom.getChildNodes();
            for(int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);

                if("szamla".equals(node.getNodeName()))
                    boltom.removeChild(node);
            }

            //megjelenites a consolon
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            System.out.println("-----------New File-----------");
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
