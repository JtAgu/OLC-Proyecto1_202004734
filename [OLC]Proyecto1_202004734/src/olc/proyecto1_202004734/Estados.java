/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc.proyecto1_202004734;

import java.util.ArrayList;

/**
 *
 * @author justin
 */
public class Estados {

    String estado;
    ArrayList<Nodos> hojas;
    String id;
    int hoja;

    public Estados(String estado, String id) {
        this.estado = estado;
        hojas = new ArrayList<Nodos>();
        this.id = id;
    }

    public Estados(String estado, String id, int terminal) {
        this.estado = estado;
        hojas = new ArrayList<Nodos>();
        this.id = id;
        this.hoja = terminal;
    }

}
