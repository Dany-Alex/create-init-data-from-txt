/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.cargadatosexistentestxt;

import javax.swing.JFileChooser;

/**
 *
 * @author Artist
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        manejadorArchivosTxt manejadorArchivosTxt = new manejadorArchivosTxt(new JFileChooser());
        manejadorArchivosTxt.openFile();
        manejadorArchivosTxt.imprimirArraylist();
        manejadorArchivosTxt.creatingInitData();

    }

}
