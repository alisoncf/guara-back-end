/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author cgpre
 */
public interface MOT {
    public String getSPARQLInsert();
    public String getSPARQLDestroy();
    public String getN3();
    public String getJSON();
    public String getRDFXML();
    
}
