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


//-------------------------INFORMAÇÕES POKEMONS----------------------//
/*CLASSE POKEMON ARMAZERNA INFORMAÇÕES DO NOME, DESCRIÇÃO, TIPOS E IDENTIFICADOR QUE  VEM 
  DO ARQUIVO TXT COM AS INFORMAÇÕES DOS 150 POKEMONS.*/
public class Pokemon {
    
    private String nome, descricao, tipo;
    private int identificador;
    private String tipos[];

    public Pokemon(){}

    //-------------------------MÉTODOS GET E SET---------------------------------//
    public String[] getTipos() {
        return tipos;
    }

    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }
    
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
    //-------------------------FIM MÉTODOS GET E SET---------------------------------//
    
    //-------------------------MÉTODO POKEDEX---------------------------------//
    /*METODO QUE EXIBE AS INFORMAÇÕES DO POKEMON ATRAVÉS DA POKEDEX QUANDO O AGENTE SE APROXIMA DE UM POKEMON*/
    public String toString() {
        return "Identificador:" + identificador + "\n" + "Nome:" +nome + "\n" + "Descrição:" + descricao + 
        "\n" + "Tipo(s):" + tipos[0]+","+tipos[1]+","+tipos[2] + "\n";
    }
    //-------------------------FIM DO MÉTODO POKEDEX---------------------------------//
    
    //-------------------------MÉTODO POKEMON CASA ATUAL DO AGENTE---------------------------------//
    /*METODO UTILIZADO PASSA AS INFORMAÇÕES DO POKEMON QUE ESTÃO NA MESMA COORDENADA QUE O AGENTE*/
    public String stringToProlog() {
        return nome+","+identificador+","+tipos[0]+","+tipos[1]+","+tipos[2];
    }
    //-------------------------FIM DO MÉTODO POKEMON CASA ATUAL DO AGENTE---------------------------------//
}

