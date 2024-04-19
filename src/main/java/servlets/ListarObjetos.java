/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Entidades.ObjetoDigital;
import Entidades.Raw3;
import Services.DAO.ObjetoDigitalDao;
import html.gen.HTMLUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Services.persistence.TDBUtil;
import com.google.gson.Gson;

/**
 *
 * @author cgpre
 */
@WebServlet("/ListarObjetos")
public class ListarObjetos extends HttpServlet {

    private String kw = "";
    private String tabela;
    private String colecao;
    private String type = "text/html";
    List<ObjetoDigital> list = new ArrayList();
    List<Raw3> listRaw = new ArrayList<>();

    private String RetornoHTML(ArrayList<ObjetoDigital> list) {
        String retorno = "";
        String html = "";
        tabela = "<div   style=\"overflow-x:auto; >\n"
                + "<table><tr><th>Objeto</th><th>Título</th><th>Tipo</th><th>Editar</th></tr>";
        for (int i = 0; i < list.size(); i++) {
            ObjetoDigital get = list.get(i);
            tabela = tabela + "<tr>"
                    + "<td>" + get.getUri() + "</td>"
                    + "<td>" + get.getPropval().get(0).getPropriedade().getNome() + "</td>"
                    + "<td>" + get.getPropval().get(0).getValor().get(0).getValor() + "</td>"
                    + "<td><button type=\"submit\" value=\"\">Editar</button></td>"
                    + "</tr>\n";
        }
        tabela = tabela + "</table></div>";

        String style = "<style>\n"
                + "table {\n"
                + "  border-collapse: collapse;\n"
                + "  width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "  text-align: left;\n"
                + "  padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even) {background-color: #f2f2f2;}\n"
                + "</style>";

        html = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<link rel=\"stylesheet\" href=\"/resources/css/tabela.css\">"
                + "<link rel=\"stylesheet\" href=\"/resources/css/estilo.css\">"
                + "<script src=\"https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js\"></script>"
                + "<title>Pesquisar por Tipo de Objeto</title>"
                + "</head>"
                + "<body ng-app=\"\">"
                + "<div class=\"captionfrmPesquisa\"><h3>Pesquisar</h3></div>"
                + tabela
                + "</body>"
                + "</html>";

        return retorno;
    }

    
    
    private void pesquisar() throws Exception {
        String sql = "prefix owl: <http://www.w3.org/2002/07/owl#>\n"
                + "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n"
                + "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
                + "SELECT DISTINCT ?s  ?v ?o "
                + " WHERE{?s ?v ?o.\n"
                + " filter (regex(?s,\"" + this.kw + "\",\"i\")||regex(?v,\""
                + this.kw + "\",\"i\") || regex(?o,\"" + this.kw + "\",\"i\"))"
                + "} limit 1000";
        
        listRaw = new ObjetoDigitalDao().getListaRaw3(sql, "");

    }

    private String ConstroiHTMLRetorno() {
        String s = "";
        HTMLUtil util = new HTMLUtil();
        s = util.HTMLPadrao();
        return s;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        
        this.kw = request.getParameter("kw");
        String t = request.getParameter("type");
        if (t != null) {
            this.type = t;
        }
        
        pesquisar();
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/json");
            String ret = new Gson().toJson(listRaw);
            out.print(ret);
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListarObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ListarObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
