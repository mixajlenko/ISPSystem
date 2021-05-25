package com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils;

public interface Operation {
    String LOGIN = "/view/login";
    String REGISTRATION = "/view/registration";
    String LOGOUT = "/view/logout";

    String ADMIN_MENU = "/view/admin/mainPageAdmin";
    String ADMIN_SERVICES = "/view/admin/servicePageAdmin";
    String ADMIN_USERS = "/view/admin/userPageAdmin";
    String ADMIN_MANAGE_PLAN = "/view/admin/managePlan";
    String ADMIN_MANAGE_USER = "/view/admin/manageUsers";
    String ADMIN_MANAGE_TARIFF = "/view/admin/manageTariff";

    String CLIENT_MENU = "/view/client/mainPageUser";
    String CLIENT_SERVICES = "/view/client/servicePage";
    String CHANGE_PASS = "/view/client/changePassPage";
    String SUPPORT_PAGE = "/view/client/supportPage";
    String PAYMENT_SYSTEM_PAGE = "/view/client/paymentSystemPage";
}
