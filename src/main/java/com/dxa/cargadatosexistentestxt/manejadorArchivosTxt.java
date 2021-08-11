/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dxa.cargadatosexistentestxt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Artist
 */
public class manejadorArchivosTxt {

    // private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Texto", "txt");
    public File archivo = null;
    private boolean abierto = false;//bandera de control para saber si se abrio un archivo
    private ArrayList contenido = new ArrayList();//almacena los registros leidos de *.txt
    private int index = 0; //lleva control del registro actualmente visible
    JFileChooser fileChooser;

    public manejadorArchivosTxt(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public void openFile() {
        //fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.archivo = fileChooser.getSelectedFile();
            readFile(this.archivo);
            this.abierto = true;
        }
    }

    /* Lee linea por linea un archivo de texto y almacena los registros
     * en un ArrayList segun orden de lectura
     * input: File
     */
    public boolean readFile(File fichero) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fichero));
            this.contenido.clear();
            String linea;
            while ((linea = reader.readLine()) != null) {
                this.contenido.add(linea);
            }
            //muestra el primer registro en la interfaz
            //Siguiente();
            reader.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return false;
    }

    public void imprimirArraylist() {
        for (int i = 0; i < contenido.size(); i++) {
            System.out.println(contenido.get(i) + "\n");
        }

    }

    /**
     * es la funcion que permite crear datos iniciales a partir de un arrayList
     * obtenido al leer el archivo txt, tambien realiza que la estructura de
     * entrada este escrita, que cumpla con el siguente formato
     * IDENTICADOR("string",int,"date")
     */
    public void creatingInitData() {
        if (this.archivo != null) {
            //incrementa en 1 la variable "index", si se supera el tamaño de lineas, vuelve a valor 1
            Iterator It = contenido.iterator();
            //comienza busqueda
            while (It.hasNext()) {
                String lineTmp = It.next().toString();//es una linea leida 
                String identifierTmp = "";
                String[] structureTmp;

                for (int i = 0; i < lineTmp.length(); i++) {
                    char charTmp = lineTmp.charAt(i);
                    if (Character.isLetter(charTmp) || charTmp == '_') {
                        identifierTmp += charTmp;
                        switch (identifierTmp) {
                            case "USUARIO":
                                System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                System.out.println("Identificador: " + identifierTmp);
                                if (lineTmp.charAt(identifierTmp.length()) == '(' && lineTmp.charAt(lineTmp.length() - 1) == ')') {

                                    structureTmp = lineTmp.substring(i + 2, lineTmp.length() - 1).split(",");

                                    if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                            && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')) {

                                        String nameUser = structureTmp[0].replace("\"", ""),
                                                passUser = structureTmp[1].replace("\"", "");
                                        int typeUser = Integer.parseInt(structureTmp[2]);

                                        System.out.println("datos: " + nameUser + " - " + passUser + " - " + typeUser);

                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }

                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;
                            case "PIEZA":
                                System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                System.out.println("Identificador: " + identifierTmp);
                                if (lineTmp.charAt(identifierTmp.length()) == '(' && lineTmp.charAt(lineTmp.length() - 1) == ')') {

                                    structureTmp = lineTmp.substring(i + 2, lineTmp.length() - 1).split(",");
                                    if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')) {

                                        String typePiece = structureTmp[0].replace("\"", "");
                                        double costPiece = Double.parseDouble(structureTmp[1]);

                                        System.out.println("datos: " + typePiece + " - " + costPiece);

                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }

                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;
                            case "MUEBLE":
                                System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                System.out.println("Identificador: " + identifierTmp);
                                if (lineTmp.charAt(identifierTmp.length()) == '(' && lineTmp.charAt(lineTmp.length() - 1) == ')') {

                                    structureTmp = lineTmp.substring(i + 2, lineTmp.length() - 1).split(",");
                                    if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')) {

                                        String nameFurniture = structureTmp[0].replace("\"", "");
                                        double priceFurniture = Double.parseDouble(structureTmp[1]);

                                        System.out.println("datos: " + nameFurniture + " - " + priceFurniture);

                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }

                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;
                            case "ENSAMBLE_PIEZAS":
                                System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                System.out.println("Identificador: " + identifierTmp);
                                if (lineTmp.charAt(identifierTmp.length()) == '(' && lineTmp.charAt(lineTmp.length() - 1) == ')') {

                                    structureTmp = lineTmp.substring(i + 2, lineTmp.length() - 1).split(",");

                                    if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                            && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')) {

                                        String nameFurniture = structureTmp[0].replace("\"", ""),
                                                typePiece = structureTmp[1].replace("\"", "");
                                        int amountPieces = Integer.parseInt(structureTmp[2]);

                                        System.out.println("datos: " + nameFurniture + " - " + typePiece + " - " + amountPieces);

                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }

                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;
                            case "ENSAMBLAR_MUEBLE":
                                System.out.println("+------------------< ESTRUCTURA ENCONTRADA >-------------------+");

                                System.out.println("Identificador: " + identifierTmp);
                                if (lineTmp.charAt(identifierTmp.length()) == '(' && lineTmp.charAt(lineTmp.length() - 1) == ')') {

                                    structureTmp = lineTmp.substring(i + 2, lineTmp.length() - 1).split(",");

                                    if ((structureTmp[0].charAt(0) == '"' && structureTmp[0].charAt(structureTmp[0].length() - 1) == '"')
                                            && (structureTmp[1].charAt(0) == '"' && structureTmp[1].charAt(structureTmp[1].length() - 1) == '"')
                                            && (structureTmp[2].charAt(0) == '"' && structureTmp[2].charAt(structureTmp[2].length() - 1) == '"')) {

                                        String nameFurniture = structureTmp[0].replace("\"", ""),
                                                nameUser = structureTmp[1].replace("\"", ""),
                                                assemblyDate = structureTmp[2].replace("\"", "");
                                        if (validateDate(assemblyDate)) {
                                            String assemblyDateFormat = dateFormat(assemblyDate);
                                            System.out.println("datos: " + nameFurniture + " - " + nameUser + " - " + assemblyDateFormat);

                                        } else {
                                            System.out.println("datos: " + nameFurniture + " - " + nameUser + " -  Error con el formato de fecha dd/MM/yyyy");

                                        }

                                        identifierTmp = "";
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    } else {
                                        System.out.println("la estructura no es correcta");
                                        System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                        identifierTmp = "";
                                    }

                                } else {
                                    System.out.println("la estructura no es correcta");
                                    System.out.println("+----------------------[ FIN ESTRUCTURA ]----------------------+");
                                    identifierTmp = "";
                                }
                                break;

                            default:
                            //System.out.println("no se logro capturar: identifierTmp = " + identifierTmp);

                        }

                    }

                }

            }

        }
    }

    /**
     *
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna true si la fecha recibida en el parametro es compatible
     * con el formato dd/MM/yyyy, de lo contrario retorna un false
     */
    public static boolean validateDate(String date) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(date);
        } catch (Exception e) {
            //System.out.println("e = " + e);
            return false;
        }
        return true;
    }

    /**
     * @param date Es la fecha obtenida en unda de las lineas del archivo txt,
     * con formato dd/MM/yyyy
     * @return retorna una fecha en formato yyyy-MM-dd, que es el formato
     * compatible con MYSQL
     */
    public String dateFormat(String date) {
        return ((date.substring(6)).concat(date.substring(2, 6)).concat(date.substring(0, 2))).replace('/', '-');
    }
}
