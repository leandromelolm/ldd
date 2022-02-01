package dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM_ChalmersBiographyExtract {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("web/chalmers-biography-extract.xml"));
        NodeList list = doc.getElementsByTagName("entry");
        List<Person> people = new ArrayList();

        for (int i = 0; i < list.getLength(); i++) {
            Element entry = (Element) list.item(i);
            String died = entry.getAttribute("died");
            int d = Integer.parseInt(died);
            if (d < 1600) {
                Element title = (Element) entry.getElementsByTagName("title").item(0);
                String temp = title.getTextContent().replace("\n", "").trim();
                people.add(new Person(temp, d));
            }
        }

        Document out = db.newDocument();
        Element results = out.createElement("results");
        out.appendChild(results);
        people.sort((o1, o2) -> o1.getDied() - o2.getDied());
        people.forEach(p -> {
            Element dude = out.createElement("dude");
            dude.setTextContent(String.format("%s (%d)", p.getName(), p.getDied()));
            results.appendChild(dude);
        });

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(out);
        StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);
    }
}

/*

Baseado no arquivo chalmers-biography-extract.xml, implementar programas na linguagem de programação Java utilizando DOM que
selecionem as seguintes informações:

1. Nome e ano das pessoas que morreram antes do ano de 1600, no seguinte formato:
<?xml version="1.0" encoding="UTF-8"?>
<results>
<dude>Abu-Nowas (810)</dude>
<dude>Ado (875)</dude>
<dude>Alfes, Isaac (1103)</dude>
<dude>Algazeli, Abou-Hamed-Mohammed (1111)</dude>
<dude>Aben-Ezra (1165)</dude>
<dude>Ailred (1166)</dude>
<dude>Accorso, Francis (1229)</dude>
. . .
</results>

*/