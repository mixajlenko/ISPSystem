<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<meta charset="utf-8">

<head>
    <title>404</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/404.css" media="screen">
</head>
<body>
<figure>
    <div class="sad-mac"></div>
    <figcaption>
        <span class="sr-text">Error 404: Not Found BACK</span>
        <span class="e hoverColor"></span>
        <span class="r hoverColor"></span>
        <span class="r hoverColor"></span>
        <span class="o hoverColor"></span>
        <span class="r hoverColor"></span>
        <span class="_4 hoverColor"></span>
        <span class="_0 hoverColor"></span>
        <span class="_4 hoverColor"></span>
        <span class="n hoverColor"></span>
        <span class="o hoverColor"></span>
        <span class="t hoverColor"></span>
        <span class="f hoverColor"></span>
        <span class="o hoverColor"></span>
        <span class="u hoverColor"></span>
        <span class="n hoverColor"></span>
        <span class="d hoverColor"></span>
        <div>
            <c:if test="${user.role==0}">
                <a href="${pageContext.request.contextPath}/view/admin/mainPageAdmin" >
            </c:if>
            <c:if test="${user.role==1}">
                <a href="${pageContext.request.contextPath}/view/client/mainPageUser">
            </c:if>
                <span class="b"></span>
                <span class="a"></span>
                <span class="c"></span>
                <span class="k"></span>
            </a>
        </div>
    </figcaption>
</figure>
</body>
</html>
