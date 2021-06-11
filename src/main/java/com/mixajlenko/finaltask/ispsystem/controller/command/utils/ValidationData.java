package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

public class ValidationData {

    private ValidationData() {
    }

    public static boolean isPasswordValid(String password) {
        final var regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,50}$";
        var pattern = java.util.regex.Pattern.compile(regex);
        var matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        final var regex = "^(.+)@(.+)$";
        var pattern = java.util.regex.Pattern.compile(regex);
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
