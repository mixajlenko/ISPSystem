<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.dao.manager.UserDao" %>
<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory" %>
<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao" %>
<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.model.User" %><%--
  Created by IntelliJ IDEA.
  User: mixaj
  Date: 5/17/2021
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>MAIN PAGE USER</h1>
<% String username = request.getParameter("login"); %>
<a>Welcome, <%=username%>!</a>
</body>
</html>
