/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Administrador
 */
public abstract class prefixos {
    
    public static String ListaPrefixos(){
        String s ="PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
            + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
            + "PREFIX cmg: <http://www.cmg.ueg.br/schema>\n"
            + "PREFIX obj: <http://200.137.241.247:8080/fuseki/objetos#>\n"
            + "PREFIX cls: <http://200.137.241.247:8080/fuseki/classes#>\n"
            + "PREFIX dim: <http://200.137.241.247:8080/fuseki/dimensoes#>\n"
            + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n";
    
    return s;
    }
    
}
