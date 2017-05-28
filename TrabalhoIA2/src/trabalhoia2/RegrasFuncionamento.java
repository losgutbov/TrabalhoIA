/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

/**
 *
 * @author Augusto
 */
public class RegrasFuncionamento {
    private int[][] matrizTerreno = new int[42][42];
    private int[][] matrizElementos = new int[42][42];
    private int[][] posicaoAtual = new int[1][1];
    private int[] codPokemon = new int[150];
    
    
    public RegrasFuncionamento() {
    }

    public int[][] getMatrizTerreno() {
        return matrizTerreno;
    }

    public void setMatrizTerreno(int[][] matrizTerreno) {
        this.matrizTerreno = matrizTerreno;
    }

    private int[][] getMatrizElementos() {
        return matrizElementos;
    }

    private void setMatrizElementos(int[][] matrizElementos) {
        this.matrizElementos = matrizElementos;
    }

    public int[][] getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int[][] posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }
    
    
}
