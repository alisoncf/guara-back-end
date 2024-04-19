/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.ObjetoDigital;
import Entidades.PIDO;
import Services.DAO.ObjetoDigitalDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import Services.persistence.TDBUtil;

/**
 *
 * @author Administrador
 */
@Named
@RequestScoped
public class ControleObjeto implements Serializable {

    private String ds;
    ObjetoDigital objeto;
    ObjetoDigitalDao objDao;
    
    private List<ObjetoDigital> Objetos;
    private List<PIDO> pidos;
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
        objeto = new ObjetoDigital();
        objDao = new ObjetoDigitalDao();
        Objetos = new ArrayList<>();
        pidos = new ArrayList<>();
    }

    public ObjetoDigital getObjeto() {
        return this.objeto;
    }

    public void setObjeto(ObjetoDigital obj) {

        this.objeto = obj;
    }

    public ControleObjeto() {
        inicializa();
    }

    public ControleObjeto(String ds) {

        this.ds = ds;
        objeto = new ObjetoDigital();
        objDao = new ObjetoDigitalDao();
    }
    @PostConstruct
    public void init() {
        objeto = new ObjetoDigital();
        objDao = new ObjetoDigitalDao();
    }

    public void gravarLocal() {
        objDao.CriarNovoObjLoc(ds);

    }

    public void gravarRemoto() {
        objDao.setObjeto(objeto);
        objDao.CriarNovoObjRem(TDBUtil.getTDBObjeto());

    }

    public void setDS(String ds) {
        this.ds = ds;
    }

    public List<ObjetoDigital> getObjetos() {
        return this.Objetos;
    }

    
}
