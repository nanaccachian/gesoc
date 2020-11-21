package com.testigos.gesoc.model.services.passwordValidator;

import java.util.stream.IntStream;

public class Requerimientos {

    public static boolean cumpleRequerimientos(String contrasenia) {
        return (tiene8Caracteres(contrasenia) && noTieneCaracteresRepetidos(contrasenia)
                && noTieneCaracteresSecuenciales(contrasenia) && tieneUnaMayus(contrasenia)
                && tieneUnaMinus(contrasenia) && tieneUnNumero(contrasenia) && sonTodosUNICODE(contrasenia));
    }

    private static boolean tiene8Caracteres(String contrasenia) {
        return contrasenia.length() >= 8;
    }

    private static boolean noTieneCaracteresRepetidos(String contrasenia) {
        boolean noTiene = true;
        char[] vecContrasenia = contrasenia.toCharArray();
        for (int i = 0; i < contrasenia.length() - 2; i++) {
            if (vecContrasenia[i] == vecContrasenia[i + 1] && vecContrasenia[i + 1] == vecContrasenia[i + 2]) {
                noTiene = false;
            }
        }
        return noTiene;
    }

    private static boolean noTieneCaracteresSecuenciales(String contrasenia) {
        boolean noTiene = true;
        char[] vecContrasenia = contrasenia.toCharArray();
        for (int i = 0; i < contrasenia.length() - 2; i++) {
            if (vecContrasenia[i] == vecContrasenia[i + 1] - 1
                    && vecContrasenia[i + 1] - 1 == vecContrasenia[i + 2] - 2) {
                noTiene = false;
            }
        }
        return noTiene;
    }

    private static boolean tieneUnaMayus(String contrasenia) {
        IntStream vecContrasenia = contrasenia.chars();
        return vecContrasenia.anyMatch(Character::isUpperCase);
    }

    private static boolean tieneUnaMinus(String contrasenia) {
        IntStream vecContrasenia = contrasenia.chars();
        return vecContrasenia.anyMatch(Character::isLowerCase);
    }

    private static boolean tieneUnNumero(String contrasenia) {
        IntStream vecContrasenia = contrasenia.chars();
        return vecContrasenia.anyMatch(Character::isDigit);
    }

    private static boolean sonTodosUNICODE(String contrasenia) {
        IntStream vecContrasenia = contrasenia.chars();
        return vecContrasenia.allMatch(Character::isDefined);
    }
}
