/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cgpre
 */
public class PropVal implements java.io.Serializable {

    private String prefix;
    private Propriedade propriedade;
    private List<Valor> valor = new ArrayList();
    private static final long serialVersionUID = -1L;

    public void addValor(Valor v) {
        this.valor.add(v);
    }

    public void remValor(Valor v) {
        this.valor.remove(v);
    }

    public PropVal(String prefixo, Propriedade propriedade, Valor valor) {
        this.propriedade = propriedade;
        this.prefix = prefixo;
        this.valor.add(valor);

    }

    public PropVal(Propriedade propriedade, Valor valor) {
        this.propriedade = propriedade;
        this.valor.add(valor);
    }

    public PropVal(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public PropVal() {
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public List<Valor> getValor() {
        return valor;
    }

    public void setValor(List<Valor> valor) {
        this.valor = valor;
    }

    public String getN3() {
        String s = "";

        s = this.propriedade.getN3() + " ";
        for (int i = 0; i < valor.size(); i++) {
            Valor get = valor.get(i);
            s = s + get.getN3();
            if (i < valor.size() - 1) {
                s = s + ", ";
            }
        }

        return s;
    }

}
