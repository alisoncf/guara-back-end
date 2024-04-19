/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

/**
 *
 * @author cgpre
 */

import Entidades.ObjetoDigital;
import Services.DAO.ObjetoDigitalDao;
public class TesteRetornoSparql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
      
        String xml="";
        String select = "";
        
        ObjetoDigital obj = new ObjetoDigital();
        ObjetoDigitalDao dao = new ObjetoDigitalDao(obj);
        select = dao.getSPARQL_SelectClass("c");
        xml = dao.getXMLSparqlResult(select, "tipo");
        
        System.out.println(xml);
    }
    
}
