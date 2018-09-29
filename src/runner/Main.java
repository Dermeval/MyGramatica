/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runner;

import java.io.File;
import java.util.Stack;
import javax.swing.JFileChooser;
import lex.CheckNumber;
import structure.MyReader;
import structure.token;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(chooser);
        File selectedFile = chooser.getSelectedFile();
        MyReader reader = new MyReader(selectedFile);
        reader.readLine();
        Stack<token> tokens = reader.getTokens();
        System.out.println("jf");
        
        


    }

}
