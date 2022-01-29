package sax;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
 * 9. A média de preço dos livros em português é maior que dos livros em inglês?
 * 
 */

public class SAX_Ex09 extends DefaultHandler{
    
     private boolean ttpt;
    private boolean tten;
    private boolean ttprice;
    private double sumReal = 0, qtdReal = 0, sumDolar = 0, qtdDolar = 0;

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("TITLE")) {
            if(attributes.getValue("lang").equalsIgnoreCase("pt-br")) {
                ttpt = true;
            }
            if(attributes.getValue("lang").equalsIgnoreCase("en")) {
                tten = true;
            }
        }
        
        if (qName.equalsIgnoreCase("PRICE")) {
            ttprice = true;
        }
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {

    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if(ttprice) {
            double price = Double.parseDouble(new String(ch, start, length));
            if(ttpt) {
                sumReal += price;
                qtdReal++;
                ttpt = false;
            }
            if(tten) {
                sumDolar += price;
                qtdDolar++;
                tten = false;
            }
            ttprice = false;
        }
    }

    @Override
    public void startDocument() {
    }

    @Override
    public void endDocument() {
        System.out.println("A média de preço dos livros em português é maior que dos livros em inglês?");
        System.out.println(sumReal / qtdReal > sumDolar / qtdDolar);
    }
    
    public static void main(String[] args) {
        File inputFile = new File("web/bibliography.xml");
        SAX_Ex09 userhandler = new SAX_Ex09();
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
 * SAÍDA CONSOLE : 
    A média de preço dos livros em português é maior que dos livros em inglês?
    true
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
 * 
 */