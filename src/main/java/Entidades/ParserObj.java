/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author cgpre
 */
public class ParserObj implements Serializable {

    ObjetoDigital objeto;
    private String _AD = "\"";
    private String _SP = "    ";
  private static final long serialVersionUID = -1L;
    public ParserObj(ObjetoDigital o) {
        this.objeto = o;

    }

    public String getRDFXML() {
        String s = "";
        String head = "<rdf:RDF \n";
        String pref = "";
        String desc = "<rdf:Description rdf:about=" + _AD + this.objeto.getURI() + _AD + ">\n";
        for (int i = 0; i < this.objeto.prefixo.size(); i++) {
            pref = pref + _SP + "xmlns:" + objeto.prefixo.get(i).getPrefixo() + "="
                    + _AD + objeto.prefixo.get(i).getUri() + _AD + "\n";
        }
        head = head + pref + ">\n";
        s = head;

        for (Iterator iterator = this.objeto.propval.iterator(); iterator.hasNext();) {
            PropVal next = (PropVal) iterator.next();
            desc = desc + "<" + next.getPropriedade() + "\n";
//            if(next.get)
        }

        return s;
    }

    public String getN3() {
        String s = "";
        String head = "";
        String pref = "";
        String desc = "";
        for (int i = 0; i < this.objeto.prefixo.size(); i++) {
            pref = pref + _SP + "@prefix " + objeto.prefixo.get(i).getPrefixo() + ": "
                    +  "<"+ objeto.prefixo.get(i).getUri() + ">" + ".\n";
        }
        head = head + pref + "\n";
        s = head;

        for (Iterator iterator = this.objeto.propval.iterator(); iterator.hasNext();) {
            PropVal next = (PropVal) iterator.next();
            
            desc = desc + "<" + next.getPropriedade() + "\n";
//            if(next.get)
        }

        return s;
    }

    
}
