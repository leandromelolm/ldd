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

public class DOM_2 {
    
    private List<Product> produtos = new ArrayList();
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {
        
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
            
            
            System.out.print(eName.getTextContent());
            System.out.print(" (");
            System.out.print(percentual + "%");
            System.out.println(")");            
            Element li = out.createElement("li");
            ol.appendChild(li);
            li.setTextContent(eName.getTextContent() + " (" + percentual + "%)");
//            produtos.add(new Product(eName.toString(), percentual));
        }
        
        for (int i = 0; i < products.getLength(); i++) {}        
        
        TransformerFactory transformeFactory = TransformerFactory.newInstance();
        Transformer transformer = transformeFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(out);
        StreamResult resultXML = new StreamResult(new File ("Q2.xml"));
        StreamResult result = new StreamResult(new File ("Q2.html"));
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





/*

run:
1969 Harley Davidson Ultimate Chopper (51%)
1952 Alpine Renault 1300 (46%)
1996 Moto Guzzi 1100i (58%)
2003 Harley-Davidson Eagle Drag Bike (47%)
1972 Alfa Romeo GTA (63%)
1962 LanciaA Delta 16V (70%)
1968 Ford Mustang (49%)
2001 Ferrari Enzo (46%)
1958 Setra Bus (57%)
2002 Suzuki XREO (44%)
1969 Corvair Monza (59%)
1968 Dodge Charger (64%)
1969 Ford Falcon (48%)
1970 Plymouth Hemi Cuda (40%)
1957 Chevy Pickup (47%)
1969 Dodge Charger (51%)
1940 Ford Pickup Truck (50%)
1993 Mazda RX-7 (59%)
1937 Lincoln Berline (59%)
1936 Mercedes-Benz 500K Special Roadster (45%)
1965 Aston Martin DB5 (53%)
1980s Black Hawk Helicopter (49%)
1917 Grand Touring Sedan (51%)
1948 Porsche 356-A Roadster (70%)
1995 Honda Civic (66%)
1998 Chrysler Plymouth Prowler (62%)
1911 Ford Town Car (55%)
1964 Mercedes Tour Bus (61%)
1932 Model A Ford J-Coupe (46%)
1926 Ford Fire Engine (41%)
P-51-D Mustang (58%)
1936 Harley Davidson El Knucklehead (40%)
1928 Mercedes-Benz SSK (43%)
1999 Indy 500 Monte Carlo SS (43%)
1913 Ford Model T Speedster (60%)
1934 Ford V8 Coupe (55%)
1999 Yamaha Speed Boat (60%)
18th Century Vintage Horse Carriage (58%)
1903 Ford Model A (50%)
1992 Ferrari 360 Spider red (46%)
1985 Toyota Supra (53%)
Collectable Wooden Train (67%)
1969 Dodge Super Bee (61%)
1917 Maxwell Touring Car (58%)
1976 Ford Gran Torino (50%)
1948 Porsche Type 356 Roadster (44%)
1957 Vespa GS150 (53%)
1941 Chevrolet Special Deluxe Cabriolet (61%)
1970 Triumph Spitfire (64%)
1932 Alfa Romeo 8C2300 Spider Sport (47%)
1904 Buick Runabout (60%)
1940s Ford truck (70%)
1939 Cadillac Limousine (46%)
1957 Corvette Convertible (47%)
1957 Ford Thunderbird (48%)
1970 Chevy Chevelle SS 454 (67%)
1970 Dodge Coronet (56%)
1997 BMW R 1100 S (54%)
1966 Shelby Cobra 427 S/C (58%)
1928 British Royal Navy Airplane (61%)
1939 Chevrolet Deluxe Coupe (68%)
1960 BSA Gold Star DBD34 (49%)
18th century schooner (67%)
1938 Cadillac V-16 Presidential Limousine (46%)
1962 Volkswagen Microbus (48%)
1982 Ducati 900 Monster (68%)
1949 Jaguar XK 120 (52%)
1958 Chevy Corvette Limited Edition (45%)
1900s Vintage Bi-Plane (50%)
1952 Citroen-15CV (62%)
1982 Lamborghini Diablo (43%)
1912 Ford Model T Delivery Wagon (53%)
1969 Chevrolet Camaro Z28 (59%)
1971 Alpine Renault 1600s (63%)
1937 Horch 930V Limousine (40%)
2002 Chevy Corvette (58%)
1940 Ford Delivery Sedan (58%)
1956 Porsche 356A Coupe (70%)
Corsair F4U (Bird Cage) (43%)
1936 Mercedes Benz 500k Roadster (53%)
1992 Porsche Cayenne Turbo Silver (59%)
1936 Chrysler Airflow (59%)
1900s Vintage Tri-Plane (50%)
1961 Chevrolet Impala (40%)
1980’s GM Manhattan Express (56%)
1997 BMW F650 ST (67%)
1982 Ducati 996 R (60%)
1954 Greyhound Scenicruiser (48%)
1950\'s Chicago Surface Lines Streetcar (43%)
1996 Peterbilt 379 Stake Bed with Outrigger (52%)
1928 Ford Phaeton Deluxe (48%)
1974 Ducati 350 Mk3 Desmo (55%)
1930 Buick Marquette Phaeton (62%)
Diamond T620 Semi-Skirted Tanker (59%)
1962 City of Detroit Streetcar (64%)
2002 Yamaha YZR M1 (42%)
The Schooner Bluenose (51%)
American Airlines: B767-300 (56%)
The Mayflower (50%)
HMS Bounty (44%)
America West Airlines B757-200 (69%)
The USS Constitution Ship (47%)
1982 Camaro Z28 (46%)
ATA: B757-300 (50%)
F/A 18 Hornet 1/72 (68%)
The Titanic (51%)
The Queen Mary (54%)
American Airlines: MD-11S (49%)
Boeing X-32A JSF (66%)
Pont Yacht (61%)
BUILD SUCCESSFUL (total time: 0 seconds)

*/