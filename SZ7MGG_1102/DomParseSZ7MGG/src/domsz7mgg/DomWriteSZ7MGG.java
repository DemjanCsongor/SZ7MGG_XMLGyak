package domsz7mgg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomWriteSZ7MGG {
    public static void main(String[] args) throws ParserConfigurationException,
            TransformerException {

        //peldanyositjuk a DocumentBuilderFactory osztalyt a statikus newInstance
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element root = doc.createElementNS("DOMSZ7MGG", "users");
        doc.appendChild(root);
        //letrehozunk egy gyoker elemet es hozzaadjuk a dokumentumhoz egy gyerek elemet

        //gyokerelemhez harom gyerekelemet fuzunk.
        root.appendChild(createUser(doc, "1", "Pál", "Kiss", "Web Developer"));
        root.appendChild(createUser(doc, "2", "Bence", "Kiss", "Web Developer"));
        root.appendChild(createUser(doc, "3", "Attila", "Kiss", "Web Developer"));

        //XML fileba irunk
        //Java DOM a Transformer objektumot hasznalja az XML-file letrehozasahoz
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        //beallitjuk a dokumentum kodolasat es heuzasat
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        //DOMSource tartalmazza a DOM fat
        DOMSource source = new DOMSource(doc);

        File myFile = new File("users2.xml");

        //irjunk egy konzolba es egy fajlba
        //StreamResult transformacios eredmeny birtoklasa
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(myFile);

        //transform metodus alakitja az elso parameterkent kapott source objektumot
        //az XML-forrasokat a stream eredmenyekhez irjuk
        transf.transform(source, console);
        transf.transform(source, file);
    }
    //a crateUser() metodusban uj felhasznaloi elem jon letre crateElement()
    private static Node createUser(Document doc, String id, String firstName,
                                   String lastName, String profession) {
        Element user = doc.createElement("user");

        //az elem attributumaval van beallitva setAttribute()
        user.setAttribute("id", id);
        user.appendChild(createUserElement(doc, "firstname", firstName));
        user.appendChild(createUserElement(doc, "lasatname", lastName));
        user.appendChild(createUserElement(doc, "profession", profession));

        return user;
    }
    //egy elem hozzaadodik a szulojehez a appendChild() es szoveges csompont jon letre
    private static Node createUserElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
}
