package trabalhoia2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Augusto
 */
public class RegrasFuncionamento {
    private int[][] matrizTerreno = new int[42][42];//matriz que receberá os valores referentes à localização dos terrenos
    private int[][] matrizElementos = new int[42][42];//posições de pokemons, centro e lojas.
    private int[] posicaoAtual = new int[2];
    private int[] codPokemon = new int[150];
    // final garante que o valor nao sera modificado
    final int ZERO = 0;
    final int CENTRO = 152;
    final int LOJA = 153;
    final int TREINADOR = 154;

    final int PERFUME = 155;    
    final int PROPAGANDA_BOLAS = 156;
    final int DESAFIO = 157;
    
    final int AGENTE = 200;
    
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

    public int[] getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int[] posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }
    
    public void setPosicaoAtual(int posicaoI, int posicaoJ) {
        this.posicaoAtual[0] = posicaoI;
        this.posicaoAtual[1] = posicaoJ;
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
    
    private void iniciarAgente(){
        //19 linhas 24 colunas
        this.matrizElementos[19][24]=AGENTE;
        this.setPosicaoAtual(19, 24);
    }
    
    private void sortearPokemon(){
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

    private void sortearCentro(){   
        Random gerador = new Random();
        
         for(int x = 0; x<20; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = CENTRO;
           }else{x--;}
       }          
    }
    
    private void sortearLoja(){   
        Random gerador = new Random();

         for(int x = 0; x<15; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = LOJA;
           }else{x--;}
       }          
    }
    
    private void sortearTreinador(){   
        Random gerador = new Random();

         for(int x = 0; x<50; x++){
           int i = gerador.nextInt(matrizElementos.length);
           int j = gerador.nextInt(matrizElementos.length);
           if(matrizElementos[i][j]==0){
               matrizElementos[i][j] = TREINADOR;
           }else{x--;}
       }          
    }

    private void adicionarEstimulo(int estimulo, int posiI, int posiJ){
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
    
    private void varrerMatrizParaSensores(){
        for(int i = 0; i<matrizElementos.length; i++){
            for(int j=0; j<matrizElementos.length; j++){
                switch(matrizElementos[i][j]){
                    case CENTRO:
                        adicionarEstimulo(PERFUME, i, j);
                        break;
                    case LOJA:
                        adicionarEstimulo(PROPAGANDA_BOLAS, i, j);
                        break;
                    case TREINADOR:
                        adicionarEstimulo(DESAFIO, i, j);
                        break;
                    default:
                        break;
                }
            }
        }        
    }
    
    public void sortearTudo(){
        this.zerarElementos();
        this.iniciarAgente();
        this.sortearPokemon();
        this.sortearCentro();
        this.sortearLoja();
        this.sortearTreinador();
        this.varrerMatrizParaSensores();
    }
    
    //método para ler arquivo txt para preencher a matriz com os terrenos especificos
    public void lerMatrizTerreno() throws FileNotFoundException, IOException{
        
        FileReader txtMatriz = new FileReader("C:\\Users\\Augusto\\Desktop\\matrizTerrenos.txt");
        Scanner lerTxt =  new Scanner(txtMatriz).useDelimiter("\n");
        int cont=0, i=0;
        try{                        
            while(lerTxt.hasNext()){
                String[] a1 = lerTxt.next().split(" ");
                for(int j=0; j<42; j++){
                    this.matrizTerreno[i][j] = Integer.parseInt(a1[j]);
                }
                i++;
            }                           
        }catch(Exception IOException){
            System.err.printf("Erro na abertura do arquivo: %s.\n",IOException.getMessage());
        }
    }      

    
}
