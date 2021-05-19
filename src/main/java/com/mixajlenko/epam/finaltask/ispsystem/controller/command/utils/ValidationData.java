package com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils;

public class ValidationData {

    public static boolean  isLoginValid(String login) {
        final String regex = "[A-Zа-яА-Яa-z0-9-]+";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(login);
        return m.matches();

    }

    public static boolean  isPasswordValid(String password) {
        final String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isEmailValid(String email){
        final String regex = "^(.+)@(.+)$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
