/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.ObjetoDigital;
import Entidades.PIDO;

import Services.DAO.PICDao;
import Services.DAO.ObjetoDigitalDao;
import Services.DAO.PIDODao;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import org.apache.jena.ext.com.google.common.collect.HashBiMap;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import Services.persistence.TDBUtil;
import util.prefixos;

/**
 *
 * @author Administrador
 */
@Named
@ViewScoped
public class ListarObjetosView implements Serializable {

    private String[] selectedDimensoes;
    private List<String> dimensoes;
    private static final long serialVersionUID = -1L;

    @Inject
    private ObjetoDigital obj;
    @Inject
    private ObjetoDigital objSelecionado;
    private List<ObjetoDigital> listaObj;
    private List<ObjetoDigital> listaObjFiltrada;
    private String kw;
    private ObjetoDigitalDao objDao;

    public void pesquisar() {
        try {

            kw = obj.getTitle();

            String sql = prefixos.ListaPrefixos()
                    + "SELECT DISTINCT ?s  ?v ?o ?q "
                    + " WHERE{ "
                    + "?s a dim:ObjetoFisico."
                    + "?s dc:title ?v."
                    + "?s dc:subject ?o."
                    + "?s rdfs:type ?q."
                    + "filter (regex(?s,'" + kw + "','i')||regex(?v,'" + kw + "','i') )"
                    + "}";
            System.out.println(sql);
            this.listaObj = objDao.getListObjDigital(kw);
            this.listaObjFiltrada = this.listaObj;
        } catch (Exception e) {

        }

    }

    public void pesquisarDim() {
        try {

            kw = obj.getTitle();

            this.listaObj = objDao.getListObjDim(kw);
            this.listaObjFiltrada = this.listaObj;
        } catch (Exception e) {

        }

    }

    public String getKw() {
        return kw;
    }

    public void ClasseSelecionada(SelectEvent event) {
        ObjetoDigital p = (ObjetoDigital) event.getObject();
//        this.pic = p;
        this.objSelecionado = p;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    @PostConstruct
    private void init() {
//        if (this.listaPicFiltrada == null) {
//            this.listaPic = new ArrayList<>();
//            this.listaPicFiltrada = new ArrayList<>();
//        pic = new PIC();
//        picSelecionada = new PIC();
        objDao = new ObjetoDigitalDao();
//        }
        dimensoes = new ArrayList<>();
        dimensoes.add("Pessoa");
        dimensoes.add("Fenômeno");
        dimensoes.add("Espaço");
        dimensoes.add("Tempo");
        

    }

    public String[] getSelectedDimensoes() {
        return selectedDimensoes;
    }

    public void setSelectedDimensoes(String[] selectedDimensoes) {
        this.selectedDimensoes = selectedDimensoes;
    }

    public List<String> getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(List<String> dimensoes) {
        this.dimensoes = dimensoes;
    }

    public void IniciarGravar() {
        this.obj = new ObjetoDigital();

//        return "iniciar";
    }

    public ObjetoDigital getObj() {
        return obj;
    }

    public ObjetoDigital getObjSelecionado() {
        return objSelecionado;
    }

    public void setObjSelecionado(ObjetoDigital p) {
        objSelecionado = p;
    }

    public void Testar() {
        System.out.println("teste");

    }

    public void abrirDialogo() {

        pesquisar();
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);

        opcoes.put("contentHeight", "600");
        opcoes.put("contentWidth", "1000");
        PrimeFaces.current().dialog().openDynamic("SelClass", opcoes, null);
//        pic.setSubClassOf(pic.getClassName());
    }

    public List<ObjetoDigital> getListaObj() {
        return listaObj;
    }

    public List<ObjetoDigital> getListaPicFiltrada() {
        return listaObjFiltrada;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        ObjetoDigital p = (ObjetoDigital) value;
        return p.getType().toLowerCase().contains(filterText)
                || p.getTitle().toLowerCase().contains(filterText)
                || p.getSubject().toLowerCase().contains(filterText);

    }

    public void selecionarObj(ObjetoDigital obj) {

        this.objSelecionado = obj;
        PrimeFaces.current().dialog().closeDynamic(obj);

    }

    public String gravar() {

        System.out.println(objSelecionado.getType());
        if (objDao.gravar(TDBUtil.getTDBObjeto()) == true) {
            return "sucesso";
        } else {
            return "falha";
        }
    }

}
