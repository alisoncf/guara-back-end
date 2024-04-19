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
import java.util.Map;
import java.util.UUID;
import Services.persistence.TDBUtil;
import util.prefixos;

/**
 *
 * @author Administrador
 */
public class PIDO implements Serializable {

    private String id;
    private String title;
    private String type;
    private String subject;
    private String description;
    private List<String> mediaType;
    private String tipoDimensional;
    private static final long serialVersionUID = -1L;

    public List<String> gettipoFisico() {
        return mediaType;
    }

    public String getTipoDimensional() {
        return tipoDimensional;
    }

    public void setTipoDimensional(String tipoDimensional) {
        this.tipoDimensional = tipoDimensional;
    }

    public void settipoFisico(List<String> mediaType) {
        this.mediaType = mediaType;
    }

    public String getDimType() {
        return tipoDimensional;
    }

    public void setDimType(String tipoDimensional) {
        this.tipoDimensional = tipoDimensional;
    }

    private List<PPV> prop;
    private PIC pic;
    private URI uri;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public PIC getPic() {
        return pic;
    }

    public void setPic(PIC pic) {
        this.pic = pic;
    }

    public PIDO() {
        this.tipoDimensional = "";
        prop = new ArrayList<>();
        GeraId();
    }

    public PIDO(String title, String type, String subject, String description) {
        this.title = title;
        this.type = type;
        this.subject = subject;
        this.description = description;
        prop = new ArrayList<>();
        this.tipoDimensional = "";
        GeraId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PIDO(String title, String type, String subject, ArrayList<PPV> prop, String description) {
        this.title = title;
        this.type = type;
        this.subject = subject;
        this.prop = prop;
        this.description = description;
        this.tipoDimensional = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<PPV> getProp() {
        return prop;
    }

    public void setProp(List<PPV> prop) {
        this.prop = prop;
    }

    public void addProp(PPV p) {
        this.prop.add(p);
    }

    public void addProp(String p, String v) {
        this.prop.add(new PPV(p, v));
    }

    public PPV getProp(int i) {
        return this.prop.get(i);
    }

    private void GeraId() {
        this.id = UUID.randomUUID().toString();
        this.uri = URI.create(TDBUtil.getTdbBase() + id);
    }

    public String getN3() {

        String ret = "";
        if (this.tipoDimensional == null) {
            this.tipoDimensional = "";
        }

        ret = "<" + this.uri + "> " + "dc:title " + util.RDFUtil.getValue(this.title) + ";\n";
        if (tipoDimensional.equals("")) {
            ret += " a dim:ObjetoFisico; ";
            for (int i = 0; i < mediaType.size(); i++) {
                ret += "dim:tipoFisico " + "dim:" + mediaType.get(i) + ";\n";
                
            }
            ret += "rdfs:type " + util.RDFUtil.getValue(this.type) + ";\n";
        } else {
            ret += " a dim:ObjetoDimensional; ";
            ret += " dim:tipoDimensional dim:" + this.getDimType() + ";" ;
            
        }

        ret += "dc:subject " + util.RDFUtil.getValue(this.subject) + ";\n";
        ret += "dc:description " + util.RDFUtil.getValue(this.description) + ".\n";
        
        return ret;
    }

}
