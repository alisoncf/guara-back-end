/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import others.ODModel;
import java.io.PrintWriter;
import java.io.StringWriter;
import jena.rdfparse;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.riot.RDFParserBuilder;

/**
 *
 * @author cgpre
 */
public class TesteRDFModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Model model = ModelFactory.createDefaultModel();
        Resource r = new ResourceImpl("cmg:", "tipo");
        Property p = new PropertyImpl("rdf:", "dataType");
        RDFNode o = model.createLiteral("Isso é um teste");
        StatementImpl st = new StatementImpl(r,p,o);
        StringWriter sw = new StringWriter();
        model.add(st);
        
//        System.out.println("st-->" + st);
//        
//        System.out.println(model);
//
//        
//       
//        
//        RDFDataMgr.write(sw, model, Lang.TURTLE);
//        
//        System.out.println(sw);
//        
        
        
        ODModel m = new ODModel("www.ueg.br");
        m.addStatement(st);
//        System.out.println(m.getTTL());
        System.out.println(m.getSPARQLInsertData());
        
    }
    
}
