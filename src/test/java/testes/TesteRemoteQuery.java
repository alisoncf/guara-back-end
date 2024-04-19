/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.persistence.ConexaoTDBRemota;

/**
 *
 * @author Administrador
 */
public class TesteRemoteQuery {

    public static void main(String args[]) {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota("tipo");

            ResultSet rs;

            rs = con.getResultset("select ?s ?p ?o WHERE {?s ?p ?o}");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print(rs.getString(2));
                System.out.println(rs.getString(3));

            }
        } catch (Exception ex) {
            Logger.getLogger(TesteRemoteQuery.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
