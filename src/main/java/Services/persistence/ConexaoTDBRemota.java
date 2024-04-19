/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.jdbc.remote.RemoteEndpointDriver;
import org.apache.jena.jdbc.tdb.TDBDriver;

/**
 *
 * @author cgpre
 */
public class ConexaoTDBRemota extends ConexaoTDB {

    private String FusekiPath = TDBUtil.getUrlRemota();
    private String ds;

    public ConexaoTDBRemota(String ds, String fusekiPath) {
        this.FusekiPath = fusekiPath;
        this.ds = ds;
        Conecta();
    }

    public ConexaoTDBRemota(String ds) {
        this.ds = ds;
        Conecta();
    }

    private void Conecta() {

        String url = "jdbc:jena:remote:query=" + this.FusekiPath + this.ds + "/query"
                + "&update=" + this.FusekiPath + this.ds + "/update";

        System.out.println("Registrando conexão em:" + url);

        try {
            RemoteEndpointDriver.register();
            Connection con = DriverManager.getConnection(url);
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoTDBRemota.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage() + "\n" + url);
        }

    }

    @Override
    public String executa(String sql) {
        try {
            return super.executa(sql);
        } catch (Exception ex) {
            System.out.println(sql);
            Logger.getLogger(ConexaoTDBRemota.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage() + "-" + sql;
        }

    }

    @Override
    public void fecha() {
        super.fecha();
    }

    @Override
    public ResultSet getResultset(String sql) throws Exception {
        return super.getResultset(sql);

    }

    @Override
    public List<Object> getResultList(String sql) {
        return super.getResultList(sql);

    }

}
