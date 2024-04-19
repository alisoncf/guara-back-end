/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.security.auth.Subject;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.system.PrefixMap;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.lang.SPARQLParser;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import util.RDFUtil;

/**
 *
 * @author cgpre
 */
public class ODModel {

    private StringWriter sw;
    private String id;
    private String base = "http://200.137.241.247/tese-1.0/resources/ontologies";

    private URI uri;

    private Model model;

//        Resource r = new ResourceImpl("cmg:", "tipo");
//        Property p = new PropertyImpl("rdf:", "dataType");
//        RDFNode o = model.createLiteral("Isso é um teste");
//        StatementImpl st = new StatementImpl(r,p,o);
//        StringWriter sw = new StringWriter();
//        model.add(st);
//        
//        System.out.println("st-->" + st);
//        
//        System.out.println(model);
//
//        
//       
//        
//        RDFDataMgr.write(sw, model, Lang.TURTLE);
//        System.out.println(sw);
    private void GeraId() {
        this.id = UUID.randomUUID().toString();
        this.uri = URI.create(base + id);
    }

    public void addStatement(Resource s, Property p, RDFNode o) {
        model.add(s, p, o);
    }

    public void addStatement(Resource s, Property p, String o) {
        model.add(s, p, o);
    }

    public void addStatement(StatementImpl s) {
        model.add(s);
    }

    public Model getModel() {
        return this.model;
    }

    private void Inicializa() {
        GeraId();
        model = ModelFactory.createDefaultModel();
        sw = new StringWriter();
        model.setNsPrefix("rdfs", RDFS.getURI());
        model.setNsPrefix("rdf", RDF.getURI());
        model.setNsPrefix("cmg", "http://cmg.ueg.br/");
    }

    public ODModel() {
        Inicializa();
    }

    public String getTTL() {
        RDFDataMgr.write(sw, model, Lang.JSONLD);
        
        return sw.toString();
       
    }

    
    
    
    public ODModel(String base) {
        Inicializa();
        this.uri = URI.create(base + id);
    }

    public String getId() {
        return this.id;
    }

    public String getUri() {
        return this.base + this.id;
    }

    public String getSPARQLInsertData() {
        String s = "";
        Map<String, String> map = new HashMap<String, String>();

        PrefixMapping m = model.getGraph().getPrefixMapping();

        map = m.getNsPrefixMap();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            s+="prefix " + key + ": <" + value + ">\n";
//            System.out.println(String.format("key: %s | value: %s", key, value));
        }
        s+="INSERT DATA{";
        
        
//       s+=getTTL();
        
        
        s+="}";
        return s;
    }

    public URI getURI() {
        return this.uri;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

}
