package stax;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * 
 * a) Gerar uma tabela HTML com o nome do produto, a linha, a empresa produtora, a
 * quantidade em estoque, e o preço, ordenado pelo nome do produto.
 * 
 */

public class StAX_1 {
    
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException, IOException {
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        Reader reader = new FileReader("web/products.xml");
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(reader);
        
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newFactory();
        Writer writer = new FileWriter("Q1.html");
        XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(writer);
        
        
        boolean bName = false;
        boolean bLine = false;
        String name = null;
        String line = null;
        
        xMLStreamWriter.writeStartElement("table");
        xMLStreamWriter.writeStartElement("thead");
        xMLStreamWriter.writeStartElement("tr");
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Name");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("th");
        xMLStreamWriter.writeCharacters("Line");
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeStartElement("tbody");
        
        
        while (xmlsr.hasNext()) {
            switch (xmlsr.next()){
                case XMLStreamReader.START_ELEMENT:
                    String nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("name")){
                        bName = true;
                    }
                    if (nome.equals("line")){
                        bLine = true;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    nome = xmlsr.getLocalName();
//                    System.out.println(nome);
                    if (nome.equals("product")){
//                        System.out.println(name);  
//                        System.out.println(line);
                        xMLStreamWriter.writeStartElement("tr");
                        xMLStreamWriter.writeStartElement("td");
                        xMLStreamWriter.writeCharacters(name);
                        xMLStreamWriter.writeEndElement();
                        xMLStreamWriter.writeStartElement("td");
                        xMLStreamWriter.writeCharacters(line);
                        xMLStreamWriter.writeEndElement();
                        xMLStreamWriter.writeEndElement();
                        
                        name = null;
                    }                                        
                    break;
                case XMLStreamReader.CHARACTERS:
                    if (bName) {
                        name = xmlsr.getText();
//                        System.out.println(name);
                    }
                    if (bLine){
                        line = xmlsr.getText();
                    }
                    bLine = false;
                    bName = false;
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
