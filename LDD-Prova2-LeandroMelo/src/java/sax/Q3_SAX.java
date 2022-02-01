package sax;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * c) Gerar uma tabela HTML com o nome dos fornecedores e a lista de produtos comprados
 *    deste fornecedor.
 * 
 */

public class Q3_SAX extends DefaultHandler{
    
    boolean bVendor = false;
    private List<String> vendors =  new ArrayList();
    boolean bName = false;
    boolean bProduct = false;
    private String name;
    private String vendor;
    private String fornecedor;    
    private List<Product> produtos = new ArrayList();
    
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        
        if(qName.equals("product")){
            bName = bVendor = false;
        }        
        if (qName.equals("name")){
            bName = true;
        }
        if (qName.equals("vendor")){
            bVendor = true;        
        }        
        if (qName.equals("product")){
            bProduct = true;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) 
            throws SAXException {
        fornecedor = new String(ch, start, length);
        if(bVendor) {
            if (!vendors.contains(fornecedor) ) {
                vendors.add(fornecedor);
            }      
        }
        if (bVendor){
            vendor = new String(ch, start, length);  
            bVendor = false;
        }
        if (bName){
            name = new String(ch, start, length);
            bName = false;
        }
    }
    
    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        
        if (qName.equals("product")){
             produtos.add(new Product(name, vendor));
        }
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() throws SAXException {        
        
//        for (int i = 0; i < vendors.size(); i++) {
//            System.out.println("<td>"+ vendors.get(i) +"</td>");
//
//            for (Product p : produtos) {
//                if (p.getVendor().equals(vendors.get(i))) {
//                    System.out.println("<li>" + p.getName() + "</li>");
//                  
//                }
//            }            
//        }
    }
    
    public static void main(String[] args) throws IOException, XMLStreamException {
        File inputFile = new File("web/products.xml");
        Q3_SAX userhandler = new Q3_SAX();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newFactory();
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance(); 
        
//        Writer writer = new FileWriter("web/Q3_SAX.xml");
        Writer writer = new FileWriter("web/Q3_SAX.html");
        
//        XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(writer);
        XMLStreamWriter xMLStreamWriter = new IndentingXMLStreamWriter(xMLOutputFactory.createXMLStreamWriter(writer));
        
        xMLStreamWriter.writeStartElement("table");
        xMLStreamWriter.writeStartElement("thead");
        xMLStreamWriter.writeStartElement("tr");
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Vendor");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");        
        xMLStreamWriter.writeCharacters("Name");  
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        
        xMLStreamWriter.writeStartElement("tbody");

        for (int i = 0; i < userhandler.vendors.size(); i++) {
            System.out.println("<ul>"+ userhandler.vendors.get(i) +"</ul>");
            xMLStreamWriter.writeStartElement("tr");
            xMLStreamWriter.writeStartElement("td");
            xMLStreamWriter.writeCharacters(userhandler.vendors.get(i));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("td");
            xMLStreamWriter.writeStartElement("ul");
            for (Product p : userhandler.produtos) {
                if (p.getVendor().equals(userhandler.vendors.get(i))) {                    
                    System.out.println("<li>" + p.getName() + "</li>");                    
                    xMLStreamWriter.writeStartElement("li");
                    xMLStreamWriter.writeCharacters(p.getName());
                    xMLStreamWriter.writeEndElement();
                }
            } 
            xMLStreamWriter.writeEndElement(); 
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeEndElement();
        }        
        xMLStreamWriter.writeEndElement(); // tbody    
        xMLStreamWriter.writeEndElement();        
        xMLStreamWriter.close();
    }   
}


class Product {
    private String name;
    private String vendor;

    public Product(String name, String vendor) {
        this.name = name;
        this.vendor = vendor;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}


/*
 * A montagem da saída das questões implementadas com SAX ou StAX deve utilizar como
 * base a classe XMLStreamWriter .
 * 
 */

