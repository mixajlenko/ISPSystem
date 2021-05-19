<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.dao.IUserDao" %>
<%@ page import="com.mixajlenko.epam.finaltask.ispsystem.dao.factory.DaoFactory" %><%--
  Created by IntelliJ IDEA.
  User: mixaj
  Date: 5/18/2021
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h1>Connection test</h1>
  <%
    DaoFactory daoFactory = DaoFactory.getInstance();
    IUserDao userDao = daoFactory.getUserDao();
  %>
  <%=
  userDao.getAll()
  %>
</head>
<body>

</body>
</html>
