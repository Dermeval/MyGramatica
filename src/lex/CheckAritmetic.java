
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Operador Aritimético
 *
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckAritmetic {

    private String regex;

    public CheckAritmetic() {
        
        this.regex = "\\-\\-|\\-|\\+\\+|\\+|\\*|\\/";
    }

    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem operaores aritiméticos
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um operador aritimético
     */
    public boolean checkAritmetic(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
