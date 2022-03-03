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

    ArrayList<Expresiones> ExpNormales = new ArrayList<Expresiones>();
    ArrayList <Expresiones> ExpPolacas = new ArrayList <Expresiones>();
           
    Analizador_Lexico lexico;
    Analizador_Sintactico sintactico;
    ArrayList ExpRegex = new ArrayList();
    String contenido = "", NombreArchivo;
    boolean Errores = true;
    public static LinkedList<Token> CONJUNTOS;
    public static LinkedList<Token> EXPRESIONES;
    public static LinkedList<Token> PRUEBAS;
    public static LinkedList<TError> ErroresLex;
    public static LinkedList<TError> ErroresSintact;
    public static LinkedList<String> CARACTERES;

    ArrayList <String> arboles=new ArrayList <String>();
    ArrayList <String> siguientes=new ArrayList <String>();
    ArrayList <String> automata=new ArrayList <String>();
    ArrayList <String> transicion=new ArrayList <String>();
    
    JScrollPane scroll = new JScrollPane();
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
            JSON();
        } else if (e.getSource() == bGuardarComo) {
            GuardarComo();
        } else if (e.getSource() == GenAutomata) {
            //GenerarAutomatasAFND();
            GenerarArbol();
        } else if (e.getSource() == bBack) {
            
        } else if (e.getSource() == bNext) {

        } else if (e.getSource() == bVisualizar) {

        }

    }
    
    public void JSON(){
        
    }
    
    public void GenerarArbol(){
        for(int x=0;x<ExpPolacas.size();x++){
            Dot(ExpPolacas.get(x).DotAFN(),"ARBOLES_202004734\\"+ExpPolacas.get(x).nombre+"_Arbol");
            DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(ExpPolacas.get(x).nombre);
            treee.add(arbol);
        }
    }
    

    public void GenerarAutomatasAFND() {
        int c = 0;
        while (c < ExpPolacas.size()) {
            Dot(ExpPolacas.get(c).DotAFND(),"AFND_202004734\\"+ExpPolacas.get(c).getNombre() + "_AFND");
            c++;
        }

    }

    public static void Dot(String contenido, String Clave) {
        ProcessBuilder pbuilder;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\" + Clave + ".dot"));
            bw.write(contenido);
            bw.close();
            pbuilder = new ProcessBuilder("dot", "-Tpng", "-o", "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\" + Clave + ".png", "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\" + Clave + ".dot");
            pbuilder.redirectErrorStream(true);
            pbuilder.start();
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void GuardarComo() {
        String contenidoNuevo = ta.getText();
        String NombreNuevo = JOptionPane.showInputDialog(this, "INGRESA NOMBRE DE ARCHIVO", "GUARDAR COMO", JOptionPane.INFORMATION_MESSAGE);;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(NombreNuevo + ".exp"));
            bw.write(contenidoNuevo);
            bw.close();
            JOptionPane.showMessageDialog(this, "ARCHIVO GUARDADO", "GUARDAR", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Guardar() {
        String contenidoNuevo = ta.getText();
        String NombreNuevo = "";
        if (contenido == "") {
            NombreNuevo = JOptionPane.showInputDialog(this, "INGRESA NOMBRE DE ARCHIVO", "GUARDAR COMO", JOptionPane.INFORMATION_MESSAGE);;
        }
        try {
            BufferedWriter bw = null;
            if (contenido != "") {
                bw = new BufferedWriter(new FileWriter("" + archivo));
            } else {
                bw = new BufferedWriter(new FileWriter("" + NombreNuevo + ".exp"));
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
                Errores = true;
            } else {
                Errores = false;
                ErroresLex = lexico.errores;
                ErroresSintact = sintactico.errores;
                CONJUNTOS = sintactico.CONJUNTOS;
                EXPRESIONES = sintactico.EXPRESIONES;
                PRUEBAS = sintactico.PRUEBAS;
                GuardarPolaca();
                Transformar();
                NombreArchivo = archivo.getName();
                JOptionPane.showMessageDialog(this, "ANALISIS COMPLETADO", "SIN ERRORES ENCONTRADOS", WARNING_MESSAGE);
                
            }
        } catch (Exception e) {
        }
    }

    public void GuardarPolaca() {
        String Nombre = "";
        int c = 0, i = 0;
        ArrayList<Token> Polaca = new ArrayList<Token>();

        while (c < EXPRESIONES.size()) {

            Token aux = EXPRESIONES.get(c);

            if (aux.getTipo() == "id") {
                Nombre = aux.getLexema();
            }
            i = 0;
            if (aux.getSize() > 0) {
                while (aux.getSize() > i) {
                    Polaca.add(0, aux.getCaracter(i));
                    i++;
                }
                Expresiones nuevoPol = new Expresiones(Nombre, Polaca);
                Polaca = new ArrayList<Token>();
                ExpPolacas.add(nuevoPol);

            }
            c++;
        }
        
        for(int x=0;x<ExpPolacas.size();x++){
            for(int y=0;x<ExpPolacas.get(x).Caracteres.size();y++){
                System.out.println(ExpPolacas.get(x).Caracteres.get(y).getLexema());
            }
            System.out.println("\n");
        }
    }
    
    public void Transformar() {
        int i = 0, estado = 0, NumOperadores = 0, cMovidos = 0, c = 0;
        ArrayList<TokenCaracterCambio> Expresion = new ArrayList<TokenCaracterCambio>();
        String Nombre="";
        
        
        

        
        while (c < EXPRESIONES.size()) {

            Token aux = EXPRESIONES.get(c);
            if (aux.getTipo() == "id") {
                Nombre = aux.getLexema();
            }
            i = 0;
            if (aux.getSize() > 0) {
                int operador = 0;
                while (aux.getSize() > i) {
                    TokenCaracterCambio nuevo = new TokenCaracterCambio(aux.getCaracter(i), false, i);
                    Expresion.add(0, nuevo);
                    i++;
                }
                
                i = 0;
            }

            int x = 0, y = 1, z = 2;

            while (z < Expresion.size()) {

                TokenCaracterCambio tk0 = Expresion.get(x);
                TokenCaracterCambio tk1 = Expresion.get(y);
                TokenCaracterCambio tk2 = Expresion.get(z);

                if (tk0.Caracter.getOperador() && !tk1.Caracter.getOperador() && !tk2.Caracter.getOperador() && (!tk0.Caracter.getLexema().equals("?") && !tk0.Caracter.getLexema().equals("+") && !tk0.Caracter.getLexema().equals("*"))) {
                    System.out.println("");
                    System.out.println("APLICO CASO 1");

                    int AuxX = x;
                    int conta = Expresion.get(AuxX).Elem + 1;
                    TokenCaracterCambio tkConta = Expresion.get(AuxX);
                    do {
                        tkConta.cambio = true;
                        AuxX++;
                        tkConta = Expresion.get(AuxX);
                        tkConta.SetElem(Expresion.get(AuxX).Elem - 1);
                    } while (tkConta.cambio && conta == Expresion.get(AuxX).Elem);
                    Expresion.remove(x);
                    tk0.cambio = true;
                    Expresion.add(AuxX, tk0);
                    tk1.cambio = true;
                    tk2.cambio = true;

                    Expresion.get(AuxX - 1).Caracter.setLexema(Expresion.get(AuxX - 1).Caracter.getLexema() + " " + Expresion.get(AuxX).Caracter.getLexema());
                    Expresion.remove(AuxX);
                    Expresion.get(AuxX - 1).Caracter.setLexema(Expresion.get(AuxX - 1).Caracter.getLexema() + " " + Expresion.get(AuxX).Caracter.getLexema());
                    Expresion.remove(AuxX);
                    AuxX++;
                    while (AuxX < Expresion.size()) {
                        Expresion.get(AuxX).SetElem(Expresion.get(AuxX).Elem - 2);
                        AuxX++;
                    }

                    x = 0;
                    y = 1;
                    z = 2;
                } else if ((tk0.Caracter.getLexema().equals("?") || tk0.Caracter.getLexema().equals("+") || tk0.Caracter.getLexema().equals("*")) && (!tk1.Caracter.getOperador()) && (!tk0.cambio)) {
                    System.out.println("");
                    System.out.println("APLICO CASO 2");

                    int conta = 0, AuxX = x;
                    TokenCaracterCambio tkConta = Expresion.get(AuxX);
                    do {
                        tkConta.cambio = true;
                        AuxX++;
                        tkConta = Expresion.get(AuxX);
                        tkConta.SetElem(Expresion.get(AuxX).Elem - 1);
                    } while (tkConta.cambio && conta == Expresion.get(AuxX).Elem);
                    Expresion.remove(x);
                    Expresion.get(AuxX).SetElem(Expresion.get(AuxX).Elem - 1);
                    Expresion.get(x).Caracter.setLexema("(" + Expresion.get(x).Caracter.getLexema());

                    tk0.cambio = true;
                    Expresion.add(AuxX, tk0);
                    Expresion.get(AuxX).Caracter.setLexema(")" + Expresion.get(AuxX).Caracter.getLexema());
                    tk1.cambio = true;
                    Expresion.get(AuxX - 1).Caracter.setLexema(Expresion.get(AuxX - 1).Caracter.getLexema() + Expresion.get(AuxX).Caracter.getLexema());
                    Expresion.remove(AuxX);

                    AuxX++;
                    while (AuxX < Expresion.size()) {
                        Expresion.get(AuxX).SetElem(Expresion.get(AuxX).Elem - 1);
                        AuxX++;
                    }
                    

                    x = 0;
                    y = 1;
                    z = 2;
                } else if ((tk1.Caracter.getLexema().equals("?") || tk1.Caracter.getLexema().equals("+") || tk1.Caracter.getLexema().equals("*")) && (!tk2.Caracter.getOperador()) && (!tk1.cambio)) {
                    System.out.println("");
                    System.out.println("APLICO CASO 3");

                    int AuxY = y;
                    int conta = Expresion.get(AuxY).Elem + 1;
                    TokenCaracterCambio tkConta = Expresion.get(AuxY);
                    do {
                        tkConta.cambio = true;
                        AuxY++;
                        tkConta = Expresion.get(AuxY);
                        tkConta.SetElem(Expresion.get(AuxY).Elem - 1);
                    } while (tkConta.cambio && conta == Expresion.get(AuxY).Elem);

                    Expresion.remove(y);
                    Expresion.get(y).Caracter.setLexema("(" + Expresion.get(y).Caracter.getLexema());
                    tk1.cambio = true;
                    tk1.Caracter.setOperador(false);
                    Expresion.add(AuxY, tk1);
                    Expresion.get(AuxY).SetElem(Expresion.get(AuxY).Elem - 1);
                    Expresion.get(AuxY - 1).Caracter.setLexema(Expresion.get(AuxY - 1).Caracter.getLexema() + ")");
                    Expresion.get(AuxY - 1).Caracter.setLexema(Expresion.get(AuxY - 1).Caracter.getLexema() + Expresion.get(AuxY).Caracter.getLexema());
                    Expresion.remove(AuxY);
                    tk2.cambio = true;
                    AuxY++;
                    while (AuxY < Expresion.size()) {
                        Expresion.get(AuxY).SetElem(Expresion.get(AuxY).Elem - 1);
                        AuxY++;
                    }

                    
                    System.out.println("");
                    x = 0;
                    y = 1;
                    z = 2;
                } else {
                    x++;
                    y++;
                    z++;
                }

            }
            System.out.println("FINAL");
            for (int a = 0; a < Expresion.size(); a++) {
                System.out.print(Expresion.get(a).Caracter.getLexema());
            }
            System.out.println("");
            Expresion.clear();
            c++;
        }

        System.out.println("TERMINE");
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
            int c = 0;
            for (int i = 0; i < lexico.errores.size(); i++) {
                pw.println("<tr>");

                pw.println("<td>" + i + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getLexema() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getLine() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getColumn() + "</td>");
                pw.println("<td>" + lexico.errores.get(i).getDescripcion() + "</td>");
                pw.println("<td>LEXICO</td>");
                pw.println("</tr>");
                c = i;
            }
            for (int i = 0; i < lexico.errores.size(); i++) {
                System.out.println(lexico.errores.get(i).show());
            }
            for (int i = 0; i < sintactico.errores.size(); i++) {
                c++;
                pw.println("<tr>");
                pw.println("<td>" + c + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getLexema() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getLine() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getColumn() + "</td>");
                pw.println("<td>" + sintactico.errores.get(i).getDescripcion() + "</td>");
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
        Errores = true;
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
