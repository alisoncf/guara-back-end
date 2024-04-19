/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Administrador
 */
public abstract class Util {

    public static String file_path() {
        String local = System.getProperty("user.dir");
        if ("C:\\".equals(local.substring(2))) {

            return "C:\\desenv\\tese\\files\\";
        } else {
            return "/opt/tomcat/files/";
        }
    }

    public String EntreAspas(String s) {
        return "\"" + s + "\"";
    }

}
