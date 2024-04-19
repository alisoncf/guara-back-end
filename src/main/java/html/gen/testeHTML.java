/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgpre
 */
public class testeHTML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<String> l = new ArrayList();
       
        l.add("Coluna 1");
        l.add("Coluna 2");
        l.add("Coluna 3");
        HTMLUtil h = new HTMLUtil();
        
        System.out.println(h.GeraLinhaCabTable(l, "class=\"tabela\"", ""));
        
    }
    
}
