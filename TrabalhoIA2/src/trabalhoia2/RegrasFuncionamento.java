/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Augusto
 */
public class RegrasFuncionamento {
    private int[][] matrizTerreno = new int[42][42];
    private int[][] matrizElementos = new int[42][42];
    private int[][] posicaoAtual = new int[1][1];
    private int[] codPokemon = new int[150];
    // final garante que o valor nao sera modificado
    final int ZERO = 0;
    final int CENTRO = 152;
    final int LOJA = 153;
    final int TREINADOR = 154;
    
    
    public void zerarElementos(){
         for(int i = 0; i < matrizElementos.length; i++){
             for(int j=0; j < matrizElementos.length; j++){
                 this.matrizElementos[i][j] = ZERO; 
             }     
         }          
    }
    
    public void imprimirMatriz(){ 
         for(int i = 0; i < matrizElementos.length; i++){
             for(int j=0; j < matrizElementos.length; j++){
                 System.out.print(matrizElementos[i][j]+" "); 
             }  
             System.out.println(" ");
         }          
    }
    
public void sortearPokemon(){
       Random gerador = new Random();
       int rand = 0;
       ArrayList<Integer> pokemonsJaIntroduzidos = new ArrayList<>();
       
       for(int x = 0; x<150; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               rand = 0;
               while(rand==0){
                   rand = 1 + gerador.nextInt(150);
                   if(pokemonsJaIntroduzidos.contains(rand)){
                       rand=0;
                   }else{pokemonsJaIntroduzidos.add(rand);}
               }
               matrizElementos[i][j] = rand;
           }else{x--;}
       }
   }

    public void sortearCentro(){   
        Random gerador = new Random();

         for(int x = 0; x<20; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = CENTRO;
           }else{x--;}
       }          
    }
    
    public void sortearLoja(){   
        Random gerador = new Random();

         for(int x = 0; x<15; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = LOJA;
           }else{x--;}
       }          
    }
    
    public void sortearTreinador(){   
        Random gerador = new Random();

         for(int x = 0; x<50; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = TREINADOR;
           }else{x--;}
       }          
    }
    
   
      
        
       
    
    
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
