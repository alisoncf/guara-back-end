package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alison
 */
public class Stopwords {

    private String sw;
    private String artigos;
    private String preposicoes;
    private String outros;
    private String palavras_retiradas;
    private String palavras_chave;
    private String[] nao_aceitas = null;
    private String[] aceitas = null;
    private ArrayList<String> listaA = new ArrayList<String>();
    private ArrayList<String> listaN = new ArrayList<String>();

    private void inicia() {
        artigos = "o;as;os;as;um;uma;";
        preposicoes = "de;da;dos;das;dum;pra;pelo;pela;pelas;duma;na;nas;no;nos";
        outros = "com;em;nem;quem;qual;como;?";


        sw = artigos + preposicoes + outros;
    }

    public Stopwords(String palavras_chave) {
        inicia();
        this.palavras_chave=palavras_chave;
        processa(palavras_chave);
    }
    public String getPalavrasChave(){
        return this.palavras_chave;
    }

    public String getSW() {
        return this.sw;
    }

    public String retornaRadicalVerbo(String verbo){
        String radical="";
        verbo = verbo.toLowerCase();
        if(verbo.substring(verbo.length()).equals("R")){
            
        }
        
        
        return radical;
        
    }
    
    private String[] getPalavras(String kw) {
        String[] palavra;
        palavra = kw.split(" ");



        return palavra;
    }

    private void processa(String kw) {



        String[] palavra = this.getPalavras(kw);
        List lista = Arrays.asList(palavra);

        String s = "";
        int la = 0;
        int ln = 0;

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).toString();
            if (this.getSW().contains(s)) {
                listaN.add(palavra[i]);
                
                ln++;
            } else {
                try {
                    listaA.add(palavra[i]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                la++;
            }
        }





    }

    public ArrayList<String> getListPalavrasAceitas() {
        return this.listaA;
    }

    public ArrayList<String> getListPalavrasNaoAceitas() {
        return this.listaN;
    }

    

    public String[] getPalavrasNaoAceitas() {
        return this.nao_aceitas;

    }

//    public List<String> getSinomimos() {
//        Wordnet w = new Wordnet();
//        return w.getListaSinonimos(getListPalavrasAceitas());
//    }
    
    
    public boolean getSingular(String palavra){
        boolean b=false;
        
        if (palavra.substring(palavra.length()-1).equalsIgnoreCase("s")){
            
        }        
        
        return b;
    }
    
    
}
