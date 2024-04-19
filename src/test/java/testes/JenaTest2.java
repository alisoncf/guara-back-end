/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.jena.graph.Node;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.RDFDataMgr;
import others.TDBConnection;

/**
 *
 * @author cgpre
 */
public class JenaTest2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="c:\\ontologies\\cmg.owl";
      
       
        
        Model model = ModelFactory.createDefaultModel();
        
        model.read(path);
        
        
        for(int i=1;i==model.size();i++){
            System.out.println();
        
        }
        

    }
    
}
