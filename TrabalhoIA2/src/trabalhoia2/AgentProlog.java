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
    private String[] vetorTerreno = {"grama", "agua", "montanha", "caverna", "vulcao"};
    
    public int[][] getMatrizTerreno() {
        return matrizTerreno;
    }

    public void setMatrizTerreno(int[][] matrizTerreno) {
        this.matrizTerreno = matrizTerreno;
    }

    public void executarAgente(){
        String comando = "consult('controlesPoke.pl')";
        Query execucaoComando = new Query(comando);
        System.out.println(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
        comando = "inicializar";
        execucaoComando = new Query(comando);
        System.out.println(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
        for(int i = 0; i<42; i++){
            for(int j= 0; j<42; j++){
                comando = "armazenarTerrenos("+i+","+j+","+vetorTerreno[getMatrizTerreno()[i][j]]+")";
                execucaoComando = new Query(comando);
                System.out.println(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
            }
        }
        comando = "operacao(X,Y)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
        Map<String, Term> s4 = execucaoComando.nextSolution();
        System.out.println("X " + s4.get("X") + ", Y " + s4.get("Y"));
        
        /*    
        while(execucaoComando.hasMoreSolutions()){
            Map<String, Term> s4 = execucaoComando.nextSolution();
            System.out.println("X " + s4.get("X") + ", Y " + s4.get("Y"));
        }
        //System.out.println(comando + " " + (execucaoComando.getSolution().get("K").toString())+" "+execucaoComando.getSolution().get("W").toString());
        //*/
    }
    
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
