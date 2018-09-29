package lex;

/**
 * Classe responsável pelo comportamento do Regex de Identificador
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckNumber {

    private String regex = "(-(\\x09|\\x0A|\\x0D|\\x20)*)?\\d+(\\.\\d+)?";

    public boolean checkNumber(String current, String acc) {
        current = current + acc;

        
        
        
        
        
        
        
        return current.matches(regex);
    }
}
