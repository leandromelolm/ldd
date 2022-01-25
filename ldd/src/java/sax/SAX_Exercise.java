package sax;

/**
 * EXERCÍCIO
 * 
 * Baseado no arquivo bibliography.xml, implementar programas na linguagem de programação Java utilizando SAX que selecionem as 
 * seguintes informações: 
 * 
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
 * 
 */

public class SAX_Exercise {}


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