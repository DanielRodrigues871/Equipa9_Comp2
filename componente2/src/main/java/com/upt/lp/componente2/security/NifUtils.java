package com.upt.lp.componente2.security;

public class NifUtils {
    
    public static boolean isNifValido(String nif) {
        if (nif == null || nif.length() != 9 || !nif.matches("\\d+")) {
            return false;
        }
        
        int[] nifArray = new int[9];
        for (int i = 0; i < 9; i++) {
            nifArray[i] = Character.getNumericValue(nif.charAt(i));
        }
        
        int soma = nifArray[0]*9 + nifArray[1]*8 + nifArray[2]*7 + 
                   nifArray[3]*6 + nifArray[4]*5 + nifArray[5]*4 + 
                   nifArray[6]*3 + nifArray[7]*2;
        
        int resto = soma % 11;
        int digitoControlo;
        
        if (resto == 0 || resto == 1) {
            digitoControlo = 0;
        } else {
            digitoControlo = 11 - resto;
        }
        
        return digitoControlo == nifArray[8];
    }
}