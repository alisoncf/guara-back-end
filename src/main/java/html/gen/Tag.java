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
public abstract class Tag {

    private String tag;
    protected List<String> atributo= new ArrayList();
    protected String valor="";

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tag(String tag, List<String> atributo, String valor) {
        this.tag = tag;
        this.atributo = atributo;
        this.valor = valor;
    }
    public Tag(String tag, String atributo, String valor) {
        this.tag = tag;
        this.atributo.add( atributo);
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<String> getAtributo() {
        return atributo;
    }

    public void setAtributo(List<String> atributo) {
        this.atributo = atributo;
    }

    public Tag(String tag, String valor) {
        this.tag = tag;
        this.valor = valor;
        
    }

    public Tag(String tag, List<String> atributo) {
        this.tag = tag;
        this.atributo = atributo;
    }

    public Tag(String tag) {
        this.tag = tag;
        this.atributo = new ArrayList();
        this.valor="";
    }

    public String AbreTag() {
        String s = "<" + this.tag + " ";
        if (atributo != null) {
            for (int i = 0; i < atributo.size(); i++) {
                s += atributo.get(i) + " ";

            }
        }
        s += ">";
        if (this.valor != null) {
            s += this.valor;
        }
        return s;

    }

    

    
}
