
package lex;

/**
 * Classe responsável pelo comportamento do Regex do Delimitador
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckDelim {

    private String regex;

    public CheckDelim() {

        this.regex = "\\;|\\,|\\(|\\)|\\[|\\]|\\{|\\}|\\.";

    }

    /**
     * Método que verifica a categoria do próximo caractere lido
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um delimitador
     */
    
    public boolean checkDelim(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
