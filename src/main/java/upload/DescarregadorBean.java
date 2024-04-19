package upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class DescarregadorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private StreamedContent streamedContent;

    public void descarregar(File file) throws IOException {
        try {
            InputStream inputStream;
            

            inputStream = new FileInputStream(file);
            streamedContent = new DefaultStreamedContent(inputStream,
                Files.probeContentType(file.toPath()), file.getName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DescarregadorBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }

    public StreamedContent getStreamedContent() {

        return streamedContent;
    }
}
