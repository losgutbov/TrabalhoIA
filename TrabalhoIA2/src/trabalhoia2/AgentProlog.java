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
                comando = "armazenarTerrenos("+j+","+i+","+vetorTerreno[inter.getRegras().getMatrizTerreno()[i][j]]+")";
                execucaoComando = new Query(comando);
                System.out.print(comando + " " + (execucaoComando.hasSolution() ? "correto" : "falhou"));
                execucaoComando.hasSolution();
            }
        }
       while(teste<30){
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
           
            inter.elementosDaCasa();
            
            //-------
            comando = "operacao(X,Y)";
            execucaoComando = new Query(comando);
            execucaoComando.hasMoreSolutions();
           //-------
           teste++;
           
       }
    }
}
