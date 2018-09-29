package lex;

/**
 * Classe responsável pelo comportamento do Regex de Identificador
 * @author Dermeval Neves e Elias Monteiro
 */

public class CkeckBlocComment {

    private String regex;

    public CkeckBlocComment() {
        this.regex = "^(/\\*).*(\\*/)$";

    }
    
 /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem Comentários
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um Comentário de Linha
     */
    
    public boolean checkLineComment(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
