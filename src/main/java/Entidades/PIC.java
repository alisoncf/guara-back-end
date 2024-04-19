/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

public class PIC implements Serializable, MOT {
    private static final long serialVersionUID = -1L;
    private String className = "";
    private String label = "";
    private String comment = "";
    private String subClassOf = "";
    private PIC superclass;
    public PIC(String className, String label, String comment, String subClassOf) {
        this.className = className;
        this.label = label;
        this.comment = comment;
        this.subClassOf = subClassOf;
        
    }
    public PIC(String className, String label, String comment, String subClassOf, PIC superclass) {
        this.className = className;
        this.label = label;
        this.comment = comment;
        this.subClassOf = subClassOf;
        this.superclass = superclass;
    }
    public PIC() {
        
    }

    public PIC getSuperclass() {
        return superclass;
    }

    public void setSuperclass(PIC superclass) {
        this.superclass = superclass;
    }

    

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubClassOf() {
        return subClassOf;
    }

    public void setSubClassOf(String subClassOf) {
        this.subClassOf = subClassOf;
    }

    public String getSPARQLInsertN3() {

       return getSPARQLInsert();
    }

    public String getSPARQLDeleteInsertN3() {

        return getSPARQLDestroy();
    }

    @Override
    public String getSPARQLInsert() {
        String s = util.prefixos.ListaPrefixos();
        s = s + "INSERT DATA{<" + this.className + "> a  owl:Class ;\n "
                + "        rdfs:comment     \"" + this.comment + "\";\n "
                + "        rdfs:label       \"" + this.label + "\" ;\n "
                + "        rdfs:subClassOf  <" + this.subClassOf + "> ."
                + "}";
        return s;
    }

    @Override
    public String getSPARQLDestroy() {
        String s = util.prefixos.ListaPrefixos();
        s += "DELETE WHERE { <" + this.className + ">"
                + "        rdfs:comment ?x;\n "
                + "        rdfs:label ?y;\n "
                + "        rdfs:subClassOf ?z ."
                + "};";
        s += util.prefixos.ListaPrefixos();
        s += "INSERT DATA{<" + this.className + "> "
                + "rdfs:label       \"" + this.label + "\" ;\n "
                + "rdfs:comment     \"" + this.comment + "\";\n "
                + " rdfs:subClassOf  <" + this.subClassOf + "> ."
                + "}";
        return s;
    }

    @Override
    public String getN3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getJSON() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRDFXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
}
