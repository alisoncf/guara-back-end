
package Services.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class ConexaoTDB {
    protected Statement st;
    public String executa(String sql) {
        try {
            st.execute(sql);
            return "Sucesso";
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoTDB.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
    }
    public ResultSet getResultset(String sql) throws SQLException, Exception {
        ResultSet r;
        try {
            r = st.executeQuery(sql);
            return r;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()+"\n"+sql);
            throw new Exception(ex.getMessage());
        }
    }
    public List<Object> getResultList(String sql) {
        ResultSet r;
        List<Object> l = new ArrayList();
        try {
            r = st.executeQuery(sql);
            l = (List<Object>) r;
            return (List<Object>) r;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+"\n"+sql);
            Logger.getLogger(ConexaoTDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    public void fecha() {
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoTDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
