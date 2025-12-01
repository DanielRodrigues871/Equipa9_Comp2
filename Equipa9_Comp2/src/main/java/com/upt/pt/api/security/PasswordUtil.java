package com.upt.pt.api.security;

public final class PasswordUtil {

    // regex:
    // - pelo menos 1 maiúscula: (?=.*[A-Z])
    // - pelo menos 1 dígito: (?=.*\\d)
    // - pelo menos 1 carácter especial: (?=.*[^A-Za-z0-9])
    // - sem espaços: (?=\\S+$)
    // - mínimo 8 caracteres: .{8,}
    private static final String STRONG_PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9])(?=\\S+$).{8,}$";

    private PasswordUtil() {
        // impedir instanciar
    }

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
}
