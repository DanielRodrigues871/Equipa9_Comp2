package com.upt.pt.api.security;

public class TestPasswordHash {
    public static void main(String[] args) {
        String pwd = "Abc123!!";
        String hash = PasswordUtils.hashPassword(pwd);
        System.out.println(hash);
    }
}