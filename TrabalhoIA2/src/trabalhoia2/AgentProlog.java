/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.io.FileReader;
import java.io.IOException;
import org.jpl7.Query;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import org.jpl7.Term;

/**
 *
 * @author stephanie
 */
public class AgentProlog {
    
    private int[][] matrizTerreno = new int[42][42];//matriz que receberá os valores referentes à localização dos terrenos
    
    
//    public void terrenoGrama() throws IOException{
//        
//        FileReader txtMatriz = new FileReader("C:\\Users\\Augusto\\Desktop\\matrizTerrenos.txt");
//        Scanner lerTxt =  new Scanner(txtMatriz).useDelimiter("\n");
//        int cont=0, i=0;
//        try{                        
//            while(lerTxt.hasNext()){
//                String[] a1 = lerTxt.next().split(" ");
//                for(int j=0; j<42; j++){
//                    this.matrizTerreno[i][j] = Integer.parseInt(a1[j]);
//                    if(this.matrizTerreno[i][j] == 0){
//                    
//                    }
//                    
//                    System.out.println(matrizTerreno[i][j]);
//                }
//                i++;
//            }                           
//        }catch(Exception IOException){
//            System.err.printf("Erro na abertura do arquivo: %s.\n",IOException.getMessage());
//        }
//    }
    
        
            
            
    
    public static void main(String args[]){
        
        //Alterar para o arquivo controlesPoke.pl.
        
        String t1 = "consult('teste.pl')";
        Query q1 = new Query(t1);
        System.out.println(t1 + " " + (q1.hasSolution() ? "correto" : "falhou"));
        String t2 = "pai(lima,rodrigo)";
        Query q2 = new Query(t2);
        System.out.println(t2 + " " + (q2.hasSolution() ? "correto" : "falhou"));
        String t3 = "pai(X,Y)";
        Query q3 = new Query(t3);
        System.out.println(t3 + " " + (q3.hasSolution() ? "correto" : "falhou"));
        while(q3.hasMoreSolutions()){
            Map<String, Term> s4 = q3.nextSolution();
            System.out.println("X " + s4.get("X") + ", Y " + s4.get("Y"));
        
        }
    }
    
}
