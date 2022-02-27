/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.proyecto1_202004734;

import Analizadores.Token;

/**
 *
 * @author justin
 */
public class TokenCaracterCambio {
    Token Caracter;
    boolean cambio;
    public TokenCaracterCambio(Token Caracter,boolean cambio){
        this.Caracter=Caracter;
        this.cambio=cambio;
    }
    
    
    public String getLexema() {
        return Caracter.getLexema();
    }
    
    public boolean getOperador() {
        return Caracter.getOperador();
    }
    
    public String getTipo() {
        return Caracter.getTipo();
    }
}
