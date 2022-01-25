/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * 1. Qual o nome dos livros que possuem mais de um autor?
 * 
 */

public class SAX_Ex01 extends DefaultHandler{
    
    private int qAuthors = 0;
    private String title;
    private boolean bTitle = false;

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
    public void endElement(String uri, String localName, String qName) throws SAXException {
       if(qName.equalsIgnoreCase("book")){
           
            if (qAuthors > 1){
                System.err.println(title);        
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
        SAX_Ex01 userhandler = new SAX_Ex01();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


/*

Seguinte ERRO ao executar projeto ( RUN File [atalho Shift + F6]):

Error: Could not create the Java Virtual Machine.
-Xbootclasspath/p is no longer a supported option.
Error: A fatal exception has occurred. Program will exit.
/home/melo/.cache/netbeans/12.3/executor-snippets/run.xml:111: The following error occurred while executing this line:
/home/melo/.cache/netbeans/12.3/executor-snippets/run.xml:68: Java returned: 1
BUILD FAILED (total time: 0 seconds)

Resolvido da seguinte forma:
    projeto ldd > properties > libraries > javaplatform = JDK 1.8

*/