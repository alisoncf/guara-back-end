/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entidades.TriplaSimples;
import com.google.gson.Gson;
import Services.DAO.ObjetoDigitalDao;
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
import org.apache.jena.util.FileUtils;

/**
 *
 * @author cgpre
 */
@WebServlet("/getRelation")
public class getRelation extends HttpServlet {

    private String id;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String getHTML() {
        String html;
        html = "";

        List<TriplaSimples> l = new ArrayList<TriplaSimples>();

        try {
            l = new ObjetoDigitalDao().getListPropValueObjetoDigital(id);
            html += "<div class=\"table-responsive\">"
                    + "<table class=\"table\">"
                    + "<thead>"
                    + "<tr>"
                    + "<td>Propriedade<td>"
                    + "<td>Valor<td>"
                    + "</tr>"
                    + "</thead>";
            for (int i = 0; i < l.size(); i++) {
                TriplaSimples t = l.get(i);

                html += "<tr>"
                        + "<td><a href=" + t.getPropriedade() + ">" + t.getPropriedade() + "</a><td>";

                if (FileUtils.isURI(t.getObjeto())) {
                    html += "<td><a href=" + t.getObjeto() + ">" + t.getObjeto() + "</a><td>";

                } else {
                    html += "<td>" + t.getObjeto() + "<td>";
                }
                html += "</tr>";

            }
            html += "</table>"
                    + "</div>";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(getRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
        html += "Gerado por Guará - Objetos Digitais Semântico (API)";
        html += "</body>"
                + "</html>";

        return html;
    }

    public String getJson() {
        String s = "";
        
 
            
 
        List<TriplaSimples> l = new ArrayList<TriplaSimples>();
        try {
            l = new ObjetoDigitalDao().getListPropValueObjetoDigital(this.id);
            String json = new Gson().toJson(l);
            s=json;
        } catch (Exception ex) {
            Logger.getLogger(getRelation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return s;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            this.id = request.getParameter("id");
            String type=request.getParameter("type");
            if ("json".equals(type)){
                response.setContentType("text/json;charset=UTF-8");
                out.println(getJson());
            }else{
                response.setContentType("text/html;charset=UTF-8");
                out.println(getHTML());
            }
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
