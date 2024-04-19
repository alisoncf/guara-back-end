/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author cgpre
 */
public abstract class RDFUtil {

    public enum TipoRecurso {
        URI, LITERAL, PREFIX
    }

    /**
     * @param node
     * @param args the command line arguments
     * @return
     */
    public static TipoRecurso getTipo(String node) {
        node = node.toLowerCase();
        if (node == null) {
            return TipoRecurso.LITERAL;
        }
        if (node.length() <= 1) {
            return TipoRecurso.LITERAL;
        }
        if (node.substring(1).equals("\"") || node.substring(1).equals("'")) {
            return TipoRecurso.LITERAL;
        }

        if (node.length() > 7) {
            String cab = node.substring(0, 8);
            if (cab.substring(0, 7).equals("http://") || cab.substring(0, 8).equals("https://")
                    || cab.substring(0, 4).equals("www.") || cab.substring(0, 7).equals("mailto:")
                    || cab.substring(0, 6).equals("ftp://")) {
                return TipoRecurso.URI;
            }
            
        } else if (0>1) {
            return TipoRecurso.PREFIX;
        } else {
            return TipoRecurso.LITERAL;
        }
        
        return TipoRecurso.LITERAL;
    }

    public static String getValue(String node) {

        try {
            TipoRecurso t = getTipo(node);
            if (t == TipoRecurso.LITERAL) {
                return "\"" + node + "\"";
            }
            else if(t==TipoRecurso.PREFIX){
                return node;
            }
            else {
                return "<" + node + ">";
            }
        } catch (Exception e) {
            return "\"" + "\"";
        }

    }

}
