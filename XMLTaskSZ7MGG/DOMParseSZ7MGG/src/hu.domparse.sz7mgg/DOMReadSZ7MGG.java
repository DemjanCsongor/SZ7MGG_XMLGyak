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

public class DOMReadSZ7MGG {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //file nev megadasa
        File file = new File("XMLSZ7MGG.xml");

        //kesobb hasznalt valtoz deklaralasa
        String prev="";
        //parse
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();
        //gyokerelem kiiratasa
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        //gyerekelemek mentese listaba
        NodeList nList = (NodeList) doc.getDocumentElement();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if(!node.getNodeName().equals("#text")) {
                System.out.println("\n");
                System.out.println("Current element: " + node.getNodeName());
            }

            if(node.getNodeType()==Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                //id kiiratasa
                String id = elem.getAttribute(node.getNodeName()+"Id");
                NodeList childs = elem.getChildNodes();

                if(!id.equals(""))
                    System.out.println("ID: "+id);
                for (int j = 0; j < childs.getLength() ; j++) {
                    Node node2 = childs.item(j);
                    if(!node2.getNodeName().equals("#text")) {
                        int k = 0;
                        String result = node2.getNodeName()+": "+elem.getElementsByTagName(node2.getNodeName()).item(k).getTextContent();
                        if(prev.equals(result)) {
                            k++;
                            result = node2.getNodeName()+": "+elem.getElementsByTagName(node2.getNodeName()).item(k).getTextContent();
                        }
//                        if(result.contains("motor")) {
//                            Node node3 = elem.getElementsByTagName("hengerurtartalom").item(0);
//                            Node node4 = elem.getElementsByTagName("teljesitmeny").item(0);
//                            result = "motor: \n"+"		"+node3.getNodeName()+": "+node3.getTextContent()+", \n"+"		"+node4.getNodeName()+": "+node4.getTextContent();
//                        }
                        if(result.contains("cim")) {
                            Node node3 = elem.getElementsByTagName("irsz").item(0);
                            Node node4 = elem.getElementsByTagName("varos").item(0);
                            Node node5 = elem.getElementsByTagName("utca").item(0);
                            Node node6 = elem.getElementsByTagName("hazszam").item(0);
                            result = "cim: \n"+"		"+node3.getNodeName()+": "+node3.getTextContent()+", \n"
                                    +"		"+node4.getNodeName()+": "+node4.getTextContent()+", \n"
                                    +"		"+node5.getNodeName()+": "+node5.getTextContent()+", \n"
                                    +"		"+node6.getNodeName()+": "+node6.getTextContent();
                        }
                        System.out.println("	"+result);
                        prev = result;
                    }
                }
            }
        }
    }

}