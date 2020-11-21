package com.testigos.gesoc.model.services.passwordValidator;

import java.io.File;
import java.util.Scanner;

public class Archivo {

    private static Scanner archivo;

    public static void abrirArchivo() {
        try {
            String path = new File("com/testigos/gesoc/model/services/passwordValidator/OWASP_Top10k_mostCommon.txt").getAbsolutePath();
            archivo = new Scanner(path);
        } catch (Exception e) {
            System.out.println("No se pudo encontrar archivo");
        }
    }

    public static boolean noEstaEnArchivo(String contrasenia) {
        abrirArchivo();
        boolean noEstaEnArchivo = true;
        while (noEstaEnArchivo && archivo.hasNext()) {
            String a = archivo.next();
            noEstaEnArchivo = !(contrasenia.equals(a));
        }
        cerrarArchivo();
        return noEstaEnArchivo;
    }

    public static void cerrarArchivo() {
        archivo.close();
    }
}