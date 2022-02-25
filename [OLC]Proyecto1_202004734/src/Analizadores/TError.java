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
public class TError {
    String tipo, lexema, descripcion;
    int linea, columna;

    public TError(String tipo, String lexema, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.descripcion = descripcion;
        this.linea = linea+1;
        this.columna = columna+1;
    }
            
     public String show() {
        String data = "";
        data += "\ntipo:" + tipo;
        data += "\nlexema:" + lexema;
        data += "\ndescripcion:" + descripcion;
        data += "\nlinea:" + String.valueOf(linea);
        data += "\ncolumna:" + String.valueOf(columna);
        return data;
    }
     
     
     public String getLexema() {
        return lexema;
    }


    public int getLine() {
        return linea;
    }


    public int getColumn() {
        return columna;
    }


    public String getDescripcion() {
        return descripcion;
    }
}
