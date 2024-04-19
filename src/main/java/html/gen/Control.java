/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

/**
 *
 * @author cgpre
 */
public abstract class Control {

    private String element;
    private String type;
    private String id;
    private String classe;
    private String value;
    private String valor;
    protected Tag tag;
    
    public Control(String element, String type, String id, String classe, String value, String valor) {
        this.element = element;
        this.type = type;
        this.id = id;
        this.classe = classe;
        this.value = value;
        this.valor = valor;
        
    }

    public Control(String element, String id, String classe, String value, String valor) {
        this.element = element;
        this.id = id;
        this.classe = classe;
        this.value = value;
        this.valor = valor;
    }
    
    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

 

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

 

}
