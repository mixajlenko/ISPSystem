package com.mixajlenko.epam.finaltask.ispsystem.controller.command.utils;

public interface Operation {
    String LOGIN = "/login";
    String REGISTRATION = "/view/registration";
    String LOGOUT = "/view/logout";

    String ADMIN_MENU = "/view/mainPageAdmin";
    String ADMIN_MANAGE_PLAN = "/view/admin/managePlan";
    String ADMIN_MANAGE_USER = "/view/admin/manageUsers";
}
