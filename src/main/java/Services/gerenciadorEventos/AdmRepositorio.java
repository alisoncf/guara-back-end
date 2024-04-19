/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.Repositorio;
import Services.DAO.RepositorioDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cgpre
 */
@Named
@ViewScoped
public class AdmRepositorio implements Serializable {

    @Inject
    private Repositorio repositorio;
    private List<Repositorio> lista;

    @PostConstruct
    private void init() {
        lista = new ArrayList<Repositorio>();
    }

    public void listaRepo() {
        lista = new RepositorioDao().ListRepo("");
    }

    public void buscaRepo() {
        repositorio = new RepositorioDao().getRepositorioAdm();
    }

    public List<Repositorio> getListRepo() {
        lista = new RepositorioDao().ListRepo("");
        return lista;

    }

    public Repositorio getRepoAdm() {
        return this.repositorio;

    }
}
