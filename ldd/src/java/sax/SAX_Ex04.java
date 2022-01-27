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
 * 4. Quantos livros a partir de 2010 possuem preço maior que 150?
 * 
 */

public class SAX_Ex04 extends DefaultHandler{
    
    private boolean ttyear;
    private boolean ttprice;
    private double price;
    private int year;
    private int qtd;

    private boolean tttprice;

    private boolean bTitle = false;
    private String title;
    private boolean bYear = false;
    private int yearBook;
    private boolean bPrice = false;
    private double priceBook;

    @Override
    public void startDocument() throws SAXException {}

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("PRICE")) {
            ttprice = true;
        }

        if (qName.equalsIgnoreCase("YEAR")) {
            ttyear = true;
        }

        
        /**
         * *******EXTRA*******
         */
        
        if (qName.equalsIgnoreCase("PRICE")) {
            tttprice = true;
        }

        switch (qName) {
            case "title":
                bTitle = true;
                break;
            case "year":
                bYear = true;
                break;
            case "price":
                bPrice = true;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (ttprice) {
            price = Double.parseDouble(new String(ch, start, length));
        }

        if (ttyear) {
            year = Integer.parseInt(new String(ch, start, length));
        }

        
        /**
         * *******EXTRA*******
         */
        
        if (bTitle) {
            title = new String(ch, start, length);
        }

        if (bYear) {
            yearBook = Integer.parseInt(new String(ch, start, length));
        }
        
        if (bPrice) {
            priceBook = Double.parseDouble(new String(ch, start, length));
            if (price > 150 && year > 2010) {
                System.err.print(yearBook + "  ");
                System.err.print(title + "  ");
                System.err.println(priceBook);
            } else {
                System.out.print(yearBook + "  ");
                System.out.print(title + "  ");
                System.out.println(priceBook);
            }
        }
        
        bYear = false;
        bPrice = false;
        bTitle = false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("PRICE")) {
            ttprice = false;
        }

        if (qName.equalsIgnoreCase("YEAR")) {
            ttyear = false;
        }

        if (qName.equalsIgnoreCase("BOOK")) {
            if (price > 150 && year > 2010) {
                qtd++;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.print("\nQuantos livros a partir de 2010 possuem preço maior que 150: ");
        System.out.println(qtd);
    }        
    
    public static void main(String[] args) {
        File inputFile = new File("web/bibliography.xml");
        SAX_Ex04 userhandler = new SAX_Ex04();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 
 *  SAIDA CONSOLE:
2011  C: Como Programar  220.0  <System.err.print>
2012  Sistemas de Banco de Dados  180.9  <System.err.print>
2006  Organização Estruturada de Computadores  166.9
2011  Engenharia de Software  137.9
2012  Beginning XML  26.25
2012  Computer Networking: A Top-Down Approach  122.17
2008  Head First Servlets and JSP: Passing the Sun Certified Web Component Developed Exam  30.64
2007  Utilizando UML e Padrões: Uma Introdução à Análise e Projeto Orientados a Objetos e ao Processo Unificado  166.0
2012  Operating System Concepts  130.48
2005  Software Testing  25.99
2009  Artificial Intelligence: A Modern Approach  137.92
2010  Sistemas Operacionais Modernos  138.0
2008  XSLT  46.68

Quantos livros a partir de 2010 possuem preço maior que 150: 2
* 
*/