/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.DAO;

import Entidades.Repositorio;
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
public class RepositorioDao implements Serializable {

    private static final long serialVersionUID = -1L;
    private String tdb_classes;

    private String SPARQL_SelectClass;

    private Repositorio repo;

    private List<Repositorio> listaRepo;
    private String prefix;

    public String getSPARQL_SelectClass(String keyword) {

        prefix = "prefix :      <http://200.137.241.247:8080/fuseki/repositoriosamigos#> \n"
                + "prefix rpa:   <http://200.137.241.247:8080/fuseki/repositorios#> \n"
                + "prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "prefix owl:   <http://www.w3.org/2002/07/owl#> \n"
                + "prefix xsd:   <http://www.w3.org/2001/XMLSchema#> \n"
                + "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> \n";
        SPARQL_SelectClass = prefix
                + "SELECT DISTINCT ?uri ?nome ?responsavel ?contato ?descricao WHERE{?class a rpa:Repositorio. \n"
                + "  OPTIONAL { ?class rpa:uri ?uri} \n"
                + "  OPTIONAL { ?class rpa:nome ?nome} \n"
                + "  OPTIONAL { ?class rpa:descricao ?descricao} \n "
                + "  OPTIONAL { ?class rpa:contato ?contato} \n"
                + "  OPTIONAL { ?class rpa:responsavel ?responsavel} \n";

        if (keyword != "" && keyword != null) {
            SPARQL_SelectClass += "filter ((!bound(?uri)||(regex(?uri,\":keyword\",\"i\")))||(!bound(?nome)||(regex(?nome,\":keyword\",\"i\") ))) \n";
        }
        SPARQL_SelectClass += "}";

        SPARQL_SelectClass.replaceAll(":keyword", keyword);

        return SPARQL_SelectClass;

    }

    private void init() {
        listaRepo = new ArrayList<>();
        this.tdb_classes = TDBUtil.getTdb_repo();
    }

    public RepositorioDao(Repositorio repo) {

        this.repo = repo;
        init();
    }

    public RepositorioDao() {

        this.repo = new Repositorio();
        init();
    }

    public boolean GravarRepo(Repositorio repo) {
        try {
            String sparql = prefix;
            sparql += ":       a                rpa:Repositorio ;\n"
                    + "        rpa:contato      \"" + repo.getContato() + "\"\n"
                    + "        rpa:nome         \"" + repo.getNome() + "\"\n"
                    + "        rpa:responsavel  \"" + repo.getResponsavel() + "\"\n"
                    + "        rpa:uri          <" + repo.getUri() + "> .";
            sparql += "}";
            ConexaoTDBRemota con = new ConexaoTDBRemota(tdb_classes);
            con.executa(sparql);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List<Repositorio> ListRepo(String keyword) {
        try {
            List<Repositorio> list = new ArrayList();

            ConexaoTDBRemota con = new ConexaoTDBRemota(this.tdb_classes);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
            ResultSet rs = con.getResultset(getSPARQL_SelectClass(keyword));
            try {
                while (rs.next()) {
                    Repositorio p = new Repositorio();
                    p.setContato(rs.getString("contato"));
                    p.setNome(rs.getString("nome"));
                    p.setResponsavel(rs.getString("responsavel"));
                    p.setUri(rs.getString("uri"));
                    p.setDescricao(rs.getString("descricao"));

                    list.add(p);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
            }

            return list;

        } catch (Exception ex) {
            Logger.getLogger(RepositorioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    public Repositorio getRepositorioAdm() {
        try {
            
            ConexaoTDBRemota con = new ConexaoTDBRemota(this.tdb_classes);
//        sql = "SELECT * WHERE {?s ?p ?o} limit 10";
            ResultSet rs = con.getResultset(getSPARQL_SelectClass(""));
            try {
                while (rs.next()) {
                    Repositorio p = new Repositorio();
                    p.setContato(rs.getString("contato"));
                    p.setNome(rs.getString("nome"));
                    p.setResponsavel(rs.getString("responsavel"));
                    p.setUri(rs.getString("uri"));
                    p.setDescricao(rs.getString("descricao"));

                    return p;    
                }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(PICDao.class.getName()).log(Level.SEVERE, null, ex);
            }

            

        } catch (Exception ex) {
            Logger.getLogger(RepositorioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

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
            Logger.getLogger(RepositorioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xml;
    }

}
