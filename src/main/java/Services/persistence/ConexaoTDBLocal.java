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
import org.apache.jena.jdbc.tdb.TDBDriver;

/**
 *
 * @author cgpre
 */
public class ConexaoTDBLocal extends ConexaoTDB {

    

    public ConexaoTDBLocal(String ds) {

        String url = "jdbc:jena:tdb:location=" + ds;


        try {
            TDBDriver.register();
            Connection con = DriverManager.getConnection(url);
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoTDBLocal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public String executa(String sql){
        return super.executa(sql);
    }

    @Override
    public void fecha(){
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
