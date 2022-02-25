/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.proyecto1_202004734;

import Analizadores.Analizador_Sintactico;
import Analizadores.Analizador_Lexico;
import Analizadores.TError;
/**
 *
 * @author justin
 */
import Analizadores.Analizador_Lexico;
import Analizadores.Analizador_Sintactico;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.*;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

public class Interfaz extends JFrame implements ActionListener {

    Analizador_Lexico lexico;
    Analizador_Sintactico sintactico;
    
    String contenido = "";
    boolean Errores=true;
    
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
        ta.setBounds(10, 80, 430, 270);
        ta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(ta);

        TaSalida = new JTextArea();
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

        } else if (e.getSource() == bJSON) {

        } else if (e.getSource() == bGuardarComo) {

        } else if (e.getSource() == GenAutomata) {

        } else if (e.getSource() == bBack) {

        } else if (e.getSource() == bNext) {

        } else if (e.getSource() == bVisualizar) {

        }

    }

    public void Analizar() {
        try {
            lexico = new Analizador_Lexico(new BufferedReader(new FileReader(archivo)));
            sintactico = new Analizador_Sintactico(lexico);
            sintactico.parse();
            System.out.println("\n\n***Reporte de errores encontrados ");

            if (lexico.errores.size() > 0 || sintactico.errores.size() > 0) {
                contenido = "";
                JOptionPane.showMessageDialog(this, "GENERANDO REPORTE DE ERRORES", "ERROR ENCONTRADO", WARNING_MESSAGE);
                ReporteErrores();
            }else{
                Errores=false;
                ObtenerDatos();
            }
        } catch (Exception e) {
        }
    }
    
    public void ObtenerDatos(){
        System.out.println("CONJUNTOS");
        for(int i=0;i<sintactico.CONJUNTOS.size();i++){
            System.out.println(sintactico.CONJUNTOS.get(i).getLexema());
        }
        System.out.println("PRUEBAS");
        for(int i=0;i<sintactico.PRUEBAS.size();i++){
            System.out.println(sintactico.PRUEBAS.get(i).getLexema());
        }
    }

    public void ReporteErrores() {
        FileWriter Reporte = null;
        PrintWriter pw = null;
        try {
            Reporte = new FileWriter("REPORTE DE ERRORES.html");
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
