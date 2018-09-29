package lex;

/**
 * Classe responsável pelo comportamento do Regex de Operador Aritmético
 * @author Dermeval Neves e Elias Monteiro
 */
public class CheckAritmetic {

    private String regex;

    public CheckAritmetic() {
        
        this.regex = "\\-\\-|\\-|\\+\\+|\\+|\\*|\\/";
    }

    /** Método que 
    * @param current Caractere que está sendo lido 
    * @param acc Acumulador - Acumula os caracteres para verificação
    */
    
    
    public boolean checkAritmetic(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
