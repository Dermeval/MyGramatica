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
public class CheckRelacional {

    private String regex;

    public CheckRelacional() {
        regex = "\\!\\=|\\=\\=|\\>\\=|\\<\\=|\\<|\\>|\\=";

    }

    public boolean checkRelacional(String current, String acc) {
        current = current + acc;
        return current.matches(regex);
    }

}
