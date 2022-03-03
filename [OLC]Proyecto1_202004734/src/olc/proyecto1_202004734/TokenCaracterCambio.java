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
    int Elem;
    public TokenCaracterCambio(Token Caracter,boolean cambio,int i){
        this.Caracter=Caracter;
        this.cambio=cambio;
        this.Elem=i;
    }
    
    public void SetElem(int e){
        this.Elem=e;
    }
    
    public String getLexema() {
        return Caracter.getLexema();
    }
    
    public void SetLexema(String lexema) {
       Caracter.setLexema(lexema);
    }
    
    public boolean getOperador() {
        return Caracter.getOperador();
    }
    
    public String getTipo() {
        return Caracter.getTipo();
    }
}
