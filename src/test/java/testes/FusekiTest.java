/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.jena.jdbc.JenaJDBC;

import org.apache.jena.jdbc.tdb.TDBDriver;

/**
 *
 * @author cgpre
 */
public class FusekiTest {

    public static void main(String[] args) throws Exception{
        // TODO code application logic here
//        String url = "jdbc:jena:tdb:location=ds";
        String url = "jdbc:jena:remote:query=http://200.137.241.247:8080/fuseki/ds/query"
                + "&update=http://200.137.241.247:8080/fuseki/ds/update";
        try {
            TDBDriver.register();
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();

            String dados = "PREFIX aop:<http://mypersonallink.com/person/>\n"
                    + "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                    + "PREFIX foaf:<http://xmlns.com/foaf/0.1/>\n"
                    + "INSERT DATA {\n"
                    + "aop:TestfirstTestlast a foaf:Person ;\n"
                    + "<foaf:name> \"pedro\" ;\n"
                    + "foaf:mbox <mailto:pedro@mail.com> .\n"
                    + "}";

            st.execute(dados);
            st.close();
            
            
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
