package com.pwc.core;

public class DB {

    private String password = "ThisIsOldPassword#1";

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean checkPasswordAgainstDBValue(String password) {
        return this.password.equals(password);
    }

    public boolean isPasswordSimilarToOldPassword(String newPassword) {
        return true;
    }
}
