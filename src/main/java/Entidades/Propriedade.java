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
public class Propriedade implements Serializable {

    private String prefixo;
    private String nome;
    private static final long serialVersionUID = -1L;

    public Propriedade() {
    }

    public Propriedade(String prefixo, String nome) {
        this.prefixo = prefixo;
        this.nome = nome;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getN3() {
        if (prefixo == "") {
            return "<" + nome + ">";
        } else {
            return prefixo + ":" + nome;
        }

    }

}
