
package lex;

/**
 * Classe responsável pelo comportamento do Regex de Palavras Reservadas
 *
 * @author Dermeval Neves e Elias Monteiro
 */
public class CheckReserved {
    
    
 
    private String[] words = {"class", "const", "variables", "method", "return", "main", "if", "then", "else" , "while", "read", "write", "void", "int", "string", "true", "false", "extends"}; 

    public CheckReserved() {
       

    }
    
    /** 
     * Método que verifica a categoria do próximo caractere retornando os caracteres que forem Palavras Reservadas
     * @param current Palavra atual que está sendo lida
     * @param acc Acumulador que acumula os caracteres lidos
     * @return retorna uma Palavra Reservada
     */
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
 

