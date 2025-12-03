package com.upt.lp.componente2.security;

public class TestPasswordHash {
    public static void main(String[] args) {
        String pwd = "Abc123!!";
        String hash = PasswordUtils.hashPassword(pwd);
        System.out.println(hash);
    }
}
