/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lex;

/**
 *
 * @author elias
 */
public class CheckLogical {

    private String regex;

    public CheckLogical() {

        this.regex = "\\!|\\&\\&|\\|\\|";
    }

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
