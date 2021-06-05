package com.mixajlenko.finaltask.ispsystem.controller.command;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

    void execute(HttpServletRequest request, HttpServletResponse response);

}
