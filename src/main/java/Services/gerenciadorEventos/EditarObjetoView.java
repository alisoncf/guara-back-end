/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.gerenciadorEventos;

import Entidades.ObjetoDigital;
import Entidades.PIDO;
import Entidades.TriplaSimples;

import Services.DAO.ObjetoDigitalDao;
import Services.DAO.PIDODao;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.net.URI;
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
import org.apache.lucene.util.automaton.DaciukMihovAutomatonBuilder;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import Services.persistence.TDBUtil;

/**
 *
 * @author Administrador
 */
@Named
@ConversationScoped
public class EditarObjetoView implements Serializable {

    private static final long serialVersionUID = -1L;

    @Inject
    private ObjetoDigital obj;
    @Inject
    private ObjetoDigital objSelecionado;
    private List<ObjetoDigital> listaObj;
    private List<TriplaSimples> relacoes;
    private TriplaSimples relacao;
    private List<ObjetoDigital> listaObjFiltrada;
    private String kw;
    private ObjetoDigitalDao objDao;
    private PIDODao pidoDao;
    @Inject
    private Conversation conversation;
    private String message;
    private PIDO pido;

    public Conversation getConversation() {
        return conversation;
    }

    public List<TriplaSimples> getRelacoes() {
        return relacoes;
    }

    public void setRelacoes(List<TriplaSimples> relacoes) {
        this.relacoes = relacoes;
    }

    public TriplaSimples getRelacao() {
        return relacao;
    }

    public void setRelacao(TriplaSimples relacao) {
        this.relacao = relacao;
    }

    private void TransformPido(ObjetoDigital p) {
        this.pido = new PIDO();
        this.pido.setId(p.getId());
        this.pido.setUri(URI.create(p.getUri()));
        this.pido.setTitle(p.getTitle());
        this.pido.setType(p.getType());
        this.pido.setSubject(p.getSubject());
        this.pido.setDescription(p.getDescription());

    }
public void addRelacao(){
    relacao.setSujeito(objSelecionado.getURI().toString());
    String s = objDao.addRelacao(TDBUtil.getTDBObjeto(),relacao);
     FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", s));

}
    public void initConversation() {
        if (!FacesContext.getCurrentInstance().isPostback()
                && conversation.isTransient()) {

            conversation.begin();
        }
    }

    public void editar(ObjetoDigital p) {
        this.objSelecionado = p;
//        abrirDialogo();
    }

    public void selecionar(ObjetoDigital p) {
        this.objSelecionado = p;
//        abrirDialogo();
    }
    public void pesquisar() {
        try {
            this.listaObj = objDao.getListObjDigital(obj.getTitle());

        } catch (Exception e) {
        }

    }
    public void pesquisarRelacoes() {
        try {
            this.relacoes = objDao.getListPropValueObjetoDigital(
                    objSelecionado.getUri());

        } catch (Exception e) {
        }
        System.out.println("pesquisando relações");
    }

    public String getKw() {
        return kw;
    }

    public void ObjetoSelecionado(SelectEvent event) {
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
        pidoDao = new PIDODao();
        this.relacao = new TriplaSimples();
//        }

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

        Map<String, List<String>> params = new HashMap<String, List<String>>();

        Map<String, Object> opcoes = new HashMap<>();
        List<String> list = new ArrayList<String>();

        params.put("id", (List<String>) this.objSelecionado);
        opcoes.put("modal", true);
        opcoes.put("resizable", true);

        opcoes.put("contentHeight", "500");
        opcoes.put("contentWidth", "1000");
        PrimeFaces.current().dialog().openDynamic("SelecionarClasse", opcoes, params);
//        pic.setSubClassOf(pic.getClassName());
    }

    public void setPic(ObjetoDigital obj) {
        this.obj = obj;
    }

    public List<ObjetoDigital> getListaObj() {
        return listaObj;
    }

    public List<ObjetoDigital> getListaObjFiltrada() {
        return listaObjFiltrada;
    }

    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        ObjetoDigital p = (ObjetoDigital) value;
        return p.getId().toLowerCase().contains(filterText)
                || p.getTitle().toLowerCase().contains(filterText)
                || p.getSubject().toLowerCase().contains(filterText);

    }

    public void selecionarObj(ObjetoDigital p) {

        this.objSelecionado = p;
        PrimeFaces.current().dialog().closeDynamic(p);

    }

    public String gravar() {
        TransformPido(objSelecionado);
        if (objDao.gravar(TDBUtil.getTDBObjeto())) {
            return "sucesso";
        } else {
            return "falha";
        }
    }

    public String alterar() {
        TransformPido(objSelecionado);
        pidoDao = new PIDODao(pido);
        if (pidoDao.alterar(TDBUtil.getTDBObjeto())) {
            return "sucesso";
        } else {
            return "falha";
        }

    }

    public void destruir(ObjetoDigital p) {
        objSelecionado = p;
        TransformPido(objSelecionado);
        pidoDao = new PIDODao();
        pidoDao.setObjeto(pido);
        System.out.println("Preparando para destruir objeto:" + pido.getId());
        if (pidoDao.destruir(TDBUtil.getTDBObjeto())) {
            FacesContext.getCurrentInstance().addMessage("messages", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Objeto Destruído"));
        } else {
//            return "falha";
        }
        
    }
    public void destruirRelacao(TriplaSimples tripla) {
        
        pidoDao = new PIDODao();
        pidoDao.setObjeto(pido);
        System.out.println("Preparando para destruir objeto:" + pido.getId());
        if (pidoDao.destruirRelacao(TDBUtil.getTDBObjeto(),tripla)) {
            FacesContext.getCurrentInstance().addMessage("messages", 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Objeto Destruído"));
        } else {
//            return "falha";
        }
        
    }

    public void confirmarExclusao() {
        addMessage("Exlcusão", "You have accepted");
    }

    public void delete() {
        addMessage("Confirmed", "Record deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
