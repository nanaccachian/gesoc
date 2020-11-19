package com.testigos.gesoc.model.services.passwordValidator;

public class ValidadorContrasenia {

    private ValidadorContrasenia() {
    }

    public static boolean validarContrasenia(String contrasenia) {
        Archivo archivo = new Archivo();
        archivo.abrirArchivo();
        boolean noEstaEnArchivo = archivo.noEstaEnArchivo(contrasenia);
        archivo.cerrarArchivo();
        Requerimientos r = new Requerimientos();
        return (noEstaEnArchivo && r.cumpleRequerimientos(contrasenia));
    }
}
