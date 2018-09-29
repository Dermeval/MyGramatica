/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Identificador
 * @author Dermeval Neves e Elias Monteiro
 */

public class CheckReserved {
    
    
 
    private String[] words = {"class", "const", "variables", "method", "return", "main", "if", "then", "else" , "while", "read", "write", "void", "int", "string", "true", "false", "extends"}; 

    public CheckReserved() {
       

    }
    public boolean checkReservada(String current , String acc ){
        
        acc = acc+current;
        for (int i = 0; i < words.length; i++) {
            if(acc.equals(words[i])){
                return true;
            }  
        }
        return false;
         
        
        
    }
}
 

