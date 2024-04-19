/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Administrador
 */
@Named
@RequestScoped
public class ListaArquivosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
     private List<File> arquivos = new ArrayList<>();
    
    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
    }
     public List<File> getArquivos() {
        return arquivos;
    }
    
    
}
