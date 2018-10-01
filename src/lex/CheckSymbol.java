package lex;

/**
 * Classe responsável pelo comportamento do Regex de Simbolo
 *
 * @author Dermeval Neves e Elias Monteiro
 */
public class CheckSymbol {

    private String regex;

    public CheckSymbol() {

        this.regex = "[\\x20-\\x7F]";
    }

    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem Simbolo
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um Simbolo
     */
    public boolean checkSymbol(String current, String acc) {
        current = current + acc;

        if (!current.matches("\"")) {
            return current.matches(regex);
        } else {
            return current.matches(regex);
        }
    }

}