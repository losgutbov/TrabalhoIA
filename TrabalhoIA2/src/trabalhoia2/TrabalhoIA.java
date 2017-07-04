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

/*---------------CLASSE MAIN DO PROJETO--------*/
public class TrabalhoIA {

    //-------------------------MÉTODO MAIN---------------------------------//
    /* MÉTODO UTILIZADO PARA INSTANCIAR OS DEMAIS OBJETOS DO PROJETO E CHAMAR SEUS MÉTODOS PRINCIPAIS*/
    public static void main(String[] args) throws IOException {
        RegrasFuncionamento regra = new RegrasFuncionamento();
        regra.lerMatrizTerreno();
        regra.sortearTudo();
        regra.lerInformacoesPokemon();
        Interface inter = new Interface(730, 1370, regra); //INSTANCIA O OBJETO DO TIPO INTERFACE QUE CARREGA A INTERFACE GRÁFICA DO JOGO, COM OS CONTROLES.
        AgentProlog  agente = new AgentProlog(inter); //INSTANCIA O OBJETO DO TIPO AGENTE PROLOG QUE FAZ A COMUNICAÇÃO ENTRE O PROLOG E O JAVA.
        inter.geraInterface();
        agente.executarAgente();
        //System.out.println(regra.getListaPokemons().get(0).stringToProlog());   
    }
    //-------------------------FIM DO MÉTODO MAIN---------------------------------//
}
