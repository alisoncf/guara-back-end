/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;


/**
 *
 * @author Administrador
 */
public class Raw3 implements Serializable {
    private String subject;
    private String property;
    private String object;

    public Raw3(String subject, String property, String object) {
        this.subject = subject;
        this.property = property;
        this.object = object;
    }

    public Raw3() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
    
    
    
}
