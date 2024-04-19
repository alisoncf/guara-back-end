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
public class Repositorio implements Serializable {

    private String uri;
    private String nome;
    private String responsavel;
    private String contato;
    private String descricao;
    private static final long serialVersionUID = 1L;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Repositorio(String uri, String nome, String responsavel, String contato, String descricao) {
        this.uri = uri;
        this.nome = nome;
        this.responsavel = responsavel;
        this.contato = contato;
        this.descricao = descricao;
    }

    public Repositorio() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

}
