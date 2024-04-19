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
public class EditarClasseView implements Serializable {

    private static final long serialVersionUID = -1L;

    @Inject
    private PIC pic;
    @Inject
    private PIC picSelecionada;
    private PIC picOriginal;
    private List<PIC> listaPic;
    private List<PIC> listaPicFiltrada;
    private String kw;
    private PICDao classeDao;
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

    public void editar(PIC p) {
        this.picSelecionada = p;
        this.picOriginal = p;

//        abrirDialogo();
    }

    public void pesquisar() {
        try {
            this.listaPic = classeDao.getListClasses(pic.getClassName());
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

//        Map<String, List<String>> params = new HashMap<String, List<String>>();
//        
//        Map<String, Object> opcoes = new HashMap<>();
//        List<String> list = new ArrayList<String>();
//        
//        
//        params.put ("id", (List<String>) this.picSelecionada);
//        opcoes.put("modal", true);
//        opcoes.put("resizable", false);
//
//        opcoes.put("contentHeight", "600");
//        opcoes.put("contentWidth", "1000");
//        PrimeFaces.current().dialog().openDynamic("editarClasse", opcoes, params);
////        pic.setSubClassOf(pic.getClassName());
        pesquisar();
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", true);

//        opcoes.put("contentHeight", "60%");
//        opcoes.put("contentWidth", "80%");
        PrimeFaces.current().dialog().openDynamic("SelecionarClasse", opcoes, null);
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
        pic = picSelecionada;
        if (classeDao.GravarPic(pic)) {
            this.message = "Classe adicionada com sucesso!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            return "sucesso";
        } else {
            return "falha";
        }
    }

    public String alterar() {
        pic = picSelecionada;
        if (classeDao.AlterarPic(pic.getClassName(), pic.getLabel(),
                picSelecionada.getSubClassOf(), pic.getComment())) {
            this.message = "Classe adicionada com sucesso!";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
            
            return "";
        } else {
            return "";
        }
    }

    public String getMessage() {
        return this.message;
    }

    public String gravarNovo(PIC pic) {
        if (classeDao.GravarPic(pic)) {
            this.message = "Classe adicionada com sucesso!";
            return "sucesso";
            
        } else {
            return "falha";
        }

    }
    public void destruir(PIC p) {
        
        PICDao dao = new PICDao();
        dao.setPic(pic);
        System.out.println("Preparando para destruir classe:" + pic.getClassName());
        if (dao.destruir()) {
            FacesContext.getCurrentInstance().addMessage("messages", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Info: sucesso", "Objeto Destruído"));
        } else {
//            return "falha";
        }
        
    }

}
