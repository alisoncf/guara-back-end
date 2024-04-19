/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package html.gen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author cgpre
 */
public class HTML  {
    private FullTag html;
    private FullTag title;
    
    private List<EmptyTag> meta = new ArrayList();
    
    private Tag body;
    
    private List<Tag> script;
    public HTML(){
       this.html = new FullTag("html");
       this.title=new FullTag("title");
       EmptyTag e = new EmptyTag("meta", "charset=\"UTF-8\"", "");
       this.meta.add( e);
       
       
       e =   new EmptyTag("meta", "name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"","");
       this.meta.add(e);
        

    }
    
    public String getHTML(){
        String s;
        s= this.html.AbreTag()+ "\n";
        s+=this.title.getTagCompleta() + "\n";
        
        for (Iterator<EmptyTag> iterator = meta.iterator(); iterator.hasNext();) {
            EmptyTag next = iterator.next();
            s+=next.getTagCompleta() + "\n";
        }
        
        
        
        
        s+=this.html.fechaTag();
        return s;
    }
    
}
