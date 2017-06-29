/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

/**
 *
 * @author Thamires
 */
public class Pokemon {
    
    private String nome, descricao, tipo;
    private int identificador;
   

    public Pokemon(){}
    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String toString() {
        return "Identificador:" + identificador + "\n" + "Nome:" +nome + "\n" + "Descrição:" + descricao + 
        "\n" + "Tipo(s):" + tipo + "\n";
    }
}

