package sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX1 extends DefaultHandler {

    private boolean bPrice;
    private double sumOfPrices;
    private long qtdOfPrices;

    @Override
    public void startDocument() throws SAXException {
        bPrice = false;
        sumOfPrices = 0.0;
        qtdOfPrices = 0;
        System.out.println("Começando a processar o documento...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("price")) {
            bPrice = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bPrice) {
            String s = new String(ch, start, length);
            sumOfPrices += Double.parseDouble(s);
            qtdOfPrices++;
        }
        bPrice = false;
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(sumOfPrices / qtdOfPrices);
    }

    public static void main(String[] args) {
        File inputFile = new File("web/cd_catalog.xml");
        SAX1 userhandler = new SAX1();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
