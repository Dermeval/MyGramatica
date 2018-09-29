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
public class CheckIdent {

    private String regex;

    public CheckIdent() {

        this.regex = "[a-zA-Z]([a-zA-Z]|(\\d)|(_))*";

    }

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
