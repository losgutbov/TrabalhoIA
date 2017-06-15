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
        regra.lerMatrizTerreno();
        regra.sortearTudo();
        regra.imprimirMatriz();
        
        Interface inter = new Interface(730, 1370); //Instancia objeto do tipo interface que carrega a interface gráfica do jogo, com os controles.
        inter.geraInterface(regra.getMatrizTerreno());
        
    }
    
}
