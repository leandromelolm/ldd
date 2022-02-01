package stax;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * a) Gerar uma tabela HTML com o nome do produto, a linha, a empresa produtora,
 * a quantidade em estoque, e o preço, ordenado pelo nome do produto.
 *
 */
class Product implements Comparable<Product> {

    private String name;
    private String line;
    private String vendor;
    private String quantityInStock;
    private String buyPrice;

    public Product(String name, String line, String vendor, String quantityInStock, String buyPrice) {
        this.name = name;
        this.line = line;
        this.vendor = vendor;
        this.quantityInStock = quantityInStock;
        this.buyPrice = buyPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.name);

    }
}

public class Q1_StAX {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, IOException {
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        Reader reader = new FileReader("web/products.xml");
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(reader);

//        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newFactory();
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();

//        Writer writer = new FileWriter("Q1_StAX.xml");
        Writer writer = new FileWriter("web/Q1_StAX.html");

//        XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(writer);
        XMLStreamWriter xMLStreamWriter = new IndentingXMLStreamWriter(xMLOutputFactory.createXMLStreamWriter(writer));

        boolean bName = false;
        boolean bLine = false;
        boolean bVendor = false;
        boolean bQuantityInStock = false;
        boolean bBuyPrice = false;
        String name = null;
        String line = null;
        String vendor = null;
        String quantityInStock = null;
        String buyPrice = null;
        List<Product> produtos = new ArrayList();

        xMLStreamWriter.writeStartElement("table");
        xMLStreamWriter.writeStartElement("thead");
        xMLStreamWriter.writeStartElement("tr");
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Name");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Line");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Vendor");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Quantity in Stock");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Buy Price");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("tbody");

        while (xmlsr.hasNext()) {
            switch (xmlsr.next()) {
                case XMLStreamReader.START_ELEMENT:
                    String nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("name")) {
                        bName = true;
                    }
                    if (nome.equals("line")) {
                        bLine = true;
                    }
                    if (nome.equals("vendor")) {
                        bVendor = true;
                    }
                    if (nome.equals("quantityInStock")) {
                        bQuantityInStock = true;
                    }
                    if (nome.equals("buyPrice")) {
                        bBuyPrice = true;
                    }
                    break;

                case XMLStreamReader.END_ELEMENT:
                    nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("product")) {

                        produtos.add(new Product(name, line, vendor, quantityInStock, buyPrice));
                        
//                        System.out.println(name);  
//                        System.out.println(line);
//                        System.out.println(vendor);
//                        System.out.println(quantityInStock);
//                        System.out.println(buyPrice);
                        
                          // saída não ordenada
//                        xMLStreamWriter.writeStartElement("tr");
//                        xMLStreamWriter.writeStartElement("td");
//                        xMLStreamWriter.writeCharacters(name);
//                        xMLStreamWriter.writeEndElement();
//                        xMLStreamWriter.writeStartElement("td");
//                        xMLStreamWriter.writeCharacters(line);
//                        xMLStreamWriter.writeEndElement();
//                        xMLStreamWriter.writeStartElement("td");
//                        xMLStreamWriter.writeCharacters(vendor);
//                        xMLStreamWriter.writeEndElement();                        
//                        xMLStreamWriter.writeStartElement("td");
//                        xMLStreamWriter.writeCharacters(quantityInStock);
//                        xMLStreamWriter.writeEndElement();                        
//                        xMLStreamWriter.writeStartElement("td");
//                        xMLStreamWriter.writeCharacters(buyPrice);
//                        xMLStreamWriter.writeEndElement();
//                        xMLStreamWriter.writeEndElement();
                        name = null;
                    }

                    Collections.sort(produtos); // Ordenação

                    if (nome.equals("products")) {
                        for (Product p : produtos) {
                            
                            // saída ordenada
                            System.out.println(p.getName());
                            System.out.println(p.getLine());
                            System.out.println(p.getVendor());
                            System.out.println(p.getQuantityInStock());
                            System.out.println(p.getBuyPrice());

                            xMLStreamWriter.writeStartElement("tr");
                            xMLStreamWriter.writeStartElement("td");
                            xMLStreamWriter.writeCharacters(p.getName());
                            xMLStreamWriter.writeEndElement();
                            xMLStreamWriter.writeStartElement("td");
                            xMLStreamWriter.writeCharacters(p.getLine());
                            xMLStreamWriter.writeEndElement();
                            xMLStreamWriter.writeStartElement("td");
                            xMLStreamWriter.writeCharacters(p.getVendor());
                            xMLStreamWriter.writeEndElement();
                            xMLStreamWriter.writeStartElement("td");
                            xMLStreamWriter.writeCharacters(p.getQuantityInStock());
                            xMLStreamWriter.writeEndElement();
                            xMLStreamWriter.writeStartElement("td");
                            xMLStreamWriter.writeCharacters(p.getBuyPrice());
                            xMLStreamWriter.writeEndElement();
                            xMLStreamWriter.writeEndElement();

                        }
                    }
                    break;

                case XMLStreamReader.CHARACTERS:
                    if (bName) {
                        name = xmlsr.getText();
//                        System.out.println(name);
                    }
                    if (bLine) {
                        line = xmlsr.getText();
                    }
                    if (bVendor) {
                        vendor = xmlsr.getText();
                    }
                    if (bQuantityInStock) {
                        quantityInStock = xmlsr.getText();
                    }
                    if (bBuyPrice) {
                        buyPrice = xmlsr.getText();
                    }
                    bLine = false;
                    bName = false;
                    bVendor = false;
                    bQuantityInStock = false;
                    bBuyPrice = false;
                    break;
            }
        }
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();

        xmlsr.close();
        xMLStreamWriter.close();
    }
}


/*
 * A montagem da saída das questões implementadas com SAX ou StAX deve utilizar como
 * base a classe XMLStreamWriter .
 * 
 */
