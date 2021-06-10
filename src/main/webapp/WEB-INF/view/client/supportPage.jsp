<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="customTag" uri="/WEB-INF/customTag.tld" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">
<meta charset="utf-8">

<head>
    <title><fmt:message key="iSPManager5"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/client/supportPage?language=RU">
                    RU
                </a>
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/client/supportPage?language=EN">
                    EN
                </a>
            </div>
            <p id="pageLogo">ISPManager</p>
            <div class="mainmenu">
                <ul class="topChange">
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/mainPageUser"><fmt:message
                            key="mainPage"/></a></li>
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/servicePage"><fmt:message
                            key="services"/><i
                            class="fa fa-angle-down"></i></a>
                        <ul class="subChange">
                            <c:forEach items="${services}" var="item" varStatus="status">
                                <li>
                                    <a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item.name}&serviceId=${item.id}">${item.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/paymentSystemPage"><fmt:message
                            key="payments"/></a></li>
                    <li><a class="btn"
                           href="#"><fmt:message key="manageProfile"/><i class="fa fa-angle-down"></i></a>
                        <ul class="subChange" id="profChange">
                            <li>
                                <a href="${pageContext.request.contextPath}/view/client/profile?change=name"><fmt:message
                                        key="name"/></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/view/client/profile?change=email"><fmt:message
                                        key="placeholderEmail"/></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/view/client/profile?change=phone"><fmt:message
                                        key="phone"/></a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/view/client/profile?change=password"><fmt:message
                                        key="password"/></a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="btn" href="${pageContext.request.contextPath}/view/client/supportPage"><fmt:message
                            key="support"/></a>
                    </li>
                    <li><a class="logOutBtn" href="${pageContext.request.contextPath}/view/logout"><fmt:message
                            key="logout"/></a></li>
                </ul>
            </div>
        </div>
        <div class="minInfo">
            <div id="time"><span id="datetime"></span></div>
            <div class="customTagId"><customTag:idTag field="${user.id}"/></div>
        </div>
    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message key="support"/></h1>
                            <br>
                            <p><fmt:message key="infoSupport"/></p>
                        </div>
                    </div>
                    <div class="supportInfo">
                        <p class="supportText" ><fmt:message key="contactPhone"/></p>
                        <p class="supportText" ><fmt:message key="contactEmail"/></p>
                        <div class="socialNet">
                            <img class="socialIcon" src="${pageContext.request.contextPath}/styles/images/facebook.png" alt=""/>
                            <img class="socialIcon" src="${pageContext.request.contextPath}/styles/images/instagram.png" alt=""/>
                            <img class="socialIcon" src="${pageContext.request.contextPath}/styles/images/telegram.png" alt=""/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/styles/js/time.js"></script>
</body>
</html>