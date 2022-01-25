package sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX2 extends DefaultHandler {

    private String oldestTitle;
    private int oldestYear = Integer.MAX_VALUE;

    private String currentTitle;
    private int currentYear;

    private boolean bTitle = false;
    private boolean bYear = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "title":
                bTitle = true;
                break;
            case "year":
                bYear = true;
                break;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bTitle) {
            currentTitle = new String(ch, start, length);
        }
        if (bYear) {
            currentYear = Integer.parseInt(new String(ch, start, length));
        }

        bTitle = false;
        bYear = false;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("cd")) {
            if(currentYear < oldestYear) {
                oldestYear = currentYear;
                oldestTitle = currentTitle;
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(oldestTitle);
        System.out.println(oldestYear);
    }
    
    public static void main(String[] args) {
        File inputFile = new File("web/cd_catalog.xml");
        SAX2 userhandler = new SAX2();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
