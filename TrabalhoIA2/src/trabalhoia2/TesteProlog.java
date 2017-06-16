/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoia2;

import org.jpl7.Query;
import java.util.Hashtable;
import java.util.Map;
import org.jpl7.Term;

/**
 *
 * @author stephanie
 */
public class TesteProlog {
    
    public static void main(String args[]){
        
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
