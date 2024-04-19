/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.PIC;

import Services.DAO.PICDao;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

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
@ConversationScoped
public class ControlePIC implements Serializable {

    @Inject
    private PIC pic;
    @Inject
    private PIC picSelecionada;
    private List<PIC> listaPic;
    private List<PIC> listaPicFiltrada;
    private String kw;
    private PICDao classeDao;
    private static final long serialVersionUID = -1L;
    @Inject
    private Conversation conversation;
    private String message;

    public Conversation getConversation() {
        return conversation;
    }

    public void initConversation() {
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {

            conversation.begin();
        }
    }

    public String next() {
        return "secondpage?faces-redirect=true";
    }

    public String endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return "firstpage?faces-redirect=true";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void pesquisar() {
        try {
            if (pic.getSuperclass() == null) {
                pic.setSuperclass(new PIC());
            }
            this.listaPic = classeDao.getListClasses(pic.getSuperclass().getClassName());
            this.listaPicFiltrada = this.listaPic;
        } catch (Exception e) {
        }

    }

    public String getKw() {
        return kw;
    }

    public void ClasseSelecionada(SelectEvent event) {
        PIC p = (PIC) event.getObject();
//        this.pic = p;
        this.picSelecionada = p;
        this.pic.setSuperclass(p);

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
        classeDao = new PICDao();
//        }

    }

    public void IniciarGravar() {
        this.pic = new PIC();

//        return "iniciar";
    }

    public PIC getPic() {
        return pic;
    }

    public PIC getPicSelecionada() {
        return picSelecionada;
    }

    public void setPicSelecionada(PIC p) {
        picSelecionada = p;
    }

    public void Testar() {
        System.out.println("teste");

    }

    public void abrirDialogo() {

        pesquisar();
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);

        opcoes.put("contentHeight", "60%");
        opcoes.put("contentWidth", "80%");
        PrimeFaces.current().dialog().openDynamic("/classes/SelecionarClasse", opcoes, null);
//        pic.setSubClassOf(pic.getClassName());
    }

    public void setPic(PIC pic) {
        this.pic = pic;
    }

    public List<PIC> getListaPic() {
        return listaPic;
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

        PIC p = (PIC) value;
        return p.getClassName().toLowerCase().contains(filterText)
                || p.getLabel().toLowerCase().contains(filterText)
                || p.getComment().toLowerCase().contains(filterText);

    }

    public void selecionarClasse(PIC pic) {

        this.picSelecionada = pic;
        PrimeFaces.current().dialog().closeDynamic(pic);

    }

    public String gravar() {
        String ret;
//        System.out.println(picSelecionada.getClassName());
        if (pic.getSubClassOf().equals("")){
            pic.setSubClassOf(pic.getSuperclass().getClassName());
        }
        if (classeDao.GravarPic(pic)) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info: Sucesso!", "Sucesso na operação!"));
            ret = "";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info: Falha!", "Erro na operação!"));
            ret = "";
        }
       
        return ret;
    }

    public String gravarNovo(String className, String label, String subClassOf, String comment) {
        PIC pic = new PIC(className, label, comment, subClassOf);
        if (classeDao.GravarPic(pic)) {
            return "sucesso";
        } else {
            return "falha";
        }

    }

}
