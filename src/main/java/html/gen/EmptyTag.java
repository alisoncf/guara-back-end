/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cgpre
 */
public class EmptyTag extends Tag {

    public List<String> getAtributo() {
        return atributo;
    }

    public void setAtributo(List<String> atributo) {
        this.atributo = atributo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public EmptyTag(String tag, List<String> atributo, String valor) {
        super(tag, atributo, valor);
    }

    public EmptyTag(String tag, String atributo, String valor) {
        super(tag, atributo, valor);
    }

    public EmptyTag(String tag, String valor) {
        super(tag, valor);
    }

    public EmptyTag(String tag, List<String> atributo) {
        super(tag, atributo);
    }

    public EmptyTag(String tag) {
        super(tag);
    }
    
    public String getTagCompleta(){
        return super.AbreTag();
    }
    
}
