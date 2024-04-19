/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TipoFisicoSelect {

   
    private String[] selectedOptions2;
 
    private String[] selectedtipoFisico;
    private List<String> tipos;
    
    @PostConstruct
    public void init() {
        tipos = new ArrayList<>();
        tipos.add("Arqueológico");
        tipos.add("Bibliotecário");
        tipos.add("Documental");
        tipos.add("ImagemSom");
        tipos.add("Museológico");

        

        

      
    }

   

    public String[] getSelectedOptions2() {
        return selectedOptions2;
    }

    public void setSelectedOptions2(String[] selectedOptions2) {
        this.selectedOptions2 = selectedOptions2;
    }

 

    public String[] getSelectedtipoFisico() {
        return selectedtipoFisico;
    }

    public void setSelectedtipoFisico(String[] selectedtipoFisico) {
        this.selectedtipoFisico = selectedtipoFisico;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipo(List<String> mediaTypes) {
        this.tipos = tipos;
    }

   

    
}