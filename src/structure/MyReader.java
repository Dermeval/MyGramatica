/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lex.*;

/**
 *
 * @author elias
 */
public class MyReader {

    private final File Arquivo;
    private int linha;
    private Stack<token> Tokens;

    public MyReader(File Arquivo) {
        this.Arquivo = Arquivo;
        this.linha = 1;
        this.Tokens = new Stack<>();

    }

    public void readLine() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(Arquivo));
            String currentLine;
            String acc = "";
            while ((currentLine = br.readLine()) != null) {
                CheckLineComment checkNLineComment = new CheckLineComment();
                if (!checkNLineComment.checkLineComment(currentLine, "")) {

                    String[] splited = currentLine.split("");
                    for (int i = 0; i < splited.length; i++) {
                        String current = splited[i];
                        if (CheckWord(current, acc)) {
                            acc = acc + current;
                            if (i == (splited.length - 1)) {
                                CheckWord(acc);
                                acc = "";
                            }

                        } else {
                            if (!acc.equals("")) {
                                CheckWord(acc);
                                acc = "";
                                CheckWord(current);
                            }
                        }
                        ;

                    }
                    linha++;
                } else {
                    Tokens.add(new token(currentLine, "Comentario", linha));
                    linha++;
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean CheckWord(String current, String acumulador) {

        /*
         *todos  os verificadores devem estar localizados nesse metodos
         * 
         */
        CheckNumber number = new CheckNumber();
        CheckReserved reserved = new CheckReserved();
        CheckIdent ident = new CheckIdent();
        CheckAritmetic aritmetic = new CheckAritmetic();
        CheckRelacional relacmional = new CheckRelacional();
        CheckLogical logical = new CheckLogical();
        CheckDelim delim = new CheckDelim();

        boolean isNumber = number.checkNumber(current, acumulador);
        boolean isIdent = ident.CheckIdent(current, acumulador);
        boolean isReserved = reserved.checkReservada(current, acumulador);
        boolean isAritmetic = aritmetic.checkAritmetic(current, acumulador);
        boolean isRelacional = relacmional.checkRelacional(current, acumulador);
        boolean isLogical = logical.checkLogical(current, acumulador);
        boolean isdelim = delim.checkDelim(current, acumulador);

        if (isNumber || isIdent || isReserved || isAritmetic || isRelacional || isLogical || isdelim) {
            return true;
        }

        return false;
    }

    private void CheckWord(String current) {

        if (new CheckReserved().checkReservada("", current)) {
            token token = new token(current, "PalavraReservada", linha);
            Tokens.add(token);
            System.out.println("adicionar na lista de Palavras reservadas: " + current);

        }
        if (new CheckIdent().CheckIdent("", current)) {
            token token = new token(current, "Identificador", linha);
            Tokens.add(token);
            System.out.println("adicionar na lista de identificadores: " + current);
        }

        if (new CheckNumber().checkNumber("", current)) {
            if (!Tokens.isEmpty()) {

                token poped = Tokens.pop();
                if (poped.getData().equals("-")) {
                    String temp = poped.getData() + current;
                    poped.setData(temp);
                    poped.setType("Numero");
                    Tokens.add(poped);
                    System.out.println("adicionar na lista de Numeros: " + current);
                } else {
                    if (poped.getType().equals("TempNumero")) {
                        poped.setData(poped.getData() + current);
                        poped.setType("Numero");
                        Tokens.add(poped);
                    } else {
                        
                        
                        Tokens.add(poped);
                                                
                        Tokens.add(new token(current, "Numero", linha));
                    }
                }

            } else {

                Tokens.add(new token(current, "Numero", linha));
                System.out.println("adicionar na lista de Numeros: " + current);
            }

        }

        if (new CheckAritmetic().checkAritmetic("", current)) {
            if (!Tokens.isEmpty()) {

                token poped = Tokens.pop();
                if (poped.getData().equals("+")) {
                    String temp = poped.getData() + current;
                    poped.setData(temp);
                    poped.setType("OP Aritmetico");
                    Tokens.add(poped);
                } else {
                    if (poped.getData().equals("-")) {
                        String temp = poped.getData() + current;
                        poped.setData(temp);
                        poped.setType("OP Aritmetico");
                        Tokens.add(poped);
                    }

                    Tokens.add(poped);
                    token token = new token(current, "OP Aritmetico", linha);
                    Tokens.add(token);

                    System.out.println("adicionar na lista de Operadores A: " + current);
                }

            } else {
                token token = new token(current, "OP Aritmetico", linha);
                Tokens.add(token);
                System.out.println("adicionar na lista de Operadores A: " + current);

            }

        }

        if (new CheckRelacional().checkRelacional("", current)) {
            token poped = Tokens.pop();
            if (poped.getData().equals("<") && current.equals("=")) {
                poped.setData(poped.getData() + current);
                poped.setType("OP Relacional");
                Tokens.add(poped);

            } else {
                if (poped.getData().equals(">") && current.equals("=")) {
                    poped.setData(poped.getData() + current);
                    poped.setType("OP Relacional");
                    Tokens.add(poped);

                } else {
                    Tokens.add(poped);
                    token token = new token(current, "OP Relacional", linha);
                    Tokens.add(token);
                    System.out.println("adicionar na lista de Operadores Re: " + current);
                }

            }

        }

        if (new CheckLogical().checkLogical("", current)) {
            token token = new token(current, "OP Logico", linha);
            Tokens.add(token);

            System.out.println("adicionar na lista de Operadores Log: " + current);
        }
        if (new CheckDelim().checkDelim(current, "")) {
            if (current.equals(".") && !Tokens.isEmpty()) {
                token poped = Tokens.pop();
                if (poped.getType().equals("Numero")) {
                    poped.setData(poped.getData() + current);
                    poped.setType("TempNumero");
                    Tokens.add(poped);
                }

            } else {
                Tokens.add(new token(current, "Delim", linha));
                System.out.println("achou o ponto");

            }

        }

    }

    public Stack<token> getTokens() {
        return this.Tokens;
    }
}
