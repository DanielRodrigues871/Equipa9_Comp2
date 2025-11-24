package com.upt.pt.api.security;

public class NifUtils {

    /**
     * Valida se o NIF português fornecido é válido.
     * 
     * @param nif String com 9 dígitos
     * @return true se for válido, false se não
     */
    public static boolean isNifValido(String nif) {
        if (nif == null || !nif.matches("\\d{9}")) {
            return false;
        }
        int soma = 0;
        for (int i = 0; i < 8; i++) {
            soma += Character.getNumericValue(nif.charAt(i)) * (9 - i);
        }
        int digitoVerificador = 11 - (soma % 11);
        if (digitoVerificador >= 10) {
            digitoVerificador = 0;
        }
        return digitoVerificador == Character.getNumericValue(nif.charAt(8));
    }

    // Opcional: Método que lança exceção se inválido
    public static void validarNifOuExcecao(String nif) {
        if (!isNifValido(nif)) {
            throw new IllegalArgumentException("NIF inválido!");
        }
    }
}