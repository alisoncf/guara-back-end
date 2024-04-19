/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author cgpre
 */
public class ObjetoDigital implements Serializable {

    String id;
    String base = "http://200.137.241.247:8080/fuseki/";
    private String title;
    private String subject;
    private String type;
    private String description;
    private String dimensao;

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }
    List<Prefixo> prefixo = new ArrayList();
    List<PropVal> propval = new ArrayList();
    private URI uri;
    private PIDO pido;
    private PIC pic;
    private static final long serialVersionUID = -1L;

    public PIDO getPido() {
        return pido;
    }

    public void setPido(PIDO pido) {
        this.pido = pido;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private void GeraId() {
        this.id = UUID.randomUUID().toString();
        this.uri = URI.create(base + id);
    }

    public ObjetoDigital() {
        GeraId();
    }

    public void addPrefixo(Prefixo prefixo) {
        this.prefixo.add(prefixo);
    }

    public void addPropVal(PropVal p) {
        this.propval.add(p);
    }

    public void removePropVal(PropVal p) {
        propval.remove(p);
    }

    public ObjetoDigital(String base) {
        GeraId();
        this.uri = URI.create(base + id);
    }

    public String getId() {
        return this.id;
    }

    public String getUri() {
        return this.id;
    }

    public String getN3() {
        String ttl = "";
        String pref = "";
        String prop = "";
        for (Iterator<Prefixo> iterator = prefixo.iterator(); iterator.hasNext();) {
            pref = pref + "@prefix " + iterator.next().getUri().toString() + "\n";

        }

        if ("".equals(ttl)) {
            ttl = "";
        } else {
            ttl = " " + pref;
        }

        for (Iterator<PropVal> it = propval.iterator(); it.hasNext();) {
            PropVal p = it.next();
            prop = prop + p.getN3();
            if (it.hasNext()) {
                prop = prop + ";";
            }
        }

        return ttl + "<" + this.uri.toString() + "> " + prop + ".";
    }

    public String getSPARQLInsertN3() {
//        String s = new Prefixo().getListaPrefixo(prefixo,"SPARQL");
        String s = util.prefixos.ListaPrefixos();
        s = s + "INSERT DATA{";
        s = s + getN3();
        s = s + "}";
        return s;
    }

    
    
    
    public URI getURI() {
        return this.uri;
    }

    public ParserObj getParse() {
        return new ParserObj(this);
    }

    public List<Prefixo> getPrefixo() {
        return prefixo;
    }

    public List<PropVal> getPropval() {
        return propval;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setPrefixo(List<Prefixo> prefixo) {
        this.prefixo = prefixo;
    }

    public void setPropval(List<PropVal> propval) {
        this.propval = propval;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * @return the pic
     */
    public PIC getPic() {
        return pic;
    }

    /**
     * @param pic the pic to set
     */
    public void setPic(PIC pic) {
        this.pic = pic;
    }

}
