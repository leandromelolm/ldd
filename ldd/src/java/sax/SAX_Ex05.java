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
 * 5. Quantos livros da categoria LP estão em inglês?
 * 
 */

public class SAX_Ex05 extends DefaultHandler{
    
    private boolean ttcategory;
    private int qtd;

    @Override
    public void startDocument() throws SAXException {}

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("BOOK")) {
            if(attributes.getValue("category").equalsIgnoreCase("LP")) {
                ttcategory = true;
            }            
        }

        if (qName.equalsIgnoreCase("TITLE")) {
            if(attributes.getValue("lang").equalsIgnoreCase("en") && ttcategory) {
                qtd++;
            }
            ttcategory = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
    }

    @Override
    public void endDocument() throws SAXException {
         System.out.println(qtd);
    }        
    
    public static void main(String[] args) {
        File inputFile = new File("web/bibliography.xml");
        SAX_Ex05 userhandler = new SAX_Ex05();
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
 *  
 <bibliography xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:noNamespaceSchemaLocation="bibliography.xsd">
    <book category="LP">
        <title lang="pt-br">C: Como Programar</title>
        <author>Paul Deitel</author>
        <author>Harvey Deitel</author>
        <year>2011</year>
        <price>220.00</price>
    </book>
    <book category="AC">
        <title lang="pt-br">Organização Estruturada de Computadores</title>
        <author>Andrew S. Tanenbaum</author>
        <year>2006</year>
        <price>166.90</price>
    </book>
    <book category="BD">
        <title lang="pt-br">Sistemas de Banco de Dados</title>
        <author>Abraham Silberschatz</author>
        <author>Henry F. Korth</author>
        <author>S. Sudarshan</author>
        <year>2012</year>
        <price>180.90</price>
    </book>
    <book category="ES">
        <title lang="pt-br">Engenharia de Software</title>
        <author>Roger S. Pressman</author>
        <year>2011</year>
        <price>137.90</price>
    </book>
    <book category="LP">
        <title lang="en">Beginning XML</title>
        <author>Joe Fawcett</author>
        <author>Danny Ayers</author>
        <author>Liam R. E. Quin</author>
        <year>2012</year>
        <price>26.25</price>
    </book>
    <book category="RC">
        <title lang="en">Computer Networking: A Top-Down Approach</title>
        <author>James F. Kurose</author>
        <author>Keith W. Ross</author>
        <year>2012</year>
        <price>122.17</price>
    </book>
    <book category="LP">
        <title lang="en">Head First Servlets and JSP: Passing the Sun Certified Web Component Developed Exam</title>
        <author>Kathy Sierra</author>
        <author>Bert Bates</author>
        <year>2008</year>
        <price>30.64</price>
    </book>
    <book category="ES">
        <title lang="pt-br">Utilizando UML e Padrões: Uma Introdução à Análise e Projeto Orientados a Objetos e ao Processo Unificado</title>
        <author>Craig Larman</author>
        <year>2007</year>
        <price>166.00</price>
    </book>
    <book category="SO">
        <title lang="en">Operating System Concepts</title>
        <author>Abraham Silberschatz</author>
        <author>Peter B. Galvin</author>
        <author>Greg Gagne</author>
        <year>2012</year>
        <price>130.48</price>
    </book>
    <book category="ES">
        <title lang="en">Software Testing</title>
        <author>Ron Patton</author>
        <year>2005</year>
        <price>25.99</price>
    </book>
    <book category="IA">
        <title lang="en">Artificial Intelligence: A Modern Approach</title>
        <author>Stuart Russell</author>
        <author>Peter Norvig</author>
        <year>2009</year>
        <price>137.92</price>
    </book>
    <book category="SO">
        <title lang="pt-br">Sistemas Operacionais Modernos</title>
        <author>Andrew S. Tanenbaum</author>
        <year>2010</year>
        <price>138.00</price>
    </book>
    <book category="LP">
        <title lang="en">XSLT</title>
        <author>Doug Tidwell</author>
        <year>2008</year>
        <price>46.68</price>
    </book>
</bibliography>
 * 
 * 
 * 
 SAÍDA CONSOLE : 3
 * 
 */