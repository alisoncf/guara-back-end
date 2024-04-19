/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Iterator;
import util.RDFUtil;

/**
 *
 * @author cgpre
 */
public class TriplaSimples implements Serializable {

    private String sujeito;
    private String propriedade;
    private String objeto;
    private static final long serialVersionUID = -1L;

    public TriplaSimples() {
    }

    public TriplaSimples(String sujeito, String propriedade, String objeto) {
        this.sujeito = sujeito;
        this.propriedade = propriedade;
        this.objeto = objeto;
    }

    public String getSujeito() {
        return sujeito;
    }

    public void setSujeito(String sujeito) {
        this.sujeito = sujeito;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getJson() {
        String s;
        s = "{subject: " + "\"" + this.sujeito + "\" ";
        s += "{property: " + "\"" + this.propriedade + "\" ";
        s += "{object: " + "\"" + this.objeto + "\"} ";

        return s;

    }
    

    public String getSPARQLInsertN3() {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        s = s + "INSERT DATA{";
        s = s + "<" + this.sujeito + "> " + RDFUtil.getValue(this.propriedade) + " "   + RDFUtil.getValue(this.objeto);
        
        s = s + "}";
        System.out.println(s);
        return s;
        
    }
}
