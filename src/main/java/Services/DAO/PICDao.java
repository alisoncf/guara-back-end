/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.DAO;

import Entidades.ObjetoDigital;
import Entidades.PIC;
import Entidades.PIDO;
import Entidades.PropVal;
import Entidades.Propriedade;
import Entidades.Valor;
import java.io.Serializable;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.jdbc.results.SelectResults;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.thrift.TBase;
import Services.persistence.ConexaoTDB;
import Services.persistence.ConexaoTDBLocal;
import Services.persistence.ConexaoTDBRemota;
import Services.persistence.TDBUtil;
import util.RDFUtil;

/**
 *
 * @author cgpre
 */
public class PICDao implements Serializable {

    private static final long serialVersionUID = -1L;
    private String tdb_classes;

    private String SPARQL_SelectClass;

    private PIC pic;

    private List<PIC> listaPic;

    public String getSPARQL_SelectClass(String keyword) {

        SPARQL_SelectClass = "prefix owl: <http://www.w3.org/2002/07/owl#> \n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "SELECT DISTINCT ?class ?label ?description ?subclassof WHERE{?class a owl:Class. \n"
                + "  OPTIONAL { ?class rdfs:label ?label} \n"
                + "  OPTIONAL { ?class rdfs:comment ?description} \n "
                + "  OPTIONAL { ?class rdfs:subClassOf ?subclassof} \n";

        if (keyword != "" && keyword != null) {
            SPARQL_SelectClass += "filter ((!bound(?description)||(regex(?description,\":keyword\",\"i\")))||(!bound(?label)||(regex(?label,\":keyword\",\"i\") ))) \n";
        }
        SPARQL_SelectClass += "}";

        SPARQL_SelectClass.replaceAll(":keyword", keyword);

        return SPARQL_SelectClass;

    }

    private void init() {
        listaPic = new ArrayList<>();

    }

    public PICDao(PIC pic) {
        this.tdb_classes = TDBUtil.getTDBClasse();
        this.pic = pic;
        init();
    }

    public PICDao() {
        this.tdb_classes = TDBUtil.getTDBClasse();

        this.pic = new PIC();
        init();
    }

    public PIC getPic() {
        return pic;
    }

    public void setPic(PIC pic) {
        this.pic = pic;
    }

    public void CriarNovoPicRem(String ds) {
        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
        con.executa(pic.getSPARQLInsertN3());
        con.fecha();
    }

    public void CriarNovoPicRem(String nomeClass, String label, String subClassOf, String comment) {
        PIC p = new PIC(nomeClass, label, comment, subClassOf);
        ConexaoTDBRemota con = new ConexaoTDBRemota(tdb_classes);
        con.executa(p.getSPARQLInsertN3());
        con.fecha();
    }

    public void CriarNovoPicRem(PIC p) {
        ConexaoTDBRemota con = new ConexaoTDBRemota(tdb_classes);
        con.executa(p.getSPARQLInsertN3());
        con.fecha();
    }

    public void AlterarPicRem(String nomeClass, String label, String subClassOf, String comment) {
        PIC p = new PIC(nomeClass, label, comment, subClassOf);
        ConexaoTDBRemota con = new ConexaoTDBRemota(tdb_classes);
        con.executa(p.getSPARQLDeleteInsertN3());
        con.fecha();
    }

    public void AlterarPicRem(PIC p) {
        ConexaoTDBRemota con = new ConexaoTDBRemota(tdb_classes);
        con.executa(p.getSPARQLDeleteInsertN3());
        con.fecha();
    }

    public void CriarNovoObjLoc(String ds) {
        ConexaoTDBLocal con = new ConexaoTDBLocal(ds);
        con.executa(pic.getSPARQLInsertN3());
        con.fecha();
    }

    public boolean GravarPic(PIC pic) {
        try {
            CriarNovoPicRem(pic);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean AlterarPic(String className, String label, String subClassOf, String comment) {
        try {
            AlterarPicRem(className, label, subClassOf, comment);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<PIC> getListPIC() {
        return this.listaPic;
    }

    public List<PIC> getListClasses(String keyword) throws Exception {
        List<PIC> list = new ArrayList();

        ConexaoTDBRemota con = new ConexaoTDBRemota(this.tdb_classes);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
        ResultSet rs = con.getResultset(getSPARQL_SelectClass(keyword));
        try {
            while (rs.next()) {
                PIC p = new PIC();
                p.setClassName(rs.getString("class"));
                p.setLabel(rs.getString("label"));
                p.setComment(rs.getString("description"));
                p.setSubClassOf(rs.getString("subclassof"));
                list.add(p);
            }

        } catch (SQLException ex) {

            Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void pesquisar(String kw) {
        try {
            this.listaPic = getListClasses(kw);
        } catch (Exception ex) {
            Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getXMLSparqlResult(String sql, String ds) throws Exception {
        String xml = "<?xml version=\"1.0\"?>";
        String head = "";
        String results = "";
        xml += "<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n";

        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);

        try {
            ResultSet rs = con.getResultset(sql);
            ResultSetMetaData meta = rs.getMetaData();
            SelectResults sr = null;
            sr = (SelectResults) rs.getClass().cast(sr);

            head = "<head>\n";
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                head += "<variable name\"" + meta.getColumnName(i) + "\"/>\n";

            }
            head += "</head>\n";
            xml += head;
            results = "<results>\n";
            while (rs.next()) {

                results += "<result>\n";
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String s = rs.getString(i);

                    results += "<binding name=\"" + meta.getColumnName(i) + "\">\n";

                    if (RDFUtil.getTipo(s) == RDFUtil.TipoRecurso.LITERAL) {
                        s = "<literal>" + s + "</literal>\n";
                    } else {
                        s = "<uri>" + s + "</uri>\n";
                    }

                    results += s + "</binding>\n";
                }
                results += "</result>\n";
            }
            xml += results + "</results>\n</sparql>";
        } catch (SQLException ex) {
            Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage() + "\n" + sql);
        }

        return xml;
    }

    public String getSPARQLDestroy() {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        String uri = "<" + pic.getClassName() + ">";
        s += "DELETE {"
                + uri + "?p ?v } where {"
                + uri + "?p ?v }";
        System.out.println("Destruindo classe\n" + s);
        return s;

    }

    public boolean destruir() {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota(TDBUtil.getTDBClasse());
            con.executa(
                    getSPARQLDestroy());
            con.fecha();
            return true;
        } catch (Exception e) {

            return false;

        }

    }

}
