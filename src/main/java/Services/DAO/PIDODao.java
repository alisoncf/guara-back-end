/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.DAO;

import Entidades.ObjetoDigital;
import Entidades.PIC;
import Entidades.PIDO;
import Entidades.PPV;
import Entidades.PropVal;
import Entidades.Propriedade;
import Entidades.TriplaSimples;
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
import Services.persistence.ConexaoTDB;
import Services.persistence.ConexaoTDBLocal;
import Services.persistence.ConexaoTDBRemota;
import Services.persistence.TDBUtil;
import util.RDFUtil;

/**
 *
 * @author cgpre
 */
public class PIDODao implements Serializable {

    private static final long serialVersionUID = -1L;
    private String SPARQL_SelectClass = "prefix owl: <http://www.w3.org/2002/07/owl#>\n"
            + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "SELECT DISTINCT ?class ?label ?description WHERE{?class a owl:Class.\n"
            + "  OPTIONAL { ?class rdfs:label ?label}\n"
            + "  OPTIONAL { ?class rdfs:comment ?description} filter (regex(?description,\":keyword\",\"i\")||regex(?label,\":keyword\",\"i\") )}";

    private PIDO pido;
    private List<PIDO> listaPIDO;

    public String getSPARQL_SelectClass(String keyword) {
        SPARQL_SelectClass = this.SPARQL_SelectClass.replaceAll(":keyword", keyword);
        return SPARQL_SelectClass;
    }

    private void init() {
        listaPIDO = new ArrayList<>();

    }

    public PIDODao(PIDO pido) {
        this.pido = pido;
    }

    public PIDODao() {
        this.pido = new PIDO();
    }

    public PIDO getObjeto() {
        return pido;
    }

    public void setObjeto(PIDO objeto) {
        this.pido = objeto;
    }

    public String CriarNovoObjRem(String ds) {
       
        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
        String ret = con.executa(getSPARQLInsertN3());
        con.fecha();
        return ret;
    }

    public String getSPARQLInsertN3() {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        s = s + "INSERT DATA{";
        s = s + pido.getN3();
        s = s + "}";
        System.out.println(s);
        return s;
        
    }

    public String getSPARQLDestroy() {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        String uri = "<" + pido.getUri().toString() + ">";
        s += "DELETE {"
        +uri + "?p ?v } where {" 
                + uri + "?p ?v }";
        System.out.println("Destruindo objeto\n" + s );
        return s;
        
    }
    public String getSPARQLDestroyTriple(TriplaSimples tripla) {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        String uri = "<" + pido.getUri().toString() + ">";
        s += "DELETE {"
         + tripla.getSujeito()  + "  " + tripla.getPropriedade() + " " + tripla.getObjeto() + "} ";
        System.out.println("Destruindo objeto\n" + s );
        return s;
        
    }
    public boolean gravar(String ds) {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
            con.executa(
                    getSPARQLInsertN3());
            con.fecha();
            return true;
        } catch (Exception e) {

            return false;

        }

    }

    public boolean alterar(String ds) {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
            con.executa(
                    getSPARQLInsertN3());
            con.fecha();
            return true;
        } catch (Exception e) {

            return false;

        }

    }
 public boolean destruir(String ds) {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
            con.executa(
                    getSPARQLDestroy());
            con.fecha();
            return true;
        } catch (Exception e) {

            return false;

        }

    }
 public boolean destruirRelacao( String ds,TriplaSimples tripla) {
        try {
            ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
            con.executa(
                    getSPARQLDestroy());
            con.fecha();
            return true;
        } catch (Exception e) {

            return false;

        }

    }
    public void CriarNovoObjLoc(String ds) {
        ConexaoTDBLocal con = new ConexaoTDBLocal(ds);
        con.executa(getSPARQLInsertN3());
        con.fecha();
    }

    public List<PIDO> getListObjDigital(String x, String y, String z, String ds) throws Exception {
        String sql = "SELECT ?x ?y ?z "
                + "WHERE {" + x + " " + y + " " + z + "}";
        return getListObjDigital(sql, ds);
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
            Logger.getLogger(PIDODao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

    public List<PIDO> getLisdOD() {
        return this.listaPIDO;
    }

    private String getSparqlPropValObj(String id) {

        String sql = "prefix owl: <http://www.w3.org/2002/07/owl#> "
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT DISTINCT ?prop  ?val "
                + " WHERE{ "
                + "<" + id + "> " + "?prop ?val."
                + "}";

        return sql;
    }

    private String getSparqlPadrao(String kw) {

        String sql = "prefix owl: <http://www.w3.org/2002/07/owl#> "
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT DISTINCT ?s ?v ?o ?q ?x"
                + " WHERE{ "
                + "?s dc:title ?v."
                + "?s dc:subject ?o."
                + "?s rdfs:type ?q."
                + "?s dc:description ?x."
                + "filter (regex(?s,'" + kw + "','i')||regex(?v,'" + kw + "','i') )"
                + "}";
        return sql;
    }

    private String getDSPadrao() {
        return TDBUtil.getTDBObjeto();
    }

    public List<PIDO> getListObjDigital(String sql, String ds) throws Exception {
        if (sql == "") {
            sql = getSparqlPadrao("");
        }
        if (ds == "") {
            ds = getDSPadrao();
        }
        List<PIDO> list = new ArrayList();
        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
        ResultSet rs = con.getResultset(sql);
        try {
            while (rs.next()) {
                PIDO obj = new PIDO();

                obj.setId(rs.getString(1));
                PPV p = new PPV(rs.getString(2), rs.getString(3));

                obj.addProp(p);

                list.add(obj);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PIDODao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<ObjetoDigital> getListObjDigital(String kw) throws Exception {

        String sql = getSparqlPadrao(kw);

        String ds = getDSPadrao();

        List<ObjetoDigital> list = new ArrayList();

        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
        ResultSet rs = con.getResultset(sql);
        try {
            while (rs.next()) {
                ObjetoDigital obj = new ObjetoDigital();
                obj.setUri(URI.create(rs.getString(1)));
                obj.setId(rs.getString(1));
                obj.setTitle(rs.getString(2));
                obj.setSubject(rs.getString(3));
                obj.setType(rs.getString(4));
                obj.setDescription(rs.getString(5));

                Propriedade propriedade = new Propriedade("", rs.getString(2));
                Valor valor = new Valor(rs.getString(3), Valor.Tipo.LITERAL);

                String v = valor.getValor();
                if (v == null) {
                    v = "";
                };
                if (v.length() > 0) {
                    if ((v.substring(0, 1) == "\"")) {
                    } else {
                        valor.setTipo(Valor.Tipo.URI);
                    }
                }
                PropVal propval = new PropVal(propriedade, valor);
                obj.addPropVal(propval);

                list.add(obj);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PIDODao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public String toJson() {
        return "s";
    }

    public List<TriplaSimples> getListPropValueObjetoDigital(String id) throws Exception {

        String sql = getSparqlPropValObj(id);

        String ds = getDSPadrao();

        List<TriplaSimples> list = new ArrayList();

        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
        ResultSet rs = con.getResultset(sql);
        try {
            while (rs.next()) {
                TriplaSimples obj = new TriplaSimples(id, rs.getString(1), rs.getString(2));

                list.add(obj);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PIDODao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<PIDO> getListPIDO(String sql, String ds) throws Exception {
        List<PIDO> list = new ArrayList();

        ConexaoTDBRemota con = new ConexaoTDBRemota(ds);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
        ResultSet rs = con.getResultset(sql);
        try {
            while (rs.next()) {
                PIDO obj = new PIDO();

                obj.setId(rs.getString(0));

                obj.addProp(rs.getString(1), rs.getString(2));

                list.add(obj);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PIDODao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void pesquisar(String kw, String ds) {
        try {
            this.listaPIDO = getListPIDO(kw, ds);
        } catch (Exception ex) {
            Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
