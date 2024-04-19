/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import Entidades.ObjetoDigital;
import Entidades.Prefixo;
import Entidades.PropVal;
import Entidades.Propriedade;
import Entidades.Valor;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author cgpre
 */
public class objTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException {
        // TODO code application logic here
        ObjetoDigital obj= new ObjetoDigital();

        PropVal prop = new PropVal();
        
        Propriedade propriedade = new Propriedade("rdfs", "type");
        prop.setPropriedade(propriedade);
        Valor v = new Valor();
        v.setTipo(Valor.Tipo.URI);
        v.setValor("http://cmg.org.br/terms.owl#receita");
        
        prop.addValor(v);
        obj.addPropVal(prop);
        
        
        propriedade = new Propriedade("dc", "title");
        
        prop = new PropVal(propriedade);
        v=new Valor();
        v.setTipo(Valor.Tipo.LITERAL);
        v.setValor("Bolo de Banana");
        
        prop.addValor(v);
        obj.addPropVal(prop);
        
        propriedade = new Propriedade("dc", "subject");
        prop = new PropVal(propriedade);
        v = new Valor();
        v.setTipo(Valor.Tipo.LITERAL);
        v.setValor("um teste de receita de bolo de banana");
        prop.addValor(v);
        obj.addPropVal(prop);
        
        


        
        
        
        System.out.println(obj.getSPARQLInsertN3());
        
        
        
        
        
        
    }
    
}
