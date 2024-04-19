package upload;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFile;

import upload.ArquivoUtil;

@ViewScoped
@Named
public class UploadAvancadoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<File> arquivos = new ArrayList<>();
    
    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
    }
    
    public void upload(FileUploadEvent event) {
        try {
            UploadedFile uploadedFile = event.getFile();
            
            
            File arquivo = ArquivoUtil.escrever(uploadedFile.getFileName(), uploadedFile.getContents());
            
            arquivos.add(arquivo);
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));            
        } catch (IOException ex) {
            Logger.getLogger(UploadAvancadoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<File> getArquivos() {
        return arquivos;
    }
}