
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Operadores Lógicos
 *
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckLogical {

    private String regex;

    public CheckLogical() {

        this.regex = "\\!|\\&\\&|\\|\\|";
    }

    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem operadores lógicos
     * @param current Palavra atual que está sendo lida
     * @param acumulador Acumulador que acumula os caracteres lidos
     * @return retorna um operador lógico
     */
    public boolean checkLogical(String current, String acumulador) {
        current = current + acumulador;
        String[] logicos = {"!", "&&", "||", "&"};
        for (int i = 0; i < logicos.length; i++) {
            String string = logicos[i];
            if (current.equals(string)) {
                return true;
            }

        }

        return false;
    }

}
