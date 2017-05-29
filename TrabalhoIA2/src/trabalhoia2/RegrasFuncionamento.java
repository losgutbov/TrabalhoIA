/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Augusto
 */
public class RegrasFuncionamento {
    private int[][] matrizTerreno = new int[42][42];//matriz que receberá os valores referentes à localização dos terrenos
    private int[][] matrizElementos = new int[42][42];//posições de pokemons, centro e lojas.
    private int[][] posicaoAtual = new int[1][1];
    private int[] codPokemon = new int[150];
    // final garante que o valor nao sera modificado
    final int ZERO = 0;
    final int CENTRO = 152;
    final int LOJA = 153;
    final int TREINADOR = 154;
    

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
    
    public void adicionarEstimulo(int estimulo, int posiI, int posiJ){
        if((posiI!=0)&&(matrizElementos[posiI-1][posiJ]==0)){
            matrizElementos[posiI-1][posiJ] = estimulo;
        }
        if((posiI!=41)&&(matrizElementos[posiI+1][posiJ]==0)){
            matrizElementos[posiI+1][posiJ] = estimulo;
        }
        if((posiJ!=0)&&(matrizElementos[posiI][posiJ-1]==0)){
            matrizElementos[posiI][posiJ-1] = estimulo;
        }
        if((posiJ!=41)&&(matrizElementos[posiI][posiJ+1]==0)){
            matrizElementos[posiI][posiJ+1] = estimulo;
        }
    }
    
//    public void varrerMatrizParaSensores(){
//        for(int i = 0; i<matrizElementos.length; i++){
//            for(int j=0; j<matrizElementos.length; j++){
//                switch(matrizElementos[i][j]){
//                    case CENTRO:
//                        adicionarEstimulo(PERFUME, i, j);
//                        break;
//                    case LOJA:
//                        adicionarEstimulo(PROPAGANDA_BOLAS, i, j);
//                        break;
//                    case TREINADOR:
//                        adicionarEstimulo(DESAFIO, i, j);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        }        
//    }
//    
    //método para ler arquivo txt para preencher a matriz com os terrenos especificos
    public void lerMatrizTerreno() throws FileNotFoundException, IOException{
        
     
        FileReader txtMatriz = new FileReader("C:\\Users\\stephanie\\Desktop\\matrizTerrenos.txt");
        Scanner lerTxt =  new Scanner(txtMatriz).useDelimiter("\\n");
        int [][] matriz = null;
        int x;
        try {
            while(lerTxt.hasNextInt()){            
                for (int i=0; i<43;i++){
                   for(int j=0; j<43; j++){
                    x = lerTxt.nextInt(); 
                       matriz[i][j] = x;
                   }            
                }            
            }                        
        }catch(Exception IOException){
            System.err.printf("Erro na abertura do arquivo: %s.\n",IOException.getMessage());
        }
       
        for(int i=0; i<42;i++){
            for(int j=0; j<42; j++){
              System.out.print(matriz[i][j]);

            }                        
            System.out.println(" ");
        }
    }      
}
