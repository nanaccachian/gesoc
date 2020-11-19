package com.testigos.gesoc.model.services.passwordValidator;

import java.io.File;
import java.util.Scanner;

public class Archivo {

    private Scanner archivo;

    public void abrirArchivo() {
        try {
            archivo = new Scanner(new File("src/main/resources/OWASP_Top10k_mostCommon.txt").getAbsoluteFile());
        } catch (Exception e) {
            System.out.println("No se pudo encontrar archivo");
        }
    }

    public boolean noEstaEnArchivo(String contrasenia) {
        boolean noEstaEnArchivo = true;
        while (noEstaEnArchivo && archivo.hasNext()) {
            String a = archivo.next();
            noEstaEnArchivo = !(contrasenia.equals(a));
        }
        return noEstaEnArchivo;
    }

    public void cerrarArchivo() {
        archivo.close();
    }
}