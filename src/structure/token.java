/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

/**
 *
 * @author elias
 */
public class token {

    private String data;
    private String type;
    private int linha;

    public token(String data, String type, int linha) {
        this.data = data;
        this.type = type;
        this.linha = linha;
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public int getLinha() {
        return linha;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

}
