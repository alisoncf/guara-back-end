/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.PIC;

import Services.DAO.PICDao;
import static java.lang.Integer.getInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.jena.ext.com.google.common.collect.HashBiMap;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Administrador
 */
@Named
@RequestScoped
public class SelecionarClasse {

    @Inject
    private PIC pic;
    private PIC picSelecionada;
    private List<PIC> listaPic;
    private List<PIC> listaPicFiltrada;
    private String kw;
    private PICDao classeDao;

    public void pesquisar() {
        try {
            this.listaPic = classeDao.getListClasses(kw);
            this.listaPicFiltrada = this.listaPic;
        } catch (Exception e) {
        }

    }

    public String getKw() {
        return kw;
    }

    public void ClasseSelecionada(SelectEvent event) {
        PIC p = (PIC) event.getObject();
        this.pic = p;
        this.picSelecionada = p;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    @PostConstruct
    private void inicializa() {
        this.listaPic = new ArrayList<>();
        this.listaPicFiltrada = new ArrayList<>();
        pic = new PIC();
        picSelecionada = new PIC();
        classeDao = new PICDao();

    }

    public SelecionarClasse() {
        inicializa();
    }

    public PIC getPic() {
        return pic;
    }

    public PIC getPicSelecionada() {
        return this.picSelecionada;
    }

    public void setPic(PIC pic) {
        this.pic = pic;
    }

    public List<PIC> getListaPic() {
        return listaPic;
    }

    public void setListaPic(List<PIC> listaPic) {
        this.listaPic = listaPic;
    }

    public List<PIC> getListaPicFiltrada() {
        return listaPicFiltrada;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        PIC pic = (PIC) value;
        return pic.getClassName().toLowerCase().contains(filterText)
                || pic.getLabel().toLowerCase().contains(filterText)
                || pic.getComment().toLowerCase().contains(filterText);

    }

    public void setListaPicFiltrada(List<PIC> listaPicFiltrada) {
        this.listaPicFiltrada = listaPicFiltrada;
    }

    public void selecionarClasse(PIC pic) {

        this.picSelecionada = pic;
        PrimeFaces.current().dialog().closeDynamic(pic);

    }

    public String GravarNovo(PIC pic) {
        if (classeDao.GravarPic(pic)) {
            return "sucesso";
        } else {
            return "falha";
        }

    }

}
