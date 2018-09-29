
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Identificador
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckIdent {

    private String regex;

    public CheckIdent() {

        this.regex = "[a-zA-Z]([a-zA-Z]|(\\d)|(_))*";

    }

     /** 
     * Método que verifica a categoria do próximo caractere lido e se caso for um Identificaor, retorna.
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna um Identificador
     */
    
    public boolean CheckIdent(String current, String acc) {
        current = current + acc;
        CheckReserved cr = new CheckReserved();
        if (cr.checkReservada(current, "")) {
            return false;
        } else {
            return current.matches(regex);
        }
    }

}
