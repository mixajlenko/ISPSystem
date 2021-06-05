<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">
<meta charset="utf-8">

<head>
    <title><fmt:message key="iSPAdmin2"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/manageUsers?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/manageUsers?language=EN">
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
                            <h1><fmt:message key="clientRegistration"/></h1>
                            <br>
                        </div>
                    </div>
                    <div class="newTariff">
                        <form method="post"
                              action="${pageContext.request.contextPath}/view/admin/manageUsers">
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="<fmt:message key="placeholderFirstName"/>"
                                           name="fName">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="<fmt:message key="placeholderSecondName"/>"
                                           name="sName">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="email" required placeholder="<fmt:message key="placeholderEmail"/>"
                                           name="email">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="<fmt:message key="placeholderPhone"/>"
                                           name="phone">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="password" required placeholder="<fmt:message key="password"/>"
                                           name="password">
                                </label>
                            </p>
                            <input id="submitBtn" type="submit" value="<fmt:message key="registration"/>">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>