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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

public class Interfaz extends JFrame implements ActionListener {

    ArrayList<Expresiones> ExpNormales = new ArrayList<Expresiones>();
    ArrayList<Expresiones> ExpPolacas = new ArrayList<Expresiones>();

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

    ArrayList<String> arboles = new ArrayList<String>();
    ArrayList<String> siguientes = new ArrayList<String>();
    ArrayList<String> automata = new ArrayList<String>();
    ArrayList<String> transicion = new ArrayList<String>();
    ArrayList<String> thompson = new ArrayList<String>();

    JScrollPane scroll = new JScrollPane();
    JButton bAbrir, bGuardar, bGuardarComo, bJSON, GenAutomata, bAnalizar, bVisualizar, bBack, bNext;
    JTree TArchivos;
    DefaultMutableTreeNode treee, transition, next, Automatas;
    DefaultMutableTreeNode Archivo = new DefaultMutableTreeNode("Archivo");
    JTextArea ta, TaSalida;
    JLabel l1, l2, l3;
    JComboBox Imagenes;
    File archivo = null;

    int cArbol = 0, cSig = 0, cTrans = 0, cAuto = 0, cThom = 0;

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
        bVisualizar.setBounds(850, 10, 140, 35);
        bVisualizar.setBackground(new Color(171, 235, 198));
        bVisualizar.setVisible(true);
        bVisualizar.addActionListener(this);
        bVisualizar.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bVisualizar);

        bBack = new JButton("ANTERIOR");
        bBack.setBounds(700, 395, 140, 35);
        bBack.setBackground(new Color(171, 235, 198));
        bBack.setVisible(true);
        bBack.addActionListener(this);
        bBack.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bBack);

        bNext = new JButton("SIGUIENTE");
        bNext.setBounds(870, 395, 140, 35);
        bNext.setBackground(new Color(171, 235, 198));
        bNext.setVisible(true);
        bNext.addActionListener(this);
        bNext.setFont(new Font("arial", Font.BOLD, 14));
        this.add(bNext);

        //PROGRAMANDO TREE DE INTERFAZ
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
        sp.setBounds(480, 10, 175, 400);
        this.add(sp);

        //PROGRAMANDO LABEL DE INTERFAZ
        l1 = new JLabel("ARCHIVO DE ENTRADA");
        l1.setBounds(10, 50, 160, 30);
        l1.setVisible(true);
        l1.setFont(new Font("arial", Font.BOLD, 12));
        this.add(l1);

        l2 = new JLabel("ARCHIVO DE SALIDA");
        l2.setBounds(30, 410, 160, 30);
        l2.setVisible(true);
        l2.setFont(new Font("arial", Font.BOLD, 12));
        this.add(l2);

        l3 = new JLabel("");
        l3.setBounds(680, 50, 350, 335);
        l3.setVisible(true);
        l3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(l3);

        String[] list = {"Arboles", "Siguientes", "Transiciones", "Automatas", "Thompson"};
        Imagenes = new JComboBox(list);
        Imagenes.setBounds(690, 10, 140, 35);
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

        sp2.setBounds(65, 440, 930, 200);
        sp2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(sp2);

        bNext.setEnabled(false);
        bBack.setEnabled(false);
        bVisualizar.setEnabled(false);
        GenAutomata.setEnabled(false);
        bAnalizar.setEnabled(false);
        bJSON.setEnabled(false);

        this.setTitle("Log In DTT");
        this.setBounds(125, 10, 1080, 700);
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
            GenerarArbol();
            //GenerarAutomatasAFND();
            Transformar();
            bNext.setEnabled(true);
            bBack.setEnabled(true);
            bVisualizar.setEnabled(true);
            bJSON.setEnabled(true);
        } else if (e.getSource() == bBack) {
            AnteriorImagen();
        } else if (e.getSource() == bNext) {
            SiguienteImagen();
        } else if (e.getSource() == bVisualizar) {
            VerImagen();
        }
    }

    public void VerImagen() {
        cAuto = 0;
        cTrans = 0;
        cArbol = 0;
        cSig = 0;
        cThom = 0;

        String tipo = Imagenes.getSelectedItem().toString(), ruta = "";

        if (tipo.equals("Arboles")) {
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\ARBOLES_202004734\\" + arboles.get(0) + "_Arbol.png";
        } else if (tipo.equals("Automatas")) {
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFD_202004734\\" + automata.get(0) + "_AFD.png";
        } else if (tipo.equals("Siguientes")) {
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\SIGUIENTES_202004734\\" + siguientes.get(0) + "_TABLA_SIGUIENTES.png";
        } else if (tipo.equals("Transiciones")) {
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\TRANSICIONES_202004734\\" + transicion.get(0) + "_TABLA_TRANSICIONES.png";
        }

        ImageIcon img = new ImageIcon(ruta);
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(l3.getWidth(), l3.getHeight(), Image.SCALE_DEFAULT));
        l3.setIcon(icono);

    }

    public void SiguienteImagen() {
        String tipo = Imagenes.getSelectedItem().toString(), ruta = "";

        if (tipo.equals("Arboles")) {
            if ((cArbol + 1) < arboles.size()) {
                cArbol++;
            } else {
                cArbol = 0;
            }

            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\ARBOLES_202004734\\" + arboles.get(cArbol) + "_Arbol.png";
        } else if (tipo.equals("Automatas")) {
            if ((cAuto + 1) < automata.size()) {
                cAuto++;
            } else {
                cAuto = 0;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFD_202004734\\" + automata.get(cAuto) + "_AFD.png";
        } else if (tipo.equals("Siguientes")) {
            if ((cSig + 1) < siguientes.size()) {
                cSig++;
            } else {
                cSig = 0;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\SIGUIENTES_202004734\\" + siguientes.get(cSig) + "_TABLA_SIGUIENTES.png";
        } else if (tipo.equals("Transiciones")) {
            if ((cTrans + 1) < transicion.size()) {
                cTrans++;
            } else {
                cTrans = 0;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\TRANSICIONES_202004734\\" + transicion.get(cTrans) + "_TABLA_TRANSICIONES.png";
        } else {
            if ((cThom + 1) < thompson.size()) {
                cThom++;
            } else {
                cThom = 0;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFND_202004734\\" + thompson.get(cThom) + "_AFND.png";
        }

        ImageIcon img = new ImageIcon(ruta);
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(l3.getWidth(), l3.getHeight(), Image.SCALE_DEFAULT));
        l3.setIcon(icono);
    }

    public void AnteriorImagen() {
        String tipo = Imagenes.getSelectedItem().toString(), ruta = "";

        if (tipo.equals("Arboles")) {
            if ((cArbol - 1) > 0) {
                cArbol--;
            } else {
                cArbol = arboles.size() - 1;
            }

            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\ARBOLES_202004734\\" + arboles.get(cArbol) + "_Arbol.png";
        } else if (tipo.equals("Automatas")) {
            if ((cAuto - 1) > 0) {
                cAuto--;
            } else {
                cAuto = automata.size() - 1;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFD_202004734\\" + automata.get(cAuto) + "_AFD.png";
        } else if (tipo.equals("Siguientes")) {
            if ((cSig - 1) > 0) {
                cSig--;
            } else {
                cSig = siguientes.size() - 1;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\SIGUIENTES_202004734\\" + siguientes.get(cSig) + "_TABLA_SIGUIENTES.png";
        } else if (tipo.equals("Transiciones")) {
            if ((cTrans - 1) > 0) {
                cTrans--;
            } else {
                cTrans = transicion.size() - 1;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\TRANSICIONES_202004734\\" + transicion.get(cTrans) + "_TABLA_TRANSICIONES.png";
        } else {
            if ((cThom - 1) > 0) {
                cThom--;
            } else {
                cThom = thompson.size() - 1;
            }
            ruta = "C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\AFND_202004734\\" + thompson.get(cThom) + "_AFND.png";
        }

        ImageIcon img = new ImageIcon(ruta);
        Icon icono = new ImageIcon(img.getImage().getScaledInstance(l3.getWidth(), l3.getHeight(), Image.SCALE_DEFAULT));
        l3.setIcon(icono);
    }

    public void JSON() {
        String contendo2 = "{";
        String contendo = "";
        for (int a = 0; a < ExpNormales.size(); a++) {

            System.out.println(ExpNormales.get(a).Regex);
            String[] aux = ExpNormales.get(a).Regex.split(" ");
            for (int b = 0; b < aux.length; b++) {
                for (int c = 0; c < CONJUNTOS.size(); c++) {
                    if (aux[b].equals("" + CONJUNTOS.get(c).getTipo())) {
                        aux[b] = CONJUNTOS.get(c).getLexema();
                    }
                }
            }
            String regex = "";
            for (int b = 0; b < aux.length; b++) {
                if (!aux[b].equals(".")) {
                    if (!aux[b].equals("\"\\\"\"") && !aux[b].equals("\"\\\'\"")) {
                        aux[b] = aux[b].replace("\"", "");
                    }

                    if (aux[b].equals("\"\\\'\"")) {
                        aux[b] = "\\\\\\'";
                    } else if (aux[b].equals("\"\\\"\"")) {
                        aux[b] = "\\\\\\\"";
                    } else if (aux[b].equals("\\n")) {
                        aux[b] = "\\\\n";
                    }
                    regex += aux[b];
                }
            }
            System.out.println("\n");
            System.out.println(regex);
            String prueba = "";
            String nombre = "";
            String cad = "";
            for (int b = 1; b < PRUEBAS.size(); b++) {
                if (PRUEBAS.get(b - 1).getLexema().equals(ExpNormales.get(a).nombre)) {
                    nombre = PRUEBAS.get(b - 1).getLexema();
                    prueba += PRUEBAS.get(b).getLexema();

                    prueba = prueba.substring(0, prueba.length() - 1);
                    prueba = prueba.substring(1, prueba.length());

                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(prueba);
                    boolean matchFound = matcher.matches();
                    if (matchFound) {
                        cad = "CADENA VALIDA";
                    } else {
                        cad = "CADENA NO VALIDA";
                    }
                    contendo = "{\n";
                    contendo += "\"Valor\" :\"" + prueba + "\",\n";
                    contendo += "\"ExpresionRegular\" :\"" + ExpNormales.get(a).nombre + "\",\n";
                    contendo += "\"Resultado\" :\"" + cad + "\",\n";
                    contendo += "}\n";
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\justin\\Desktop\\USAC\\2022\\primerSemestre\\COMPI1\\OLC-Proyecto1_202004734\\[OLC]Proyecto1_202004734\\src\\SALIDAS_202004734\\" + ExpNormales.get(a).nombre + b + ".json"));
                        bw.write(contendo);
                        bw.close();
                        String as = "((((\"\\'\")*|[0-3])|((c)*|[4-5]))|(((e)*|\"\\\"\")|([8-9]|(h)+)))";
                    } catch (IOException ex) {
                        Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    contendo2 += contendo;
                    contendo = "";
                    prueba = "";
                }
            }

        }

        contendo2 += "}";
        TaSalida.setText(contendo2);
    }

    public void GenerarArbol() {
        for (int x = 0; x < ExpPolacas.size(); x++) {
            Dot(ExpPolacas.get(x).DotAFN(), "ARBOLES_202004734\\" + ExpPolacas.get(x).nombre + "_Arbol");
            DefaultMutableTreeNode arbol = new DefaultMutableTreeNode(ExpPolacas.get(x).nombre);
            treee.add(arbol);
            DefaultMutableTreeNode trans = new DefaultMutableTreeNode(ExpPolacas.get(x).nombre);
            transition.add(trans);
            DefaultMutableTreeNode sig = new DefaultMutableTreeNode(ExpPolacas.get(x).nombre);
            next.add(sig);
            DefaultMutableTreeNode afd = new DefaultMutableTreeNode(ExpPolacas.get(x).nombre + "_AFD");
            Automatas.add(afd);

            arboles.add(ExpPolacas.get(x).nombre);
            siguientes.add(ExpPolacas.get(x).nombre);
            transicion.add(ExpPolacas.get(x).nombre);
            automata.add(ExpPolacas.get(x).nombre);

            Archivo.remove(3);
            Archivo.remove(2);
            Archivo.remove(1);
            Archivo.remove(0);

            Archivo.add(treee);
            Archivo.add(transition);
            Archivo.add(next);
            Archivo.add(Automatas);

        }
    }

    public void GenerarAutomatasAFND() {
        int c = 0;
        while (c < ExpPolacas.size()) {
            Dot(ExpPolacas.get(c).DotAFND(), "AFND_202004734\\" + ExpPolacas.get(c).getNombre() + "_AFND");
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
                bw = new BufferedWriter(new FileWriter("" + archivo.getPath()));
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

            if (sintactico.errores.size() > 0) {
                contenido = "";
                JOptionPane.showMessageDialog(this, "GENERANDO REPORTE DE ERRORES", "ERROR ENCONTRADO", WARNING_MESSAGE);
                ReporteErrores();
                Errores = true;
            } else {
                ReporteErrores();
                Errores = false;
                ErroresLex = lexico.errores;
                ErroresSintact = sintactico.errores;
                CONJUNTOS = sintactico.CONJUNTOS;
                EXPRESIONES = sintactico.EXPRESIONES;
                PRUEBAS = sintactico.PRUEBAS;
                GuardarPolaca();

                JOptionPane.showMessageDialog(this, "ANALISIS COMPLETADO", "SIN ERRORES ENCONTRADOS", WARNING_MESSAGE);
                GenAutomata.setEnabled(true);
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
            System.out.println(c);
            c++;
        }

    }

    public void Transformar() {
        int i = 0, estado = 0, NumOperadores = 0, cMovidos = 0, c = 0;
        ArrayList<TokenCaracterCambio> Expresion = new ArrayList<TokenCaracterCambio>();
        String Nombre = "";

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
                    Expresion.get(AuxX - 1).Caracter.setLexema("( " + Expresion.get(AuxX - 1).Caracter.getLexema() + " " + Expresion.get(AuxX).Caracter.getLexema());
                    Expresion.remove(AuxX);
                    Expresion.get(AuxX - 1).Caracter.setLexema(Expresion.get(AuxX - 1).Caracter.getLexema() + " " + Expresion.get(AuxX).Caracter.getLexema() + " )");
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
                    Expresion.get(x).Caracter.setLexema("( " + Expresion.get(x).Caracter.getLexema());

                    tk0.cambio = true;
                    Expresion.add(AuxX, tk0);
                    Expresion.get(AuxX).Caracter.setLexema(" )" + Expresion.get(AuxX).Caracter.getLexema());
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
                    Expresion.get(y).Caracter.setLexema("( " + Expresion.get(y).Caracter.getLexema());
                    tk1.cambio = true;
                    tk1.Caracter.setOperador(false);
                    Expresion.add(AuxY, tk1);
                    Expresion.get(AuxY).SetElem(Expresion.get(AuxY).Elem - 1);
                    Expresion.get(AuxY - 1).Caracter.setLexema(Expresion.get(AuxY - 1).Caracter.getLexema() + " )");
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

            String s = "";
            for (int a = 0; a < Expresion.size(); a++) {
                System.out.println(Expresion.get(a).Caracter.getLexema());
                s = Expresion.get(a).Caracter.getLexema();
            }
            if (!s.equals("")) {
                System.out.println(s);
                Expresiones normal = new Expresiones(Nombre, s);
                ExpNormales.add(normal);
            }

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
                bAnalizar.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(this, "DEBE SELECCIONAR UN ARCHIVO", "ADVERTENCIA", WARNING_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
