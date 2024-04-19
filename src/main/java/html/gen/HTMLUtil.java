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
public class HTMLUtil {

    public String HTMLPadrao() {
        return "<html lang=\"pt-br\">";
    }

    public String HTMLPadrao(String lang) {
        return "<HTML " + lang + ">";
    }

    public String HeadPadrado(String titulo) {

        String s = "<head>\n"
                + "        <title>" + titulo + "</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>";
        return s;

    }

    public String HeadPadrado(String titulo, String css) {

        String s = "<head>\n"
                + "        <title>" + titulo + "</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + css + "\n"
                + "    </head>";
        return s;

    }

    public String HeadPadrado(String titulo, String css, String js) {

        String s = "<head>\n"
                + "        <title>" + titulo + "</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + css + "\n"
                + js + "\n"
                + "    </head>";
        return s;
    }

    public String GeraLinhaCabTable(List<String> h, String atribRow, String atribH) {
        String s;
        s = "<tr " + atribRow + ">\n";

        for (Object object : h) {
            s += "<th " + atribH + ">" + object.toString() + "</th>\n";
        }
        s += "</tr>";
        return s;
    }
    

}
