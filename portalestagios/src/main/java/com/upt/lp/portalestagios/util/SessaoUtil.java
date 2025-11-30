package com.upt.lp.portalestagios.util;

import com.upt.lp.portalestagios.entity.Utilizador;

public class SessaoUtil {

    private static Utilizador utilizadorLogado;

    public static Utilizador getUtilizadorLogado() {
        return utilizadorLogado;
    }

    public static void setUtilizadorLogado(Utilizador utilizador) {
        utilizadorLogado = utilizador;
    }

    public static boolean isLogado() {
        return utilizadorLogado != null;
    }
}

