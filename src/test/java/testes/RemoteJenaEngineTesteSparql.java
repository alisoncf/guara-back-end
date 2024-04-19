/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;


public class RemoteJenaEngineTesteSparql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // populate SPARQL SELECT Query string
        String SERVICE_URL = "http://localhost:3030/ds/query";
        StringBuilder sb = new StringBuilder();
        sb.append("PREFIX books:   <http://example.org/book/>").append("\n");
        sb.append("PREFIX dc:      <http://purl.org/dc/elements/1.1/>").append("\n");
        sb.append("SELECT ?book ?title").append("\n");
        sb.append("WHERE {").append("\n");
        sb.append("		?book dc:title ?title").append("\n");
        sb.append("}").append("\n");

        // query from remote service
        QueryExecution qexec = QueryExecutionFactory.sparqlService(SERVICE_URL, sb.toString());

        System.out.println("Plan to run remote SPARQL query: ");
        System.out.println("");
        System.out.println(sb.toString());
        System.out.println("");

        ResultSet rs = qexec.execSelect();

        // use result set formatter
        System.out.println(ResultSetFormatter.asXMLString(rs));

        qexec.close();
    }

}
