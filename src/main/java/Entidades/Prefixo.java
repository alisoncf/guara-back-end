/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.net.URI;
import java.util.List;

/**
 *
 * @author cgpre
 */
public class Prefixo implements java.io.Serializable {

    String prefixo;
    URI uri;
    private static final long serialVersionUID = -1L;

    public Prefixo() {
    }

    public Prefixo(String prefixo, URI uri) {
        this.prefixo = prefixo;
        this.uri = uri;
    }

    public Prefixo(String prefixo, String uri) {
        this.prefixo = prefixo;
        this.uri = URI.create(uri);
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getN3() {
        return "@prefix " + this.prefixo + ":" + this.uri.toString() + ".\n";

    }

    public String getSPARQL() {
        return "PREFIX " + this.prefixo + ":<" + this.uri.toString() + ">\n";

    }

    public String getListaPrefixo(List<Prefixo> p, String tipo) {
        String s = "";

        for (Prefixo prefixo1 : p) {
            if ("N3".equals(tipo) || "n3".equals(tipo)) {
                s = s + prefixo1.getN3();
            } else {
                s = s + prefixo1.getSPARQL();
            }
        }

        return s;
    }

}
