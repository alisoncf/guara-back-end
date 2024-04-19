/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

import java.util.List;

/**
 *
 * @author cgpre
 */
public class Input extends FullTag {
    private Tag tag;
    private final String valor;

    public Input(String valor, String tag) {
        super(tag);
        this.valor = valor;
    }
    
    
    
}
