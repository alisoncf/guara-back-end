/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import com.google.gson.Gson;
import jena.sparql;
import org.apache.jena.graph.Node;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ModelFactoryBase;
import org.apache.jena.shacl.lib.G;
import org.apache.jena.sparql.sse.Item;
import util.RDFUtil;
import util.Stopwords;

/**
 *
 * @author cgpre
 */
public class testesvariados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Stopwords s = new Stopwords("Pedro Ludovico Teixeira na fundação de goiânia");
        System.out.print( new Gson().toJson(s.getListPalavrasAceitas()) );
        
    }
    
}
