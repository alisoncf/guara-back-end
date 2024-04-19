/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

import java.util.List;

/**
 *
 * @author cgpre
 */
public class FullTag extends Tag {

    public FullTag(String tag, List<String> atributo, String valor) {
        super(tag, atributo, valor);
    }

    public FullTag(String tag, String atributo, String valor) {
        super(tag, atributo, valor);
    }

    public FullTag(String tag, String valor) {
        super(tag, valor);
    }

    public FullTag(String tag, List<String> atributo) {
        super(tag, atributo);
    }

    public FullTag(String tag) {
        super(tag);
    }
    public String getTagInicio(){
        return super.AbreTag();
    }
    public String getTagCompleta(){
        return super.AbreTag()+fechaTag();
    }
    public String fechaTag() {
        return "</" + this.getTag() + ">";
    }

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
}
