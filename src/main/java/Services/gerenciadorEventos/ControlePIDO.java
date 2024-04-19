/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.ObjetoDigital;
import Entidades.PIC;
import Entidades.PIDO;
import com.google.gson.Gson;
import Services.DAO.PICDao;
import Services.DAO.ObjetoDigitalDao;
import Services.DAO.PIDODao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import Services.persistence.TDBUtil;

/**
 *
 * @author Administrador
 */
@Named
@ConversationScoped

public class ControlePIDO implements Serializable {

    private String ds;
    PIDO objeto;
    PIDODao objDao;
    private List<PIC> listaPic;
    private List<PIC> listaPicFiltrada;
    private List<PIDO> Objetos;
    private List<String> mediaTypes;
    private String[] selectedtipoFisico;

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

    private void inicializa() {
        objeto = new PIDO();
        objDao = new PIDODao();
        Objetos = new ArrayList<>();
        mediaTypes = new ArrayList<>();
        mediaTypes.add("Arquelógico");
        mediaTypes.add("Documental");
        mediaTypes.add("Imagem&Som");
        mediaTypes.add("Bibliotecário");
        mediaTypes.add("Museulógico");

    }

    public String[] getSelectedtipoFisico() {
        return selectedtipoFisico;
    }

    public void setSelectedtipoFisico(String[] selectedtipoFisico) {
        this.selectedtipoFisico = selectedtipoFisico;
    }

    public List<String> gettipoFisicos() {
        return mediaTypes;
    }

    public void settipoFisicos(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public void SelecionarClasse(SelectEvent event) {
        PIC p = (PIC) event.getObject();
        objeto.setPic(p);
        objeto.setType(p.getClassName());
//        this.pic = p;
//        System.out.println(p);
    }

    public void ObjetoSelecionado(SelectEvent event) {
        PIDO p = (PIDO) event.getObject();
        this.objeto = p;
    }

    public PIDO getObjeto() {
        return this.objeto;
    }

    public void setObjeto(PIDO obj) {

        this.objeto = obj;
    }

    public ControlePIDO() {
        inicializa();
    }

    @PostConstruct
    public void init() {
        inicializa();
    }

    public void gravarLocal() {
        objDao.CriarNovoObjLoc(ds);

    }

    public void gravarRemoto() {

        if (selectedtipoFisico != null) {

            objeto.settipoFisico(new ArrayList<String>(Arrays.asList(selectedtipoFisico)));
        }else{
            objeto.settipoFisico(new ArrayList<String>());
        }

        objDao.setObjeto(objeto);
        String ret = objDao.CriarNovoObjRem(TDBUtil.getTDBObjeto());

//        String ret = new Gson().toJson(objeto);
        FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", ret));
//        return ret;
    }

    public void setDS(String ds) {
        this.ds = ds;
    }

    public List<PIDO> getObjetos() {
        return this.Objetos;
    }

    public void pesquisarClasses() {
        try {
            PICDao dao = new PICDao();
            this.listaPic = dao.getListClasses(objeto.getType());
            this.listaPicFiltrada = this.listaPic;
        } catch (Exception e) {
        }

    }

    public void abrirDialogoClasses() {

        pesquisarClasses();
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", true);

        opcoes.put("Height", "80%");
        opcoes.put("Width", "70%");

        PrimeFaces.current().dialog().openDynamic("/classes/SelecionarClasse", opcoes, null);
//        pic.setSubClassOf(pic.getClassName());
    }
}
