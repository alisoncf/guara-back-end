/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author cgpre
 */
public class Valor implements Serializable {

    private String valor;
    private Tipo tipo;
    private String XSDDataType = "";
    private String lang = "";
    private static final long serialVersionUID = -1L;

    public String getXSDDataType() {
        return XSDDataType;
    }

    public void setXSDDataType(String XSDDataType) {
        this.XSDDataType = XSDDataType;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Valor(String valor, Tipo tipo, String XSDDataType, String lang) {
        this.valor = valor;
        this.tipo = tipo;
        this.XSDDataType = XSDDataType;
        this.lang = lang;
    }
    private final String _AD = "\"";
    private final String _SP = "    ";

    public enum Tipo {
        URI, IRI, LITERAL
    };

    public Valor(String valor, Tipo tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public Valor() {

    }

    public String getValor() {
        if (this.valor == null) {
            this.valor = "";
        }
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public String getN3() {
        if (this.tipo == Tipo.LITERAL) {
            return _AD + this.valor + _AD + this.lang + " " + this.XSDDataType;
        } else {
            return "<" + this.valor + ">";
        }

    }

}
