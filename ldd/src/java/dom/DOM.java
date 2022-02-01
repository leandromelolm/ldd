package dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
Baseado no arquivo bibliography.xml, implementar programas na linguagem de programação Java utilizando DOM que selecionem as
seguintes informações:
1. Qual o nome dos livros que possuem mais de um autor?
2. Quantos livros possuem mais de um autor?
3. Qual a média de preços dos livros da categoria SO?
4. Quantos livros a partir de 2010 possuem preço maior que 150?
5. Quantos livros da categoria LP estão em inglês?
6. Quantos autores começam com a letra 'A'?
7. Quais autores começam com a letra 'A'?
8. Quais os nomes dos livros em português?
9. A média de preço dos livros em português é maior que dos livros em inglês?
10. Quantos livros 'Abraham Silberschatz' publicou em 2012?
11. Qual autor possui mais livros publicados?
12. Quais e quantos são os autores, agrupados pela inicial?
13. Quantos são os livros publicados, agrupados por década?
14. Quantos e quais são os livros publicados, agrupados por ano?
*/

public class DOM {

    private Document doc;

    public DOM(String path) {
        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(DOM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//1. Qual o nome dos livros que possuem mais de um autor?
    public void exercicio1a() {
        NodeList books = doc.getElementsByTagName("book");
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            int qtd = book.getElementsByTagName("author").getLength();
            if (qtd > 1) {
                System.out.println(book.getElementsByTagName("title").item(0).getTextContent());
            }
        }
    }
    
//2. Quantos livros possuem mais de um autor?
    public void exercicio1b() {
        NodeList books = doc.getElementsByTagName("book");
        int qtd = 0;
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            NodeList authors = book.getElementsByTagName("author");
            if (authors.getLength() > 1) {
                qtd++;
            }
        }
        System.out.println(qtd);
    }

//3. Qual a média de preços dos livros da categoria SO?    
    public void exercicio1c() {
        NodeList books = doc.getElementsByTagName("book");
        double sum = 0, qtd = 0;
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            if (book.getAttribute("category").equals("SO")) {
                sum += Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
                qtd++;
            }
        }
        System.out.println(sum / qtd);
    }
    
//4. Quantos livros a partir de 2010 possuem preço maior que 150?
    public void exercicio1d() {
        NodeList books = doc.getElementsByTagName("book");
        int qtd = 0;
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            int year = Integer.parseInt(book.getElementsByTagName("year").item(0).getTextContent());
            double price = Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
            if (year > 2010 && price > 150) {
                qtd++;
            }
        }
        System.out.println(qtd);
    }
    
//5. Quantos livros da categoria LP estão em inglês?
    public void exercicio1e() {
        NodeList books = doc.getElementsByTagName("book");
        int qtd = 0;
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            boolean lp = book.getAttributes().getNamedItem("category").getNodeValue().equals("LP");
            boolean en = book.getElementsByTagName("title").item(0).getAttributes().getNamedItem("lang").getNodeValue().equals("en");
            if (lp && en) {
                qtd++;
            }
        }
        System.out.println(qtd);
    }

//6. Quantos autores começam com a letra 'A'?
    public void exercicio1f() {
        NodeList authors = doc.getElementsByTagName("author");
        List<String> autores = new ArrayList();
        for (int i = 0; i < authors.getLength(); i++) {
            Element autor = (Element) authors.item(i);
            String nome = autor.getTextContent();
            if (!autores.contains(nome) && nome.startsWith("A")) {
                autores.add(nome);
            }
        }
        System.out.println(autores.size());
    }

//7. Quais autores começam com a letra 'A'?
    public void exercicio1g() {
        NodeList authors = doc.getElementsByTagName("author");
        List<String> autores = new ArrayList();
        for (int i = 0; i < authors.getLength(); i++) {
            Element autor = (Element) authors.item(i);
            String nome = autor.getTextContent();
            if (!autores.contains(nome) && nome.startsWith("A")) {
                autores.add(nome);
                System.out.println(nome);
            }
        }
    }

//8. Quais os nomes dos livros em português?
    public void exercicio1h() {
        NodeList titulos = doc.getElementsByTagName("title");
        for (int i = 0; i < titulos.getLength(); i++) {
            Element titulo = (Element) titulos.item(i);
            if (titulo.getAttribute("lang").equals("pt-br")) {
                System.out.println(titulo.getTextContent());
            }
        }
    }

//9. A média de preço dos livros em português é maior que dos livros em inglês?
    public void exercicio1i() {
        NodeList titles = doc.getElementsByTagName("title");
        double sumReal = 0, qtdReal = 0, sumDolar = 0, qtdDolar = 0;
        for (int i = 0; i < titles.getLength(); i++) {
            Element title = (Element) titles.item(i);
            Element book = (Element) title.getParentNode();
            if (title.getAttribute("lang").equals("pt-br")) {
                sumReal += Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
                qtdReal++;
            }
            if (title.getAttribute("lang").equals("en")) {
                sumDolar += Double.parseDouble(book.getElementsByTagName("price").item(0).getTextContent());
                qtdDolar++;
            }
        }
        System.out.println(sumReal / qtdReal > sumDolar / qtdDolar);
    }

//10. Quantos livros 'Abraham Silberschatz' publicou em 2012?
    public void exercicio1j() {
        NodeList books = doc.getElementsByTagName("book");
        int qtd = 0;
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            NodeList authors = book.getElementsByTagName("author");
            for (int j = 0; j < authors.getLength(); j++) {
                Element autor = (Element) authors.item(j);
                if(autor.getTextContent().equals("Abraham Silberschatz")) {
                    if(book.getElementsByTagName("year").item(0).getTextContent().equals("2012")) {
                        qtd++;
                    }
                }
            }
        }
        System.out.println(qtd);
    }

//11. Qual autor possui mais livros publicados?
    public void exercicio1k() {
        class Autor implements Comparable<Autor> {
            String nome;
            int quantidade;
            
            Autor(String n, int q) {
                this.nome = n;
                this.quantidade = q;
            }
            
            Autor(String n) {
                this.nome = n;                
            }
            
            @Override
            public boolean equals(Object o) {
                return ((Autor) o).nome.equals(nome);
            }

            @Override
            public int compareTo(Autor a) {
                return (a.quantidade > quantidade ? 1 : (a.quantidade < quantidade ? -1 : 0));
            }
        }
        
        NodeList authors = doc.getElementsByTagName("author");
        List<Autor> autores = new ArrayList();
        for(int i = 0; i < authors.getLength(); i++) {
            Element author = (Element) authors.item(i);
            String nome = author.getTextContent();
            Autor temp = new Autor(nome);
            if(!autores.contains(temp)) {
                autores.add(new Autor(nome, 1));
            } else {
                Autor a = autores.get(autores.indexOf(temp));
                a.quantidade++;
            }
        }
        Collections.sort(autores);
        int qtdMax = -1;
        for(int i = 0; i < autores.size(); i++) {
            Autor a = autores.get(i);
            if(a.quantidade >= qtdMax) {
                qtdMax = a.quantidade;
                System.out.println(a.nome + " (quant. " + a.quantidade + ")");
            }
        }
    }

    public static void main(String[] args) {
        
        DOM dom = new DOM("web/bibliography.xml");
        
        System.out.println("");
        System.out.println("1. Qual o nome dos livros que possuem mais de um autor?");
        dom.exercicio1a();
        System.out.println("");
        System.out.println("2. Quantos livros possuem mais de um autor?");
        dom.exercicio1b();
        System.out.println("");
        System.out.println("3. Qual a média de preços dos livros da categoria SO?");
        dom.exercicio1c();
        System.out.println("");
        System.out.println("4. Quantos livros a partir de 2010 possuem preço maior que 150?");
        dom.exercicio1d();
        System.out.println("");
        System.out.println("5. Quantos livros da categoria LP estão em inglês?");
        dom.exercicio1e();
        System.out.println("");
        System.out.println("6. Quantos autores começam com a letra 'A'?");
        dom.exercicio1f();
        System.out.println("");
        System.out.println("7. Quais autores começam com a letra 'A'?");
        dom.exercicio1g();
        System.out.println("");
        System.out.println("8. Quais os nomes dos livros em português?");
        dom.exercicio1h();
        System.out.println("");
        System.out.println("9. A média de preço dos livros em português é maior que dos livros em inglês?");
        dom.exercicio1i();
        System.out.println("");
        System.out.println("10. Quantos livros 'Abraham Silberschatz' publicou em 2012?");
        dom.exercicio1j();
        System.out.println("");
        System.out.println("11. Qual autor possui mais livros publicados?");
        dom.exercicio1k();
        System.out.println();
    }
}
