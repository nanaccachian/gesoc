package com.testigos.gesoc.model.services.passwordValidator;

public class ValidadorContrasenia {

    public static boolean validarContrasenia(String contrasenia) {
        return (Archivo.noEstaEnArchivo(contrasenia) && Requerimientos.cumpleRequerimientos(contrasenia));
    }
}
