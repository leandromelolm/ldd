package sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * EXERCÍCIO
 * 
 * Baseado no arquivo bibliography.xml, implementar programas na linguagem de programação Java utilizando SAX que selecionem as 
 * seguintes informações:
 * 
 * 3. Qual a média de preços dos livros da categoria SO?
 * 
 */

public class SAX_Ex03 extends DefaultHandler{
    
    private double sumPrices = 0;   
    private int countPrices = 0;
    private boolean bBook = false;
    private boolean bPrice = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "book":
                if(attributes.getValue("category").equals("SO")){
                    bBook = true;
                }
                break;
            case "price":
                if(bBook){
                    bPrice = true;
                }
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Média de preços dos livros da categoria SO? " 
                +sumPrices / countPrices);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bPrice) {
            String value = new String(ch, start, length);
            sumPrices += Double.parseDouble(value);
            countPrices++;
            bBook = false;
        }  
        bPrice = false;
    }    
    
    public static void main(String[] args) {
        File inputFile = new File("web/bibliography.xml");
        SAX_Ex03 userhandler = new SAX_Ex03();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}