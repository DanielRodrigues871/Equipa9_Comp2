package com.upt.lp.portalestagios.util;

import com.upt.lp.portalestagios.entity.Utilizador;

public class SessaoUtil {

    private static Utilizador utilizadorLogado;

    /** Retorna o utilizador autenticado */
    public static Utilizador getUtilizadorLogado() {
        return utilizadorLogado;
    }

    /** Define diretamente o utilizador logado (usado no AuthController) */
    public static void setUtilizadorLogado(Utilizador utilizador) {
        utilizadorLogado = utilizador;
    }

    /** Método esperado pelos menus */
    public static void login(Utilizador utilizador) {
        utilizadorLogado = utilizador;
    }

    /** Método esperado pelos menus */
    public static void logout() {
        utilizadorLogado = null;
    }

    /** Indica se existe sessão ativa */
    public static boolean isLogado() {
        return utilizadorLogado != null;
    }
}
