/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.proyecto1_202004734;

import Analizadores.Analizador_Sintactico;
import Analizadores.Analizador_Lexico;
import Analizadores.TError;
import Analizadores.Token;
/**
 *
 * @author justin
 */
import javax.swing.JScrollPane;
import Analizadores.Analizador_Lexico;
import Analizadores.Analizador_Sintactico;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

public class Interfaz extends JFrame implements ActionListener {

    
    ArrayList <Expresiones> ExpNormales=new ArrayList <Expresiones>();
    ArrayList <Expresiones> ExpPolacas=new ArrayList <Expresiones> ();
    Analizador_Lexico lexico;
    Analizador_Sintactico sintactico;
    ArrayList ExpRegex=new ArrayList();
    String contenido = "",NombreArchivo;
    boolean Errores=true;
    public static LinkedList<Token> CONJUNTOS;
    public static LinkedList<Token> EXPRESIONES;
    public static LinkedList<Token> PRUEBAS;
    public static LinkedList<TError> ErroresLex;
    public static LinkedList<TError> ErroresSintact;
    public static LinkedList<String> CARACTERES;
    
    JScrollPane scroll =new JScrollPane();
    JButton bAbrir, bGuardar, bGuardarComo, bJSON, GenAutomata, bAnalizar, bVisualizar, bBack, bNext;
    JTree TArchivos;
    DefaultMutableTreeNode treee, transition, next, Automatas;
    JTextArea ta, TaSalida;
    JLabel l1, l2, l3;
    JComboBox Imagenes;
    File archivo = null;
    

    public Interfaz() {

        //PROGRAMANDO BOTONES DE INTERFAZ
        bAbrir = new JButton("CARGAR");
        bAbrir.setBounds(10, 10, 100, 35);
        bAbrir.setBackground(new Color(171, 235, 198));
        bAbrir.setVisible(true);
        bAbrir.addActionListener(this);
        bAbrir.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bAbrir);

        bGuardar = new JButton("GUARDAR");
        bGuardar.setBounds(120, 10, 110, 35);
        bGuardar.setBackground(new Color(171, 235, 198));
        bGuardar.setVisible(true);
        bGuardar.addActionListener(this);
        bGuardar.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bGuardar);

        bGuardarComo = new JButton("G. COMO");
        bGuardarComo.setBounds(240, 10, 100, 35);
        bGuardarComo.setBackground(new Color(171, 235, 198));
        bGuardarComo.setVisible(true);
        bGuardarComo.addActionListener(this);
        bGuardarComo.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bGuardarComo);

        bJSON = new JButton("JSON");
        bJSON.setBounds(350, 10, 90, 35);
        bJSON.setBackground(new Color(171, 235, 198));
        bJSON.setVisible(true);
        bJSON.addActionListener(this);
        bJSON.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bJSON);

        GenAutomata = new JButton("GENERAR AUTOMATA");
        GenAutomata.setBounds(40, 370, 180, 35);
        GenAutomata.setBackground(new Color(171, 235, 198));
        GenAutomata.setVisible(true);
        GenAutomata.addActionListener(this);
        GenAutomata.setFont(new Font("arial", Font.BOLD, 13));
        this.add(GenAutomata);

        bAnalizar = new JButton("ANALIZAR");
        bAnalizar.setBounds(230, 370, 180, 35);
        bAnalizar.setBackground(new Color(171, 235, 198));
        bAnalizar.setVisible(true);
        bAnalizar.addActionListener(this);
        bAnalizar.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bAnalizar);

        bVisualizar = new JButton("VISUALIZAR");
        bVisualizar.setBounds(800, 10, 140, 35);
        bVisualizar.setBackground(new Color(171, 235, 198));
        bVisualizar.setVisible(true);
        bVisualizar.addActionListener(this);
        bVisualizar.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bVisualizar);

        bBack = new JButton("ANTERIOR");
        bBack.setBounds(650, 365, 140, 35);
        bBack.setBackground(new Color(171, 235, 198));
        bBack.setVisible(true);
        bBack.addActionListener(this);
        bBack.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bBack);

        bNext = new JButton("SIGUIENTE");
        bNext.setBounds(800, 365, 140, 35);
        bNext.setBackground(new Color(171, 235, 198));
        bNext.setVisible(true);
        bNext.addActionListener(this);
        bNext.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bNext);

        //PROGRAMANDO TREE DE INTERFAZ
        DefaultMutableTreeNode Archivo = new DefaultMutableTreeNode("Archivo");
        treee = new DefaultMutableTreeNode("ARBOLES");
        next = new DefaultMutableTreeNode("SIGUIENTES");
        transition = new DefaultMutableTreeNode("TRANSICIONES");
        Automatas = new DefaultMutableTreeNode("AUTOMATAS");

        Archivo.add(treee);
        Archivo.add(next);
        Archivo.add(Automatas);
        Archivo.add(transition);

        TArchivos = new JTree(Archivo);
        JScrollPane sp = new JScrollPane(TArchivos);
        sp.setBounds(460, 10, 175, 400);
        this.add(sp);

        //PROGRAMANDO LABEL DE INTERFAZ
        l1 = new JLabel("ARCHIVO DE ENTRADA");
        l1.setBounds(10, 50, 160, 30);
        l1.setVisible(true);
        l1.setFont(new Font("arial", Font.BOLD, 12));
        this.add(l1);

        l2 = new JLabel("ARCHIVO DE SALIDA");
        l2.setBounds(10, 410, 160, 30);
        l2.setVisible(true);
        l2.setFont(new Font("arial", Font.BOLD, 12));
        this.add(l2);

        l3 = new JLabel("HOLA");
        l3.setBounds(650, 50, 290, 290);
        l3.setVisible(true);
        l3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(l3);

        String[] list = {"Arboles", "Siguientes", "Transiciones", "Automatas"};
        Imagenes = new JComboBox(list);
        Imagenes.setBounds(650, 10, 140, 35);
        Imagenes.setFont(new Font("arial", Font.BOLD, 12));
        Imagenes.setBackground(new Color(171, 235, 198));
        this.add(Imagenes);

        //PROGRAMANDO AREAS DE INTERFAZ
        ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        JScrollPane sp3 = new JScrollPane(ta);
        
        sp3.setBounds(10, 80, 430, 270);
        sp3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(sp3);

        TaSalida = new JTextArea();
        JScrollPane sp2 = new JScrollPane(TaSalida);
        TaSalida.setBounds(10, 440, 930, 170);
        TaSalida.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(TaSalida);

        this.setTitle("Log In DTT");
        this.setBounds(150, 80, 960, 650);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bAbrir) {

            if (contenido == "") {
                leerArchivos();
            } else {
                JOptionPane.showMessageDialog(this, "YA HAY UN ARCHIVO EXISTENTE", "ADVERTENCIA", WARNING_MESSAGE);
            }

        } else if (e.getSource() == bAnalizar) {
            
            if (contenido != "") {
                Analizar();
            } else {
                JOptionPane.showMessageDialog(this, "INGRESE ARCHIVO EXP", "ADVERTENCIA", WARNING_MESSAGE);
            }

        } else if (e.getSource() == bGuardar) {
            Guardar();
        } else if (e.getSource() == bJSON) {

        } else if (e.getSource() == bGuardarComo) {
            GuardarComo();
        } else if (e.getSource() == GenAutomata) {
            GenerarAutomatasAFND();
        } else if (e.getSource() == bBack) {

        } else if (e.getSource() == bNext) {

        } else if (e.getSource() == bVisualizar) {

        }

    }
    
    
    public void GenerarAutomatasAFND(){
        int c=0;
        while(c<ExpPolacas.size()){
            Dot(ExpPolacas.get(c).DotAFND(),ExpPolacas.get(c).getNombre()+"_AFND");
            c++;
        }
        
    }
    
    
      public static void Dot(String contenido, String Clave) {
        ProcessBuilder pbuilder;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFND_202004734\\"+Clave + ".dot"));
            bw.write(contenido);
            bw.close();
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFND_202004734\\"+Clave + ".png", "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFND_202004734\\"+Clave + ".dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void GuardarComo(){
        String contenidoNuevo=ta.getText();
        String NombreNuevo=JOptionPane.showInputDialog(this, "INGRESA NOMBRE DE ARCHIVO", "GUARDAR COMO", JOptionPane.INFORMATION_MESSAGE);;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(NombreNuevo+".exp"));
            bw.write(contenidoNuevo);
            bw.close();
            JOptionPane.showMessageDialog(this, "ARCHIVO GUARDADO", "GUARDAR", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    public void Guardar(){
        String contenidoNuevo=ta.getText();
        String NombreNuevo="";
        if (contenido==""){
            NombreNuevo=JOptionPane.showInputDialog(this, "INGRESA NOMBRE DE ARCHIVO", "GUARDAR COMO", JOptionPane.INFORMATION_MESSAGE);;
        }
        try {
            BufferedWriter bw=null;
            if(contenido!=""){
                 bw = new BufferedWriter(new FileWriter(""+archivo));
            }else{
                 bw = new BufferedWriter(new FileWriter(""+NombreNuevo+".exp"));
            }
            bw.write(contenidoNuevo);
            bw.close();
            JOptionPane.showMessageDialog(this, "ARCHIVO GUARDADO", "QUE GRANDE QUE SOS AGUA DE MEYAN", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Analizar() {
        try {
            lexico = new Analizador_Lexico(new BufferedReader(new FileReader(archivo)));
            sintactico = new Analizador_Sintactico(lexico);
            sintactico.parse();

            if (lexico.errores.size() > 0 || sintactico.errores.size() > 0) {
                contenido = "";
                JOptionPane.showMessageDialog(this, "GENERANDO REPORTE DE ERRORES", "ERROR ENCONTRADO", WARNING_MESSAGE);
                ReporteErrores();
                Errores=true;
            }else{
                Errores=false;
                ErroresLex=lexico.errores;
                ErroresSintact=sintactico.errores;
                CONJUNTOS=sintactico.CONJUNTOS;
                EXPRESIONES=sintactico.EXPRESIONES;
                PRUEBAS=sintactico.PRUEBAS;
                Transformar();
                NombreArchivo=archivo.getName();
            }
        } catch (Exception e) {
        }
    }
    
    public void Transformar(){
        int c=0;
        int i=0, estado=0,NumOperadores=0,adder=0;
        ArrayList  Expresion = new ArrayList(); 
        ArrayList ExpresionPrueba = new ArrayList(); 
        ArrayList ExpresionPolaca = new ArrayList(); 
        String LexemaActual="",Nombre="",LexemaAnterior="",LexemaGuardado="";
        while(c<EXPRESIONES.size()){
            
            Token aux=EXPRESIONES.get(c);
            if(aux.getTipo()=="id"){
                Nombre = aux.getLexema();
            }
            i = 0;
            if (aux.getSize() > 0) {
                int operador=0;
                while (aux.getSize() > i) {
                    TokenCaracterCambio nuevo=new TokenCaracterCambio(aux.getCaracter(i),false);
                    System.out.println(nuevo.Caracter.getLexema()+"   "+nuevo.Caracter.getOperador());
                    if(aux.getCaracter(i).getOperador()){
                        operador++;
                    }
                    Expresion.add(0,nuevo);
                    ExpresionPrueba.add(aux.getCaracter(i).getLexema());
                    i++;
                }
                i=0;
                
                while(operador>0){
                    TokenCaracterCambio actual=(TokenCaracterCambio)Expresion.get(i);
                    
                    if(actual.getOperador()&&actual.cambio==false){
                        System.out.println("Lexema: "+actual.getLexema()+"   "+actual.cambio);
                        int Espacios=0,i2=i;
                        
                        while (actual.getOperador()) {
                            if(actual.cambio==false){
                                Espacios++;    
                            }
                            i++;
                            actual = (TokenCaracterCambio) Expresion.get(i);
                        }
                        System.out.println("Espacios: "+Espacios);
                        i = i2;
                        if(i>=Expresion.size()){
                            i--;
                        }
                        System.out.println(i);
                        actual = (TokenCaracterCambio) Expresion.get(i);
                        if ((actual.getLexema().equals("*") || actual.getLexema().equals("?") || actual.getLexema().equals("+"))&&Espacios>1) {
                            Espacios++;
                        }
                        if(i+2<Expresion.size()){
                            TokenCaracterCambio sig=(TokenCaracterCambio)Expresion.get(i+2);
                        if(sig.Caracter.equals("|")&&sig.cambio==false){
                            Espacios++;
                        }
                        }
                        if(i+1<Expresion.size()){
                        TokenCaracterCambio sig=(TokenCaracterCambio)Expresion.get(i+1);
                        
                        if((sig.Caracter.getLexema().equals("*")||sig.Caracter.getLexema().equals("?")||sig.Caracter.getLexema().equals("+"))&&sig.cambio==false){
                                Espacios--;
                        }
                        
                        }
                        
                        System.out.println("Espacios: "+Espacios);
                        actual.cambio=true;
                        TokenCaracterCambio mover = actual;
                        System.out.println("Valor i : "+i);
                        Expresion.remove(i);
                        if(!mover.getLexema().equals("|")){
                            Expresion.add(i,new TokenCaracterCambio(new Token("ParentesisA","(",false),false));
                        }
                        
                        while (Espacios > 0) {
                            
                            if (actual.getTipo().equals("id") || actual.getTipo().equals("Cadena")) {
                                Espacios--;
                            }
                            System.out.println("Espacios en While: "+Espacios);
                            i++;
                            System.out.println("I: "+i+"     Expresion:"+Expresion.size());
                            if(i<Expresion.size()){
                                actual = (TokenCaracterCambio) Expresion.get(i);
                            }else{
                                break;
                            }
                            
                            
                        }
                        System.out.println("Espacios: "+Espacios);
                        System.out.println("Sali While");
                        System.out.println("Valor i : "+i);
                        if (mover.getLexema().equals("*") || mover.getLexema().equals("?") || mover.getLexema().equals("+")) {
                            
                            mover.cambio=true;
                            System.out.println(mover.cambio);
                            if(i+1>=Expresion.size()){
                                Expresion.add(i, mover);
                                Expresion.add(i,new TokenCaracterCambio(new Token("ParentesisC",")",false),false));
                            }else{
                                Expresion.add(i, mover);
                                Expresion.add(i,new TokenCaracterCambio(new Token("ParentesisC",")",false),false));
                            }
                                
                            
                            
                            operador--;
                            System.out.println("operador: "+operador);
                        } else {
                            
                            if(mover.getLexema().equals("|")){
                                
                                mover.cambio=true;
                                System.out.println(mover.cambio);
                                Expresion.add(i, mover);
                            }else{
                                mover.cambio=true;
                                System.out.println(mover.cambio);
                                Expresion.add(i, mover);
                                Expresion.add(i,new TokenCaracterCambio(new Token("ParentesisC",")",false),false));
                                
                            }
                            
                            operador--;
                            System.out.println("operador: "+operador);
                         
                        }
                        i = 0;
                        System.out.println("");
                        System.out.println("\n");
                        for (int z = 0; z < Expresion.size(); z++) {
                            TokenCaracterCambio Ver = (TokenCaracterCambio) Expresion.get(z);
                            System.out.println(Ver.Caracter.getLexema()+"   "+Ver.cambio);
                        }
                        for (int z = 0; z < Expresion.size(); z++) {
                            TokenCaracterCambio Ver = (TokenCaracterCambio) Expresion.get(z);
                            System.out.print(Ver.Caracter.getLexema());
                        }
                        System.out.println("\n");

                           
                    }else{
                        i++;
                    }
                    
                    
                    
                }
                
                
                i=0;
                while(aux.getSize()>i){
                    ExpresionPolaca.add(0,aux.getCaracter(i));
                    i++;
                }
                
            }
            
            c++;
            
            
            if(Expresion.size()>0){
                
                Expresiones exp=new Expresiones(Nombre,Expresion);
                ExpNormales.add(exp);
                exp=new Expresiones(Nombre,ExpresionPolaca);
                ExpPolacas.add(exp);
                Expresion=new ArrayList(); 
            }
            
        }
        
        for(int j=0;j<ExpNormales.size();j++){
            //Expresiones Exp=;
            System.out.println(ExpNormales.get(j).getNombre());
            for (int k=0;k<ExpNormales.get(j).getLista().size();k++){
                System.out.println("\t"+ExpNormales.get(j).getLista().get(k));
            }
            System.out.println(ExpPolacas.get(j).getNombre());
            for (int k=0;k<ExpPolacas.get(j).getLista().size();k++){
                System.out.println("\t"+ExpPolacas.get(j).getLista().get(k).getLexema());
            }
        }
        
        System.out.println("Termine");
    }
    
    

    public void ReporteErrores() {
        FileWriter Reporte = null;
        PrintWriter pw = null;
        try {
            Reporte = new FileWriter("C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\ERRORES_202004734\\REPORTE DE ERRORES.html");
            pw = new PrintWriter(Reporte);
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset=\"UTF-8\">");
            pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
            pw.println("<title> REPORTE DE ERRORES </title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<h1>  REPORTE ERRORES </h1>");
            pw.println("<table class=\"table table-dark table-hover\">");
            pw.println("<tbody>");

            pw.println("<td>Num</td>");
            pw.println("<td>Lexema</td>");
            pw.println("<td>Fila</td>");
            pw.println("<td>Columna</td>");
            pw.println("<td>Descripcion</td>");
            pw.println("<td>Tipo</td>");
            int c=0;
            for (int i = 0; i < lexico.errores.size(); i++) {
                pw.println("<tr>");

                pw.println("<td>" + i + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getLexema() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getLine() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getColumn() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getDescripcion()+ "</td>");
                pw.println("<td>LEXICO</td>");
                pw.println("</tr>");
                c=i;
            }
            for (int i = 0; i < lexico.errores.size(); i++) {
                System.out.println(lexico.errores.get(i).show());
            }
            for (int i = 0; i < sintactico.errores.size(); i++) {
                c++;
                pw.println("<tr>");
                pw.println("<td>" + c+ "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getLexema() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getLine() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getColumn() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getDescripcion()+ "</td>");
                pw.println("<td>SINTACTICO</td>");
                pw.println("</tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");
            pw.println("</center>");
            pw.println("</body>");
            pw.println("</HTML>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != Reporte) {
                    Reporte.close();
                    System.out.println("REPORTE ERRORES GENERADOS");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leerArchivos() {
        Errores=true;
        try {
            JFileChooser ruta = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.EXP", "exp");
            ruta.setFileFilter(filtro);
            int opcion = ruta.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                archivo = ruta.getSelectedFile();
                FileReader fr = new FileReader(archivo);
                BufferedReader bf = new BufferedReader(fr);

                String linea;
                while ((linea = bf.readLine()) != null) {
                    contenido += linea;
                }
                String contenido2 = contenido.replace("\t", "\n\t");
                ta.setText(contenido2);

            } else {
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN ARCHIVO", "ADVERTENCIA", WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
