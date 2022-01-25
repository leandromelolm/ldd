package sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX3 extends DefaultHandler {
    
    private int currentDied;
    private String currentName;
    private boolean readName;
    private boolean bTitle;
    private boolean bName;
    
    private List<Person> people = new ArrayList();
    
    @Override
    public void startDocument() throws SAXException {
        System.out.println("<results>");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "entry":
                currentDied = Integer.MAX_VALUE;
                currentName = "";
                readName = bTitle = bName = false;
                int died = Integer.parseInt(attributes.getValue("died"));
                if (died < 1600) {
                    currentDied = died;
                    readName = true;
                }
                break;
            case "title":
                if (readName) {
                    bTitle = true;
                }
                break;
            case "csc":
                if (bTitle) {
                    bName = true;
                }
                break;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bName) {
            String s = new String(ch, start, length);
            currentName += s;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("title")) {
            if (currentDied != Integer.MAX_VALUE) {
                String temp = currentName.replace("\n", "").trim();
                people.add(new Person(temp, currentDied));
//                System.out.println(String.format("<dude>%s (%d)</dude>", temp, currentDied));
            }
        }
    }
    
    @Override
    public void endDocument() throws SAXException {
//        Collections.sort(people);
        people.sort((o1, o2) -> o1.getDied() - o2.getDied());
        people.forEach(p -> System.out.println(String.format("<dude>%s (%d)</dude>", p.getName(), p.getDied())));
        System.out.println("</results>");
    }
    
    public static void main(String[] args) {
        File inputFile = new File("web/chalmers-biography-extract.xml");
        SAX3 userhandler = new SAX3();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Comparable<Person> {
    
    private String name;
    private int died;
    
    public Person(String name, int died) {
        this.name = name;
        this.died = died;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getDied() {
        return died;
    }
    
    public void setDied(int died) {
        this.died = died;
    }

    @Override
    public int compareTo(Person o) {
        return this.getDied() - o.getDied();
    }
}
