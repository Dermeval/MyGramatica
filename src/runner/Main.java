
package runner;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFileChooser;
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
        reader.readbyWord();
        reader.CheckUnknow();
        Stack<token> tokens = reader.getTokens();
        ArrayList<token> unk = reader.unknowTokens();
        System.out.println("");  
    }
}
        
        /*File listaArquivos = new File("entrada");
        File[] arquivos = listaArquivos.listFiles();
        MyReader reader2 = new MyReader(listaArquivos);

        for (File file : arquivos) {
            if(!file.isDirectory() && !file.getName().contains("output_")){
                
                //reader2.unknowTokens(file);
            }
    }
    }
    */

        
    
    
    


