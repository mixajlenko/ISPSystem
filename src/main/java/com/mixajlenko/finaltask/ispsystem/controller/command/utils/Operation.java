package com.mixajlenko.finaltask.ispsystem.controller.command.utils;

public enum Operation {

    LOGIN("/view/login"),
    REGISTRATION("/view/registration"),
    LOGOUT("/view/logout"),

    ADMIN_MENU("/view/admin/mainPageAdmin"),
    ADMIN_SERVICES("/view/admin/servicePageAdmin"),
    ADMIN_USERS("/view/admin/userPageAdmin"),
    ADMIN_MANAGE_PLAN("/view/admin/managePlan"),
    ADMIN_MANAGE_USER("/view/admin/manageUsers"),
    ADMIN_MANAGE_TARIFF("/view/admin/addTariff"),

    CLIENT_MENU("/view/client/mainPageUser"),
    CLIENT_SERVICES("/view/client/servicePage"),
    CLIENT_MANAGE_PROFILE("/view/client/profile"),
    SUPPORT_PAGE("/view/client/supportPage"),
    PAYMENT_SYSTEM_PAGE("/view/client/paymentSystemPage"),
    DOWNLOAD_SERVICES("/view/client/downloadServices");

    private final String command;

    public String getCommand() {
        return command;
    }

    Operation(String command) {
        this.command = command;
    }
}
