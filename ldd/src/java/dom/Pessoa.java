package dom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Pessoa {

    private String nome;
    private double salario;
    private int idade;
    
    public Pessoa(String nome, double salario, int idade) {
        this.nome = nome;
        this.salario = salario;
        this.idade = idade;
    }

    public Pessoa() {
    }

//    @XmlAttribute
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    @XmlElement(name = "salary")
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @XmlTransient
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, Idade: %d, Salário: %f", nome, idade, salario);
    }
    
}
