<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">

<head>
    <title><fmt:message key="iSPAdmin5"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/mainPageAdmin?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/mainPageAdmin?language=EN">
                    EN
                </a>
            </div>
            <p id="pageLogo">ISPAdmin</p>
            <div class="mainmenu">
                <ul>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/mainPageAdmin"><fmt:message key="mainPage"/></a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/servicePageAdmin"><fmt:message key="services"/></a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/userPageAdmin"><fmt:message key="clients"/></a></li>
                    <li><a class="scroll-animite logOutBtn" href="${pageContext.request.contextPath}/view/logout"><fmt:message key="logout"/></a></li>
                </ul>
            </div>
        </div>
        <div id="time"><span id="datetime"></span></div>
        <script src="${pageContext.request.contextPath}/styles/js/time.js"></script>

    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message key="mainPage"/></h1>
                            <br>
                            <p><fmt:message key="basicInfoAdmin"/></p>
                        </div>
                    </div>
                    <div class="userInfo">
                        <h4><fmt:message key="clientsCount"/> ${users}</h4>
                        <h4><fmt:message key="activeCount"/> ${active}</h4>
                        <h4><fmt:message key="blockedCount"/> ${blocked}</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>