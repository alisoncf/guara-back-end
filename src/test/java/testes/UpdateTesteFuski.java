/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.util.UUID;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

/**
 *
 * @author cgpre
 */
public class UpdateTesteFuski {

    /**
     * @param args the command line arguments
     */
    private static final String UPDATE_TEMPLATE
            = "PREFIX dc: <http://purl.org/dc/elements/1.1/>"
            + "INSERT DATA"
            + "{ <http://example/%s>    <dc:title>    \"Um título\" ;"
            + "                         dc:creator  \"Um autor\" ." + "}   ";

    public static void main(String[] args) {
        //Add a new book to the collection
        String id = UUID.randomUUID().toString();
        System.out.println(String.format("Adding %s", id));
        UpdateProcessor upp = UpdateExecutionFactory.createRemote(
                UpdateFactory.create(String.format(UPDATE_TEMPLATE, id)),
                "http://localhost:3030/ds/update");
        upp.execute();
        //Query the collection, dump output
        QueryExecution qe = QueryExecutionFactory.sparqlService(
                "http://localhost:3030/ds/query", "SELECT * WHERE {?x ?r ?y}");
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results);
        qe.close();
    }

}
