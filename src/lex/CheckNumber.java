
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Número
 *
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckNumber {

    private String regex = "(-(\\x09|\\x0A|\\x0D|\\x20)*)?\\d+(\\.\\d+)?";

    
    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem Números
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um número
     */
    public boolean checkNumber(String current, String acc) {
        current = current + acc;        
        
        return current.matches(regex);
    }
}
