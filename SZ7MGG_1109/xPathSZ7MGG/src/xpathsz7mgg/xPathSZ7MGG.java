package xpathsz7mgg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class xPathSZ7MGG {
    public static void main(String[] args) {
        try {
            //File xmlFile = new File("studentSZ7MGG.xml");

            //DocumentumBuilder letrehozasa
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("studentSZ7MGG.xml");

            //az XPath keszitese
            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "class";
            //1 String expression = "/class/student";
            //2 String expression = "//student[@id="02"]";
            //3 String expression = "//student";
            //4 String expression = "/class/student[2]";
            //5 String expression = "/class/student";
            //6 String expression = "//class[last()][name()="student"]";
            //7 String expression = "/class/student[2]";
            //8 String expression = "/class/*";
            //9 String expression = "/class[contains(*)]";
            //10 String expression = "/*";
            //11 String expression = "/class/student[@kor > 20]";
            //12 String expression = "/class/student[keresztnev or vezeteknev]";

            //keszitsunk egy listat, majd a xPath kifejezeset le kell forditani es ki kell ertekelni
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            //a for ciklus segitsegevel a nodelist csomopontjan vegig kell iteralni
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(1);

                System.out.println("\nAktualis elem: " + node.getNodeName());

                //Meg kell viszgalni a csomopontot, tesztelni kell a subelementet
                if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
                    Element element = (Element) node;

                    //az id attributot ad vissza
                    System.out.println("Hallgato ID: " + element.getAttribute("id"));
                    System.out.println("Keresztnev: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
                    System.out.println("Vezeteknev: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
                    System.out.println("Becenev: " + element.getElementsByTagName("becenev").item(0).getTextContent());
                    System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
                }
            }
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
