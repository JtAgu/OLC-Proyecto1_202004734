/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

/**
 *
 * @author justin
 */
public class Token {
    String tipo, lexema;
    

    public Token(String tipo, String lexema) {
        this.tipo = tipo;
        this.lexema = lexema;
    }
    
    public String getLexema() {
        return lexema;
    }
    
    public String getTipo() {
        return tipo;
    }
    
}
