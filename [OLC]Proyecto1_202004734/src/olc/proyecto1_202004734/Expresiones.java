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

    ArrayList<Token> Caracteres;
    String Regex;
    String nombre;
    ArrayList <Nodos> Nodos;
    ArrayList <String> siguientes=new ArrayList<String>();

    public Expresiones(String nombre, ArrayList<Token> Lista) {
        this.nombre = nombre;
        this.Caracteres = Lista;
        this.Nodos = new ArrayList <Nodos>();
    }

    public Expresiones(String nombre, String regex) {
        this.nombre = nombre;
        this.Regex = regex;
    }

    public String DotAFND() {
        String contenido = "digraph G {\n";
        contenido += "label = \"ARBOL PARA " + this.nombre + "\"\n";
        contenido += "S0 [label = \"S0\"]\n";
        int c = 0;
        int NumNodo = 1;
        ArrayList<Token> aux = this.Caracteres;
        System.out.println(aux.size());
        while (c < aux.size()) {

            Token actual = (Token) aux.get(c);
            if (actual.getOperador()) {
                String lexema = actual.getLexema();
                if (lexema.equals(".")) {

                    c++;
                    actual = (Token) aux.get(c);
                    contenido += "S" + NumNodo + " [label = \"S" + NumNodo + "\"]\n";
                    contenido += "rank=same{S" + (NumNodo - 1) + "-> S" + NumNodo + "[label=\"" + actual.getLexema() + "\"]}\n";
                    NumNodo++;
                } else if (lexema.equals("*")) {

                }
            }
            c++;
        }
        contenido += "S" + (NumNodo - 1) + "[color=blue]";
        contenido += "}";
        System.out.println(contenido);
        return contenido;
    }

    public String DotAFN() {
        ArrayList<Nodos> ListaNodos = new ArrayList<Nodos>();
        ArrayList<Nodos> ListaNodos2 = new ArrayList<Nodos>();
        int Punto = 0, Mas = 0, Inte = 0, Cad = 0, id = 0, Aste = 0, Or = 0, Hoja = 0;
        int i = 0;
        
        System.out.println(nombre);
        
        
        
        while (Caracteres.size() > i) {
            Token tk = Caracteres.get(i);
            String tipo = "";
            boolean op = false, anulable = false;
            if (tk.getTipo().equals("Punto")) {
                tipo = "SPunto" + Punto;
                Punto++;
                op = true;
                anulable = false;
            } else if (tk.getTipo().equals("Asterisco")) {
                tipo = "SAsterisco" + Aste;
                Aste++;
                op = true;
                anulable = true;
            } else if (tk.getTipo().equals("Cadena")) {
                tipo = "SCadena" + Cad;
                Cad++;
                op = false;
                anulable = false;
                Hoja++;
            } else if (tk.getTipo().equals("id")) {
                tipo = "Sid" + id;
                id++;
                op = false;
                anulable = false;
                Hoja++;
            } else if (tk.getTipo().equals("InterrogacionC")) {
                tipo = "SInte" + Inte;
                Inte++;
                op = true;
                anulable = true;
            } else if (tk.getTipo().equals("Mas")) {
                tipo = "SMas" + Mas;
                Mas++;
                op = true;
                anulable = false;
            } else if (tk.getTipo().equals("Or")) {
                tipo = "SOr" + Or;
                Or++;
                op = true;
                anulable = false;
            }

            String lex = Caracteres.get(i).getLexema();
            lex = lex.replace("\"", "\\\"");
            lex = lex.replace("|", "\\|");
            Nodos nuevo = null;
            if (op) {
                nuevo = new Nodos(tipo, lex, op, anulable);

            } else {
                nuevo = new Nodos(tipo, lex, op, anulable, Hoja);
                nuevo.Primeros.add(Hoja);
                nuevo.Ultimos.add(Hoja);
            }

            ListaNodos.add(nuevo);
            ListaNodos2.add(nuevo);
            i++;
        }

        String contenido = "digraph G {\n"
                + "node [shape=\"Mrecord\"]";
        contenido += "label = \"ARBOL " + this.nombre + "\"\n";
        i = 0;
        int x = 0, y = 1, z = 2;
        String ultimo = "";

        while (i < ListaNodos.size()) {
            Nodos tk = ListaNodos.get(i);
            if(tk.getOperador()){
                contenido += tk.Nodo + "[label=\"|{|" + tk.lexema + "}|\"]\n";
            }else{
                String ult="[ ",prim="[ ";
                for(int a=0;a<tk.Primeros.size();a++){
                    prim+=tk.Primeros.get(a)+" ,";
                }
                for(int a=0;a<tk.Ultimos.size();a++){
                    ult+=tk.Ultimos.get(a)+" ,";
                }
                ult = ult.substring(0, ult.length() - 1);
                prim = prim.substring(0, prim.length() - 1);
                ult+=" ]";prim+=" ]";
                
                contenido += tk.Nodo + "[label=\""+prim+"|{"+tk.anulable+"|" + tk.lexema + "|"+tk.Hoja+"}|"+ult+"\"]\n";
            }
            i++;
        }

        while (z < ListaNodos.size()) {

            Nodos tk0 = ListaNodos.get(x);
            Nodos tk1 = ListaNodos.get(y);
            Nodos tk2 = ListaNodos.get(z);

            if (tk0.getOperador() && !tk1.getOperador() && !tk2.getOperador() && (!tk0.getLexema().equals("?") && !tk0.getLexema().equals("+") && !tk0.getLexema().equals("*"))) {
                System.out.println("");
                
                ultimo = tk0.Nodo;

                tk0.leido = true;
                tk1.leido = true;
                tk2.leido = true;
                
                tk0.operador = false;

                contenido += tk0.Nodo + "->" + tk2.Nodo + "\n";
                contenido += tk0.Nodo + "->" + tk1.Nodo + "\n";
                
                
                
                ListaNodos.remove(z);
                ListaNodos.remove(y);

                if (tk0.lexema.equals("\\|")) {
                    if (tk1.anulable || tk2.anulable) {
                        for (int a = 0; a < ListaNodos2.size(); a++) {
                            Nodos tk = ListaNodos2.get(a);
                            if (tk0.Nodo.equals(tk.Nodo)) {
                                tk.anulable = true;
                                break;
                            }
                        }
                    }
                    Nodos tk = null;
                    for (int a = 0; a < ListaNodos2.size(); a++) {
                        tk = ListaNodos2.get(a);
                        if (tk0.Nodo.equals(tk.Nodo)) {
                            break;
                        }
                    }

                    for (int a = 0; a < tk1.Primeros.size(); a++) {
                        tk.Primeros.add(tk1.Primeros.get(a));
                    }
                    for (int a = 0; a < tk2.Primeros.size(); a++) {
                        tk.Primeros.add(tk2.Primeros.get(a));
                    }
                    for (int a = 0; a < tk1.Ultimos.size(); a++) {
                        tk.Ultimos.add(tk1.Ultimos.get(a));
                    }
                    for (int a = 0; a < tk2.Ultimos.size(); a++) {
                        tk.Ultimos.add(tk2.Ultimos.get(a));
                    }

                } else if (tk0.lexema.equals(".")) {
                    if (tk1.anulable && tk2.anulable) {
                        for (int a = 0; a < ListaNodos2.size(); a++) {
                            Nodos tk = ListaNodos2.get(a);
                            if (tk0.Nodo.equals(tk.Nodo)) {
                                tk.anulable = true;
                                break;
                            }
                        }
                    }

                    Nodos tk = null;
                    for (int a = 0; a < ListaNodos2.size(); a++) {
                        tk = ListaNodos2.get(a);
                        if (tk0.Nodo.equals(tk.Nodo)) {
                            break;
                        }
                    }

                    for (int a = 0; a < tk1.Primeros.size(); a++) {
                        tk.Primeros.add(tk1.Primeros.get(a));
                    }
                    if (tk1.anulable) {
                        for (int a = 0; a < tk2.Primeros.size(); a++) {
                            tk.Primeros.add(tk2.Primeros.get(a));
                        }
                    }
                        
                    for (int a = 0; a < tk2.Ultimos.size(); a++) {
                        tk.Ultimos.add(tk2.Ultimos.get(a));
                    }
                    if (tk2.anulable) {
                        for (int a = 0; a < tk1.Ultimos.size(); a++) {
                            tk.Ultimos.add(tk1.Ultimos.get(a));
                        }
                    }
                    
                    for(int a=0;a<tk1.Ultimos.size();a++){
                        Nodos tkplus=null;
                        for(int c=0;c<ListaNodos2.size();c++){
                            if((int)tk1.Ultimos.get(a)==ListaNodos2.get(c).Hoja){
                                tkplus=ListaNodos2.get(c);
                                break;
                            }
                        }
                        for(int b=0;b<tk2.Primeros.size();b++){
                            tkplus.Siguientes.add(tk2.Primeros.get(b));
                        }
                    }
                    
                }
                String ult="[ ",prim="[ ";
                for(int a=0;a<tk0.Primeros.size();a++){
                    prim+=tk0.Primeros.get(a)+" ,";
                }
                for(int a=0;a<tk0.Ultimos.size();a++){
                    ult+=tk0.Ultimos.get(a)+" ,";
                }
                ult = ult.substring(0, ult.length()-1);
                prim = prim.substring(0, prim.length()-1);
                ult+=" ]";prim+=" ]";
                contenido += tk0.Nodo + "[label=\""+prim+"|{"+tk0.anulable+"|" + tk0.lexema + "}|"+ult+"\"]\n";
                
                x = 0;
                y = 1;
                z = 2;
            } else if ((tk0.getLexema().equals("?") || tk0.getLexema().equals("+") || tk0.getLexema().equals("*")) && (!tk1.getOperador()) && (!tk0.leido)) {
                System.out.println("");
                

                tk0.operador = false;
                tk0.leido = true;
                tk1.leido = true;
                ListaNodos.remove(y);
                
                contenido += tk0.Nodo + "->" + tk1.Nodo + "\n";

                ultimo = tk0.Nodo;

                if (tk0.lexema.equals("+")) {
                    if (tk1.anulable) {
                        for (int a = 0; a < ListaNodos2.size(); a++) {
                            Nodos tk = ListaNodos2.get(a);
                            if (tk0.Nodo.equals(tk.Nodo)) {
                                tk.anulable = true;
                                break;
                            }
                        }
                    }
                    
                    
                }

                Nodos tk = null;
                for (int a = 0; a < ListaNodos2.size(); a++) {
                    tk = ListaNodos2.get(a);
                    if (tk0.Nodo.equals(tk.Nodo)) {
                        break;
                    }
                }

                for (int a = 0; a < tk1.Primeros.size(); a++) {
                    tk.Primeros.add(tk1.Primeros.get(a));
                }
                for (int a = 0; a < tk1.Ultimos.size(); a++) {
                    tk.Ultimos.add(tk1.Ultimos.get(a));
                }

                if(tk0.lexema.equals("+")||tk0.lexema.equals("*")){
                    for(int a=0;a<tk1.Ultimos.size();a++){
                        Nodos tkplus=null;
                        for(int c=0;c<ListaNodos2.size();c++){
                            if((int)tk1.Ultimos.get(a)==ListaNodos2.get(c).Hoja){
                                tkplus=ListaNodos2.get(c);
                                break;
                            }
                        }
                        for(int b=0;b<tk1.Primeros.size();b++){
                            tkplus.Siguientes.add(tk1.Primeros.get(b));
                        }
                    }
                }
                
                
                x = 0;
                y = 1;
                z = 2;
                
                String ult="[ ",prim="[ ";
                for(int a=0;a<tk0.Primeros.size();a++){
                    prim+=tk0.Primeros.get(a)+" ,";
                }
                for(int a=0;a<tk0.Ultimos.size();a++){
                    ult+=tk0.Ultimos.get(a)+" ,";
                }
                ult = ult.substring(0, ult.length()-1);
                prim = prim.substring(0, prim.length()-1);
                ult+=" ]";prim+=" ]";
                contenido += tk0.Nodo + "[label=\""+prim+"|{"+tk0.anulable+"|" + tk0.lexema + "}|"+ult+"\"]\n";
                
                
            } else if ((tk1.getLexema().equals("?") || tk1.getLexema().equals("+") || tk1.getLexema().equals("*")) && (!tk2.getOperador()) && (!tk1.leido)) {
                System.out.println("");
                

                ultimo = tk1.Nodo;

                tk1.operador = false;
                tk1.leido = true;
                tk2.leido = true;
                ListaNodos.remove(z);
                contenido += tk1.Nodo + "->" + tk2.Nodo + "\n";

                if (tk1.lexema.equals("+")) {
                    if (tk2.anulable) {
                        for (int a = 0; a < ListaNodos2.size(); a++) {
                            Nodos tk = ListaNodos2.get(a);
                            if (tk1.Nodo.equals(tk.Nodo)) {
                                tk.anulable = true;
                                break;
                            }
                        }
                    }
                }

                Nodos tk = null;
                for (int a = 0; a < ListaNodos2.size(); a++) {
                    tk = ListaNodos2.get(a);
                    if (tk1.Nodo.equals(tk.Nodo)) {
                        break;
                    }
                }

                for (int a = 0; a < tk2.Primeros.size(); a++) {
                    tk.Primeros.add(tk2.Primeros.get(a));
                }
                for (int a = 0; a < tk2.Ultimos.size(); a++) {
                    tk.Ultimos.add(tk2.Ultimos.get(a));
                }
                
                String ult="[ ",prim="[ ";
                for(int a=0;a<tk1.Primeros.size();a++){
                    prim+=tk1.Primeros.get(a)+" ,";
                }
                for(int a=0;a<tk1.Ultimos.size();a++){
                    ult+=tk1.Ultimos.get(a)+" ,";
                }
                ult = ult.substring(0, ult.length()-1);
                prim = prim.substring(0, prim.length()-1);
                ult+=" ]";prim+=" ]";
                contenido += tk1.Nodo + "[label=\""+prim+"|{"+tk1.anulable+"|" + tk1.lexema + "}|"+ult+"\"]\n";
                
                if(tk1.lexema.equals("+")||tk1.lexema.equals("*")){
                    for(int a=0;a<tk2.Ultimos.size();a++){
                        Nodos tkplus=null;
                        for(int c=0;c<ListaNodos2.size();c++){
                            if((int)tk2.Ultimos.get(a)==ListaNodos2.get(c).Hoja){
                                tkplus=ListaNodos2.get(c);
                                break;
                            }
                        }
                        
                        for(int b=0;b<tk2.Primeros.size();b++){
                            System.out.println(b);
                            tkplus.Siguientes.add(tk2.Primeros.get(b));
                        }
                    }
                }
                
                
                x = 0;
                y = 1;
                z = 2;
            } else {
                x++;
                y++;
                z++;
            }

        }
        Hoja++;
        
        Nodos nuevo=new Nodos("EOF","$",false,false,Hoja);
        nuevo.Primeros.add(Hoja);
        nuevo.Ultimos.add(Hoja);
        ListaNodos2.add(nuevo);
        
        String ult = "["+Hoja+"]"; String prim = "["+Hoja+"]";

        contenido += "EOF[label=\""+prim+"|{false|$|"+nuevo.Hoja+"}|"+ult+"\"];\n";
        
        
        nuevo=new Nodos("SPuntoFinal",".",true,false);
        nuevo.Ultimos.add(Hoja);
        
        Nodos tk = null;
        for (int a = 0; a < ListaNodos2.size(); a++) {
            tk = ListaNodos2.get(a);
            if (ultimo.equals(tk.Nodo)) {
                break;
            }
        }

        for (int a = 0; a < tk.Primeros.size(); a++) {
            nuevo.Primeros.add(tk.Primeros.get(a));
        }
        if (tk.anulable) {
            nuevo.Primeros.add(Hoja);
        }
        
        ListaNodos2.add(nuevo);
        
        for (int a = 0; a < tk.Ultimos.size(); a++) {
            Nodos tkplus = null;
            for (int c = 0; c < ListaNodos2.size(); c++) {
                if ((int) tk.Ultimos.get(a) == ListaNodos2.get(c).Hoja) {
                    tkplus = ListaNodos2.get(c);
                    break;
                }
            }
                tkplus.Siguientes.add(Hoja);
        }
        
        ult = "[ "; prim = "[ ";
        for (int a = 0; a < nuevo.Primeros.size(); a++) {
            prim += nuevo.Primeros.get(a) + " ,";
        }
        for (int a = 0; a < nuevo.Ultimos.size(); a++) {
            ult += nuevo.Ultimos.get(a) + " ,";
        }
        ult = ult.substring(0, ult.length() - 1);
        prim = prim.substring(0, prim.length() - 1);
        ult += " ]";
        prim += " ]";

        contenido += "SPuntoFinal[label=\""+prim+"|{false|.}|"+ult+"\"];\n";
        
        
        
        contenido += "SPuntoFinal->EOF\n"
                + "SPuntoFinal->" + ultimo + "\n";
        contenido += "}";
        
        for(int a=0;a<ListaNodos2.size();a++){
            if(ListaNodos2.get(a).lexema.equals(".")){
                ListaNodos2.get(a).operador=true;
            }else if(ListaNodos2.get(a).lexema.equals("+")){
                ListaNodos2.get(a).operador=true;
            }else if(ListaNodos2.get(a).lexema.equals("\\|")){
                ListaNodos2.get(a).operador=true;
            }else if(ListaNodos2.get(a).lexema.equals("*")){
                ListaNodos2.get(a).operador=true;
            }else if(ListaNodos2.get(a).lexema.equals("?")){
                ListaNodos2.get(a).operador=true;
            }
        }
        
        Nodos=ListaNodos2;
        Siguientes();
        return contenido;
    }
    
    public void Siguientes(){
        String contenido2 = "digraph G {\n";
        contenido2 += "label = \"TABLA SIGUIENTES PARA " + this.nombre + "\"\n";
        contenido2 += "a0 [label=<\n <TABLE cellspacing=\"0\" cellpadding=\"10\">\n"
                + "<TR>\n"
                + "<TD>LEXEMA</TD>\n"
                + "<TD>HOJA</TD>\n"
                + "<TD>SIGUIENTES</TD>\n"
                + "</TR>\n";
        
        for (int a = 0; a < Nodos.size(); a++) {
            if (!Nodos.get(a).getOperador()) {
                String sig="[ ";
                contenido2 += "<TR>\n"
                        + "<TD>"+Nodos.get(a).getLexema()+"</TD>\n"
                        + "<TD>"+Nodos.get(a).Hoja+"</TD>\n";
                for(int b=0;b<Nodos.get(a).Siguientes.size();b++){
                    sig+=Nodos.get(a).Siguientes.get(b)+" ,";
                }
                sig = sig.substring(0, sig.length() - 1);
                sig+="]";
                contenido2+= "<TD>"+sig+"</TD>\n";
                contenido2+= "</TR>\n";

            }
        }
        contenido2+="</TABLE>>];\n}";
        System.out.println(contenido2);
        
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Token> getLista() {
        return Caracteres;
    }

}
