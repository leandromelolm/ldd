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
 * 2. Quantos livros possuem mais de um autor?
 * 
 */

public class SAX_Ex02 extends DefaultHandler{
    
    private int qAuthors = 0;
    private String title;
    private boolean bTitle = false;
    private int qCountAuthor = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "title":
                bTitle = true;
                break;
            case "author":
                qAuthors++;
                break;        
        }
        
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Quantos livros possuem mais de um autor? " 
                + qCountAuthor);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       if(qName.equalsIgnoreCase("book")){
           
            if (qAuthors > 1){ 
                qCountAuthor++;
            }
            qAuthors = 0;
            title = "";
       }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bTitle) {
            title = new String (ch, start, length);
        }
        bTitle = false;        
    }
    
    public static void main(String[] args) {
        File inputFile = new File("web/bibliography.xml");
        SAX_Ex02 userhandler = new SAX_Ex02();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}