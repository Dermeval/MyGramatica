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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import lex.*;

/**
 * Classe principal responsável por analisar o tokens e categorizarlos
 *
 * @author Dermeval Neves e Elias Monteiro
 */
public class MyReader {

    private final File Arquivo;
    private int currentLineCount;
    private Stack<token> Tokens;
    private ArrayList<token> unknowTokens;
    private boolean islocked;

    public MyReader(File Arquivo) {
        this.Arquivo = Arquivo;
        this.currentLineCount = 1;
        this.Tokens = new Stack<>();
        this.unknowTokens = new ArrayList<>();
        this.islocked = false;
    }

    private void checkWord(String current) {

        if (new CheckReserved().checkReservada("", current)) {
            token token = new token(current, "PalavraReservada", currentLineCount);
            Tokens.add(token);
            System.out.println("adicionar na lista de Palavras reservadas: " + current);

        } else {
            if (new CheckIdent().CheckIdent("", current)) {
                token token = new token(current, "Identificador", currentLineCount);
                Tokens.add(token);
                System.out.println("adicionar na lista de identificadores: " + current);
            }
        }
        if (new CheckNumber().checkNumber("", current)) {

            if (!Tokens.isEmpty()) {

                token poped = Tokens.pop();
                if (poped.getData().equals("-")) {
                    poped.setData(poped.getData() + current);
                    poped.setType("Numero");
                    Tokens.add(poped);

                } else {
                    Tokens.add(poped);
                    Tokens.add(new token(current, "Numero", currentLineCount));
                }

            } else {
                Tokens.add(new token(current, "Numero", currentLineCount));
            }

        }

        if (new CheckAritmetic().checkAritmetic("", current)) {
            Tokens.add(new token(current, "OP Aritmetico", currentLineCount));
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
                    token token = new token(current, "OP Relacional", currentLineCount);
                    Tokens.add(token);
                    System.out.println("adicionar na lista de Operadores Re: " + current);
                }

            }
        }

        if (new CheckLogical().checkLogical("", current)) {
            token token = new token(current, "OP Logico", currentLineCount);
            Tokens.add(token);

        }
        if (new CheckDelim().checkDelim(current, "")) {

            Tokens.add(new token(current, "Delim", currentLineCount));

        }

        if (new CheckSymbol().checkSymbol(current, "")) {
            Tokens.add(new token(current, "Symbolo", currentLineCount));
        }

        if (!CheckLex(current)) {
            if (!current.equals("")) {

                if (!current.equals(" ")) {
                    this.unknowTokens.add(new token(current, "Unknow", currentLineCount));
                    CheckUnknow();
                     
                }

            }

        }

    }

    public Stack<token> getTokens() {
        return this.Tokens;
    }

    public void readbyWord() {
        BufferedReader br;
        String currentLine;
        try {
            br = new BufferedReader(new FileReader(Arquivo));
            while ((currentLine = br.readLine()) != null) {
                if (!new CheckLineComment().checkLineComment(currentLine, "")) {
                    String[] split = currentLine.split(" ");
                    for (String currentWord : split) {
                        checkWord(currentWord);

                    }
                } else {
                    Tokens.add(new token(currentLine, "LineComent", this.currentLineCount));
                }
                this.currentLineCount++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException ex) {
            System.out.println("Erro ao ler arquivo");
        }
    }

    private boolean CheckLex(String current) {
        String acumulador = "";
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
        CheckSymbol checkSymbol = new CheckSymbol();
        CheckBlocComment blocComment = new CheckBlocComment();
        CheckLineComment lineComment = new CheckLineComment();

        boolean isNumber = number.checkNumber(current, acumulador);
        boolean isIdent = ident.CheckIdent(current, acumulador);
        boolean isReserved = reserved.checkReservada(current, acumulador);
        boolean isAritmetic = aritmetic.checkAritmetic(current, acumulador);
        boolean isRelacional = relacmional.checkRelacional(current, acumulador);
        boolean isLogical = logical.checkLogical(current, acumulador);
        boolean isdelim = delim.checkDelim(current, acumulador);
        boolean isSymbol = checkSymbol.checkSymbol(current, acumulador);
        boolean isBloc = blocComment.checkBlockComment(current, acumulador);
        boolean isLineComment = lineComment.checkLineComment(current, acumulador);
                
                
        if (isNumber || isIdent || isReserved || isAritmetic || isRelacional || isLogical || isdelim || isSymbol || isBloc || isLineComment) {
            return true;
        }

        return false;
    }

    public ArrayList<token> unknowTokens() {
        return this.unknowTokens;
    }

    public void CheckUnknow() {
        String acc = "";

        for (Iterator<token> iterator = unknowTokens.iterator(); iterator.hasNext();) {
            token next = iterator.next();
            this.currentLineCount = next.getLinha();
            String current;
            current = next.getData();
            String[] splited = current.split("");
            for (String currentSplited : splited) {
                if (CheckWord(currentSplited, acc)) {
                    acc = acc + currentSplited;
                } else {
                    checkWord(acc);
                    acc = currentSplited;
                }

            }
            checkWord(acc);
            acc = "";

        }
        this.unknowTokens = new ArrayList<>();

    }
    
    /**
     * Método onde ficam todos os verificadores dos tokens por palavras
     * @param current - Caractere que está sendo lido
     * @param acumulador - Acumulador onde guarda os caracteres lidos
    */
    private boolean CheckWord(String current, String acumulador) {

        CheckNumber number = new CheckNumber();
        CheckReserved reserved = new CheckReserved();
        CheckIdent ident = new CheckIdent();
        CheckAritmetic aritmetic = new CheckAritmetic();
        CheckRelacional relacmional = new CheckRelacional();
        CheckLogical logical = new CheckLogical();
        CheckDelim delim = new CheckDelim();
        CheckSymbol checkSymbol = new CheckSymbol();
        CheckBlocComment blocComment = new CheckBlocComment();
        CheckLineComment lineComment = new CheckLineComment();

        boolean isNumber = number.checkNumber(current, acumulador);
        boolean isIdent = ident.CheckIdent(current, acumulador);
        boolean isReserved = reserved.checkReservada(current, acumulador);
        boolean isAritmetic = aritmetic.checkAritmetic(current, acumulador);
        boolean isRelacional = relacmional.checkRelacional(current, acumulador);
        boolean isLogical = logical.checkLogical(current, acumulador);
        boolean isdelim = delim.checkDelim(current, acumulador);
        boolean isSymbol = checkSymbol.checkSymbol(current, acumulador);
        boolean isBloc = blocComment.checkBlockComment(current, acumulador);
        boolean isLineComment = lineComment.checkLineComment(current, acumulador);
                

        if (isNumber || isIdent || isReserved || isAritmetic || isRelacional || isLogical || isdelim || isSymbol || isBloc || isLineComment) {
            return true;
        }

        return false;
    }
}
