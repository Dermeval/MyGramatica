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
public class CheckNumber {

    private String regex = "(-(\\x09|\\x0A|\\x0D|\\x20)*)?\\d+(\\.\\d+)?";

    public boolean checkNumber(String current, String acc) {
        current = current + acc;

        
        
        
        
        
        
        
        return current.matches(regex);
    }
}
