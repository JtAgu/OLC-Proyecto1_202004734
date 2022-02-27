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
public class Expresiones {
    ArrayList Caracteres;
    String nombre;
    public Expresiones(String nombre,ArrayList Lista){
        this.nombre=nombre;
        this.Caracteres=Lista;
    }
    
    public String DotAFND(){
        String contenido="digraph G {\n";
        contenido += "label = \"AFND "+this.nombre+"\"\n";
        contenido += "S0 [label = \"S0\"]\n";
        int c=0;
        int NumNodo=1;
        ArrayList <Token> aux=this.Caracteres;
        System.out.println(aux.size());
        while(c<aux.size()){
            
            Token actual=(Token)aux.get(c);
            if(actual.getOperador()){
                String lexema=actual.getLexema();
                if(lexema.equals(".")){
                    
                    c++;
                    actual=(Token)aux.get(c);
                    contenido += "S"+NumNodo+" [label = \"S"+NumNodo+"\"]\n";
                    contenido+="rank=same{S"+(NumNodo-1)+"-> S"+NumNodo+"[label=\""+actual.getLexema()+"\"]}\n";
                    NumNodo++;
                }else if(lexema.equals("*")){
                    int Inicio=NumNodo-1;
                    contenido += "S"+NumNodo+" [label = \"S"+NumNodo+"\"]\n";
                    contenido+="rank=same{S"+(NumNodo-1)+"-> S"+NumNodo+"[label=\"ε\"]}\n";
                    NumNodo++;
                    c++;
                    
                    actual=(Token)aux.get(c);
                    
                    int Operadores=0;
                    while(actual.getOperador()){
                        Operadores++;
                        c++;
                        actual=(Token)aux.get(c);
                    }
                    c=c-Operadores;
                    actual=(Token)aux.get(c);
                
                    if (Operadores == 0) {

                    } else {
                        boolean or = false;
                        Operadores++;
                        int FinalOr = 0, inicioOr = 0;
                        while (Operadores >= 0) {

                            if (actual.getLexema().equals("|")) {
                                if (or) {

                                    c++;
                                    actual = (Token) aux.get(c);
                                    contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                                    contenido += "rank=same{S" + (NumNodo - 1) + "-> S" + NumNodo + "[label=\"" + actual.getLexema() + "\"]}\n";
                                    NumNodo++;

                                } else {
                                    inicioOr = NumNodo;
                                    c++;
                                    actual = (Token) aux.get(c);
                                    contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                                    contenido += "rank=same{S" + (inicioOr) + "-> S" + NumNodo + "[label=\"ε\"]}\n";
                                    NumNodo++;

                                    c++;

                                    actual = (Token) aux.get(c);
                                    contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                                    contenido += "rank=same{S" + (inicioOr) + "-> S" + NumNodo + "[label=\"ε\"]}\n";
                                    NumNodo++;
                                }
                                or = true;

                            } else if (actual.getLexema().equals(".")) {
                                c++;
                                actual = (Token) aux.get(c);
                                contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                                contenido += "rank=same{S" + (NumNodo - 1) + "-> S" + NumNodo + "[label=\"" + actual.getLexema() + "\"]}\n";
                                NumNodo++;
                                c++;
                                actual = (Token) aux.get(c);
                            }
                            
                            System.out.println(actual.getLexema());
                            Operadores--;
                            System.out.println(Operadores);
                            System.out.println(contenido);
                            System.out.println("\n");
                        }
                    }

                    contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                    contenido += "rank=same{S" + (NumNodo - 1) + "-> S" + NumNodo + "[label=\"ε\"]}\n";
                    contenido += "rank=same{S" + (NumNodo - 1) + "-> S" + (Inicio + 1) + "[label=\"ε\"]}\n";
                    contenido += "S" + (Inicio) + "-> S" + NumNodo + "[label=\"ε\"]\n";
                    NumNodo++;
                }
            }
            c++;
        }
        contenido+="S"+(NumNodo-1)+"[color=blue]";
        contenido += "}";
        System.out.println(contenido);
        return contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Token> getLista() {
        return Caracteres;
    }
    
    
}
