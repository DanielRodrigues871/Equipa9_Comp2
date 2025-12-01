package com.upt.pt.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordUtils {

    // regex password forte
    private static final String STRONG_PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$";

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private PasswordUtils() {
    }

    // Valida força
    public static void validarPasswordForte(String password) {
        if (password == null) {
            throw new IllegalArgumentException("A password é obrigatória.");
        }

        if (!password.matches(STRONG_PASSWORD_REGEX)) {
            throw new IllegalArgumentException(
                "A password deve ter pelo menos 8 caracteres, " +
                "uma letra maiúscula, um número e um carácter especial, sem espaços."
            );
        }
    }

    // Gera hash BCrypt
    public static String hashPassword(String plainPassword) {
        validarPasswordForte(plainPassword);
        return encoder.encode(plainPassword);
    }

    // Verifica password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return encoder.matches(plainPassword, hashedPassword);
    }
}
