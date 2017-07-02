/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import java.io.IOException;

/**
 *
 * @author Augusto
 */
public class TrabalhoIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        RegrasFuncionamento regra = new RegrasFuncionamento();
//        agent.terrenoGrama();
        regra.lerMatrizTerreno();
        regra.sortearTudo();
        //regra.imprimirMatriz();
        regra.lerInformacoesPokemon();
        //regra.imprimirListaPokemons();
        //regra.pesquisarElementoListaPokemons(1);
        //regra.pokedexInformacao(2);
        Interface inter = new Interface(730, 1370, regra); //Instancia objeto do tipo interface que carrega a interface gráfica do jogo, com os controles.
        AgentProlog  agente = new AgentProlog(inter);
        //agente.setMatrizTerreno(regra.getMatrizTerreno());
        
        inter.geraInterface();
        agente.executarAgente();
        //inter.outroPosicionamento(24, 20);
        //inter.outroPosicionamento(24, 18);
        //inter.outroPosicionamento(23, 19);        
        
    }
}
