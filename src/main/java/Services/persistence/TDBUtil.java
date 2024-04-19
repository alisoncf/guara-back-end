/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.persistence;

/**
 *
 * @author Administrador
 */
public abstract class TDBUtil {

    public enum Dimensao{
        PESSOA,FENÔMENO,ESPAÇO,TEMPO
    }
//private static  String url_remota = "http://localhost:8080/fuseki/"; //descomente essa linha para produção
    private static  String url_remota = "http://200.137.241.247:8080/fuseki/"; //descomente esse linha para desenvolvimento
    
//    private static  String url_remota = "http://200.137.241.247:8080/fuseki/dataset.html?tab=query&ds=/tipo"; 
    private static  String url_local = "http://localhost:3030/";
//    private static  String db_tipo = "cartorio";
//    private static String tdb_name = "cartorioobj";
    private static  String tdb_tipo = "mplclass";
    private static String tdb_name = "mplobj";
    private static String tdb_repo = "repositoriosamigos";

    public static String getTdb_repo() {
        return tdb_repo;
    }
    
    public static String getUrlRemota() {
//        url_remota = url_local;
        
        return TDBUtil.url_remota;
    }
    public static String getTDBClasse(){
        return tdb_tipo;
    }
    public static String getTDBObjeto(){
        return tdb_name;
    }
    public static String getTdbBase(){
        return url_remota+ tdb_name+"/";
    }
}
