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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * b) Gerar uma lista ordenada em HTML com o nome do produto e o percentual do preço
 * comprado sobre o preço sugerido, ordenado de forma decrescente pelo percentual
 * 
 */

public class Q2_DOM {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        
        List<Product> produtos = new ArrayList();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("web/products.xml"));
        
        Document out = db.newDocument();
        
        NodeList products = doc.getElementsByTagName("product");
        Element ol = out.createElement("ol");
        out.appendChild(ol);
        for (int i = 0; i < products.getLength(); i++) {
            Element product = (Element) products.item(i);
            Element eName = (Element) product.getElementsByTagName("name").item(0);
            Element eBuyPrice = (Element) product.getElementsByTagName("buyPrice").item(0);            
            Double dBuyPrice = Double.parseDouble(eBuyPrice.getTextContent());             
            Element eMSRP = (Element) product.getElementsByTagName("MSRP").item(0);             
            Double dMSRP = Double.parseDouble(eMSRP.getTextContent());            
            int percentual = (int) Math.round(dBuyPrice/dMSRP*100);  
            
            produtos.add(new Product(eName.getTextContent(), percentual));
             
            // NÃO ORDENADO
//            System.out.print(eName.getTextContent());
//            System.out.print(" (");
//            System.out.print(percentual + "%");
//            System.out.println(")");  
//            Element li = out.createElement("li");
//            ol.appendChild(li);
//            li.setTextContent(eName.getTextContent() + " (" + percentual + "%)");
        }
        
        // ordenando
        produtos.sort((o1, o2) -> {
            return o2.getPercentual() - o1.getPercentual() ;
        });
        
        // ORDENADO PELO PERCENTUAL
        for (Product p : produtos) {
            System.out.print(p.getName());
            System.out.print(" (");
            System.out.print(p.getPercentual() + "%");
            System.out.println(")");
            
            Element li = out.createElement("li");
            ol.appendChild(li);
            li.setTextContent(p.getName() + " (" + p.getPercentual() + "%)");           
        }        
        
        TransformerFactory transformeFactory = TransformerFactory.newInstance();
        Transformer transformer = transformeFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(out);
        StreamResult resultXML = new StreamResult(new File ("web/Q2_DOM.xml"));
        StreamResult result = new StreamResult(new File ("web/Q2_DOM.html"));
        transformer.transform(source, result);
        transformer.transform(source, resultXML);
    }
    
}

class Product {
    private String name;
    private int percentual;

    public Product(String name, int percentual) {
        this.name = name;
        this.percentual = percentual;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentual() {
        return percentual;
    }

    public void setPercentual(int percentual) {
        this.percentual = percentual;
    }
}


/*
 * A montagem da saída das questões implementadas com DOM deve criar uma árvore vazia
 * usando a classe Document , preenchendo-a com o resultado e depois a serializando.
 * 
 */
