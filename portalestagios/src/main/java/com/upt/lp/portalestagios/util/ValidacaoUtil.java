package com.upt.lp.portalestagios.util;

public class ValidacaoUtil {

    public static void validarNome(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O nome é obrigatório.");
    }

    public static void validarEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Email inválido.");
    }

    public static void validarPassword(String pass) {
        if (pass == null || pass.length() < 6)
            throw new IllegalArgumentException("Password deve ter pelo menos 6 caracteres.");
    }

    public static void validarUUID(String id) {
        try {
            java.util.UUID.fromString(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("UUID inválido.");
        }
    }
}

