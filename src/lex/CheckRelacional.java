
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Operadore Relacional
 *
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckRelacional {

    private String regex;

    public CheckRelacional() {
        regex = "\\!\\=|\\=\\=|\\>\\=|\\<\\=|\\<|\\>|\\=";

    }

    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem operaores relacionais
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um Operador Relacional
     */
    
    public boolean checkRelacional(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
