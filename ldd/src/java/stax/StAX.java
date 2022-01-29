package stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAX {

    private XMLStreamReader xsr;

    public StAX(String path) {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        try {
            xsr = xif.createXMLStreamReader(new FileInputStream(path));
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(StAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        StAX stax = new StAX("web/bibliography.xml");
        try {
//            stax.exercicio1a();
            stax.exercicio1h();
        } catch (XMLStreamException ex) {
            Logger.getLogger(StAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    1. Qual o nome dos livros que possuem mais de um autor?
    public void exercicio1a() throws XMLStreamException {
        int qtd = 0;
        String title = "";
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("author")) {
                        qtd++;
                    }
                    if (xsr.getLocalName().equals("title")) {
                        title = xsr.getElementText();
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xsr.getLocalName().equals("book")) {
                        if (qtd > 1) {
                            System.out.println(title);
                        }
                        qtd = 0;
                    }
                    break;
            }
        }
    }
    
//    2. Quantos livros possuem mais de um autor?
    public void exercicio1b() throws XMLStreamException {
        int qtd = 0, qtdLivros = 0;
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("author")) {
                        qtd++;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xsr.getLocalName().equals("book")) {
                        if (qtd > 1) {
                            qtdLivros++;
                        }
                        qtd = 0;
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    System.out.println(qtdLivros);
            }
        }
    }
    
//    3. Qual a média de preços dos livros da categoria SO?
    public void exercicio1c() throws XMLStreamException {
        boolean ok = false;
        double price = 0;
        int count = 0;
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("book") && xsr.getAttributeValue(null, "category").equals("SO")) {
                        ok = true;
                    } else if (xsr.getLocalName().equals("price") && ok) {
                        price += Double.parseDouble(xsr.getElementText());
                        ok = false;
                        count++;
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    System.out.println(price / count);
                    break;
            }
        }
    }

//    4. Quantos livros a partir de 2010 possuem preço maior que 150?
    public void exercicio1d() throws XMLStreamException {
        boolean year = false, price = false;
        int count = 0;
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    switch (xsr.getLocalName()) {
                        case "price":
                            double preco = Double.parseDouble(xsr.getElementText());
                            if (preco > 150) {
                                price = true;
                            }
                            break;
                        case "year":
                            int ano = Integer.parseInt(xsr.getElementText());
                            if (ano >= 2010) {
                                year = true;
                            }
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xsr.getLocalName().equals("book")) {
                        if (year && price) {
                            count++;
                        }
                        year = false;
                        price = false;
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    System.out.println(count);
                    break;
            }
        }
    }

//    5. Quantos livros da categoria LP estão em inglês?
    public void exercicio1e() throws XMLStreamException {
        boolean cat = false, lang = false;
        int count = 0;
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("book") && xsr.getAttributeValue(null, "category").equals("LP")) {
                        cat = true;
                    } else if (xsr.getLocalName().equals("title") && xsr.getAttributeValue(null, "lang").equals("en")) {
                        lang = true;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (xsr.getLocalName().equals("book")) {
                        if (cat && lang) {
                            count++;
                        }
                        cat = false;
                        lang = false;
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    System.out.println(count);
                    break;
            }
        }
    }
    
//    6. Quantos autores começam com a letra 'A'?
    public void exercicio1f() throws XMLStreamException {
        List<String> autores = new ArrayList();
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("author")) {
                        String nome = xsr.getElementText();
                        if (!autores.contains(nome) && nome.startsWith("A")) {
                            autores.add(nome);
                        }
                    }
                    break;
                case XMLStreamReader.END_DOCUMENT:
                    System.out.println(autores.size());
                    break;
            }
        }
    }
    
//     7. Quais autores começam com a letra 'A'?
    public void exercicio1g() throws XMLStreamException {
        List<String> autores = new ArrayList();
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("author")) {
                        String nome = xsr.getElementText();
                        if (!autores.contains(nome) && nome.startsWith("A")) {
                            autores.add(nome);
                            System.out.println(nome);
                        }
                    }
                    break;
            }
        }
    }
    
    // Iniciais do livro
    public void exercicio1h() throws XMLStreamException {
        List<String> autores = new ArrayList();
        while (xsr.hasNext()) {
            int eventType = xsr.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    if (xsr.getLocalName().equals("title")) {
                        String nome = xsr.getElementText();
                        if (!autores.contains(nome) && nome.startsWith("S")) {
                            autores.add(nome);
                            System.out.println(nome);
                        }                        
                    }                    
                    break;
            }
        }
        System.out.println(autores);
    }
}
