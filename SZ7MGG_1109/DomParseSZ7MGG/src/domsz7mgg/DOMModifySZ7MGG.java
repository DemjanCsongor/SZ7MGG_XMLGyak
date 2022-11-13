package domsz7mgg;

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
            File inputFile = new File("carsSZ7MGG.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(inputFile);

            Node cars = doc.getFirstChild();

            Node supercar = doc.getElementsByTagName("supercars").item(0);

            //szuper auto attributumának módositassa
            NamedNodeMap attr = supercar.getAttributes();
            Node nodeAttr = attr.getNamedItem("company");
            nodeAttr.setTextContent("Lamborghini");

            //loop the supercar child node
            NodeList list = supercar.getChildNodes();

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    if ("carname".equals(eElement.getNodeName())) {
                        if ("Ferrari 01".equals(eElement.getTextContent())) {
                            eElement.setTextContent("Lamborghini 01");
                        }
                    }
                }
                NodeList childNodes = cars.getChildNodes();

                for(int count = 0; count < childNodes.getLength(); count++) {
                    Node node1 = childNodes.item(count);

                    if ("luxurycars".equals(node1.getNodeName()))
                        cars.removeChild(node1);
                }

                //Tartalom konzolra irasa
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(doc);

                System.out.println("-------Módosított fájl-------");
                StreamResult consoleResult = new StreamResult(System.out);
                transformer.transform(source, consoleResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
