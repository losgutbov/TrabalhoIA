/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import org.jpl7.Query;
import java.util.Map;
import org.jpl7.Term;

/**
 *
 * @author stephanie
 */
public class AgentProlog {
    
    private int[][] matrizTerreno = new int[42][42];//matriz que receberá os valores referentes à localização dos terrenos
    private String[] vetorTerreno = {"grama", "agua", "montanha", "caverna", "vulcao"};
    private Interface inter;
    
    public int[][] getMatrizTerreno() {
        return matrizTerreno;
    }

    public void setMatrizTerreno(int[][] matrizTerreno) {
        this.matrizTerreno = matrizTerreno;
    }

    public AgentProlog(Interface inter){
        this.inter = inter;
    }
    
    public void executarAgente(){
        String comando;
        Query execucaoComando;
        Map<String, Term> results;
        int coordenadaX, coordenadaY, pontos, pokebolas, carga, totalPokemons, teste=0;
        
        comando = "consult('controlesPoke.pl')";
        execucaoComando = new Query(comando);
        System.out.println(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
        comando = "inicializar";
        execucaoComando = new Query(comando);
        System.out.println(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
        for(int i = 0; i<42; i++){
            for(int j= 0; j<42; j++){
                //comando = "armazenarTerrenos("+i+","+j+","+vetorTerreno[getMatrizTerreno()[i][j]]+")";
                comando = "armazenarTerrenos("+j+","+i+","+vetorTerreno[inter.getRegras().getMatrizTerreno()[i][j]]+")";
                execucaoComando = new Query(comando);
                System.out.print(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
                execucaoComando.hasSolution();
            }
            //System.out.println();
        }
       while(teste<10){
            comando = "passarInformacoes(CoordenadaX, CoordenadaY, Pontos, Pokebolas, Carga, TotalPokemons)";
            execucaoComando = new Query(comando);
            execucaoComando.hasMoreSolutions();
            results = execucaoComando.nextSolution();
            System.out.println("CoordenadaX " + results.get("CoordenadaX") + ", CoordenadaY " + results.get("CoordenadaY")+", Pontos "+ results.get("Pontos")+", Pokebolas "+ results.get("Pokebolas")+", Carga "+ results.get("Carga")+", TotalPokemons "+ results.get("TotalPokemons"));
            coordenadaX = Integer.parseInt(results.get("CoordenadaX").toString());
            coordenadaY = Integer.parseInt(results.get("CoordenadaY").toString());
            pontos = Integer.parseInt(results.get("Pontos").toString());
            pokebolas = Integer.parseInt(results.get("Pokebolas").toString());
            carga = Integer.parseInt(results.get("Carga").toString());
            totalPokemons = Integer.parseInt(results.get("TotalPokemons").toString());
            inter.repassarInterface(coordenadaX, coordenadaY, pontos, pokebolas, carga, totalPokemons);
           
            try {
                Thread.sleep(1000);
             } catch (Exception e) {
                e.printStackTrace();
             }
            
            //-------
            comando = "operacao(X,Y)";
            execucaoComando = new Query(comando);
            execucaoComando.hasMoreSolutions();
           //-------
           teste++;
       }
       /*
       comando = "passarInformacoes(CoordenadaX, CoordenadaY, Pontos, Pokebolas, Carga, TotalPokemons)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
        results = execucaoComando.nextSolution();
        System.out.println("CoordenadaX " + results.get("CoordenadaX") + ", CoordenadaY " + results.get("CoordenadaY")+", Pontos "+ results.get("Pontos")+", Pokebolas "+ results.get("Pokebolas")+", Carga "+ results.get("Carga")+", TotalPokemons "+ results.get("TotalPokemons"));
       //-------
        comando = "operacao(X,Y)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
       //-------  
        comando = "passarInformacoes(CoordenadaX, CoordenadaY, Pontos, Pokebolas, Carga, TotalPokemons)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
        results = execucaoComando.nextSolution();
        System.out.println("CoordenadaX " + results.get("CoordenadaX") + ", CoordenadaY " + results.get("CoordenadaY")+", Pontos "+ results.get("Pontos")+", Pokebolas "+ results.get("Pokebolas")+", Carga "+ results.get("Carga")+", TotalPokemons "+ results.get("TotalPokemons"));
       //-------
        comando = "operacao(X,Y)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
       //-------  
        comando = "passarInformacoes(CoordenadaX, CoordenadaY, Pontos, Pokebolas, Carga, TotalPokemons)";
        execucaoComando = new Query(comando);
        execucaoComando.hasMoreSolutions();
        results = execucaoComando.nextSolution();
        System.out.println("CoordenadaX " + results.get("CoordenadaX") + ", CoordenadaY " + results.get("CoordenadaY")+", Pontos "+ results.get("Pontos")+", Pokebolas "+ results.get("Pokebolas")+", Carga "+ results.get("Carga")+", TotalPokemons "+ results.get("TotalPokemons"));
        
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
