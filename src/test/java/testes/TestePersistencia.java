/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import Entidades.ObjetoDigital;
import Services.DAO.ObjetoDigitalDao;
import com.github.jsonldjava.utils.Obj;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Services.persistence.ConexaoTDBLocal;
import Services.persistence.ConexaoTDBRemota;

/**
 *
 * @author cgpre
 */
public class TestePersistencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        ObjetoDigitalDao dao = new ObjetoDigitalDao();
        List<ObjetoDigital> list = new ArrayList();

        String sql = "prefix owl: <http://www.w3.org/2002/07/owl#>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "\n"
                + "SELECT DISTINCT ?class ?label ?description\n"
                + "WHERE {\n"
                + "  ?class a owl:Class.\n"
                + "  OPTIONAL { ?class rdfs:label ?label}\n"
                + "  OPTIONAL { ?class rdfs:comment ?description}\n"
                + "}";
        list = dao.getListObjDigital(sql , "tipo");

        
        for (int i = 0; i < list.size(); i++) {
            ObjetoDigital get = list.get(i);
            System.out.println(get.getUri() + " " + 
                    get.getPropval().get(0).getPropriedade().getNome() + 
                    " " + get.getPropval().get(0).getValor().get(0).getValor());
        }
        

    }

    void testaResultset() throws Exception {
        ConexaoTDBRemota con = new ConexaoTDBRemota("ds");
//        String str="INSERT DATA{<www.ueg.br> <rdfs:type> \"site\".}";
//        con.executa(str);
        String sql = "SELECT ?x ?y ?z WHERE{?x ?y ?z}";
        ResultSet r = con.getResultset(sql);

        System.out.println("-----------------");
        try {
            while (r.next()) {

                System.out.println(r.getString(1) + " -  "
                        + r.getString(2) + " - " + r.getString(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.fecha();
    }

}
