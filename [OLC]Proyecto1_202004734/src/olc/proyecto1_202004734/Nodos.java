/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.proyecto1_202004734;

import Analizadores.Token;
import java.util.ArrayList;

/**
 *
 * @author justin
 */
public class Nodos {

    boolean operador;
    String Nodo;
    String lexema;
    boolean leido;
    boolean anulable;
    ArrayList Primeros;
    ArrayList Ultimos;
    ArrayList Siguientes;
    int Hoja;

    public Nodos(String Nodo, String lexema, boolean operador, boolean Anulable) {
        this.Primeros = new ArrayList();
        this.Ultimos = new ArrayList();

        this.operador = operador;
        this.Nodo = Nodo;
        this.lexema = lexema;
        this.leido = false;
        this.anulable = Anulable;
    }

    public Nodos(String Nodo, String lexema, boolean operador, boolean Anulable, int Hoja) {
        this.Primeros = new ArrayList();
        this.Ultimos = new ArrayList();
        this.Siguientes = new ArrayList();
        this.operador = operador;
        this.Nodo = Nodo;
        this.lexema = lexema;
        this.leido = false;
        this.anulable = Anulable;
        this.Hoja = Hoja;
    }

    public String getLexema() {
        return lexema;
    }

    public String getTipo() {
        return Nodo;
    }

    public boolean getOperador() {
        return operador;
    }

}
