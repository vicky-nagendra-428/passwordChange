package com.pwc;

public class DB {

    private static String password = "ThisIsOldPassword#1";

    public void updatePassword(String newPassword) {
        password = newPassword;
    }

    public String getCurrentPassword() {
        return password;
    }
}
