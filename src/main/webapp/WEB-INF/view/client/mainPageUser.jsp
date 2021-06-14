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
    <title><fmt:message key="iSPManager1"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/mainPageUser?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/mainPageUser?language=EN">
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
        <div class ="minInfo">
            <div id="time"><span id="datetime"></span></div>
            <div class="customTagId"><customTag:idTag field="${user.id}"/></div>
        </div>
        <script src="${pageContext.request.contextPath}/styles/js/time.js"></script>

    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message
                                    key="mainPage"/></h1>
                            <br>
                            <p><fmt:message
                                    key="basicInfo"/></p>
                        </div>
                    </div>
                    <div class="mainPageBlocks">
                        <c:if test="${!showTariff}">
                        <div class="serviceBlock" id="clientMainPageBlock1">
                            <h4><fmt:message
                                    key="profileInfo"/></h4>
                            <p class="boldFont"><fmt:message
                                    key="name"/>:</p>
                            <br>
                            <p>${user.firstName} ${user.secondName}</p>
                            <br>
                            <p class="boldFont"><fmt:message
                                    key="userId"/>:</p>
                            <br>
                            <p> ${user.id}</p>
                            <br>
                            <p class="boldFont"><fmt:message
                                    key="placeholderEmail"/>:</p>
                            <br>
                            <p> ${user.email}</p>
                            <br>
                            <p class="boldFont"><fmt:message
                                    key="wallet"/>:</p>
                            <br>
                            <p> ${user.wallet} USD</p>
                            <br>
                            <p class="boldFont"><fmt:message
                                    key="status"/>:</p>
                            <br>
                            <c:if test="${user.status == 0}">
                                <p class="stoppedStatus">Blocked</p>
                                <br>
                            </c:if>
                            <c:if test="${user.status == 1}">
                                <p class="activeStatus">Active</p>
                            </c:if>
                        </div>
                        <div class="serviceBlock" id="clientMainPageBlock2">
                            <h4><fmt:message
                                    key="myTariffs"/></h4>
                            <table class="tableService" id="subService">
                                <thead>
                                <tr>
                                    <th><fmt:message
                                            key="name"/></th>
                                    <th><fmt:message
                                            key="status"/></th>
                                    <th><fmt:message
                                            key="nextBill"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userTariffs}" var="item">
                                    <tr class="highlight">
                                        <td><p>${item.key.name}</p></td>
                                        <td>
                                            <c:if test="${item.value.status == 0}">
                                                <p class="stoppedStatus"><fmt:message
                                                        key="stopped"/></p>
                                            </c:if>
                                            <c:if test="${item.value.status == 1}">
                                                <p class="activeStatus"><fmt:message
                                                        key="active"/></p>
                                            </c:if>
                                        </td>
                                        <td>
                                            <p>${item.value.nextBill}</p>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton" id="moreBtn"
                                               href="${pageContext.request.contextPath}/view/client/mainPageUser?command=more&Tid=${item.key.id}"><fmt:message
                                                    key="more"/></a>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/client/paymentSystemPage?command=payForTariff&id=${item.key.id}&price=${item.key.price}"><fmt:message
                                                    key="pay1"/></a>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/client/mainPageUser?command=unsubscribe&id=${item.key.id}"
                                               onclick="return confirm('<fmt:message key="unsubscribeConfirm"/> ${item.key.name} <fmt:message key="payConfirm3"/>')"><fmt:message key="unsubscribe"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                            <c:if test="${failPay}">
                                <h4 style="margin: 50px 0 0 0">Pay for tariff is failed. Maybe you blocked or insufficient funds on balance</h4>
                            </c:if>
                            <c:if test="${emptyTariffs}">
                                <h4 style="margin: 50px 0 0 0"><fmt:message key="emptyTariffListMessage"/></h4>
                            </c:if>
                            <c:if test="${showTariff}">
                                <div class="serviceBlock" style="width: 50%">
                                    <h4><fmt:message
                                            key="tariffInfo"/></h4>
                                    <table class="tableService sortable">
                                        <thead>
                                        <tr>
                                            <th class="sorttable_alpha"><fmt:message
                                                    key="name"/></th>
                                            <th class="sorttable_alpha"><fmt:message
                                                    key="tariffDescription"/></th>
                                            <th class="sorttable_numeric"><fmt:message
                                                    key="tariffPrice"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr class="highlight">
                                            <td>${tariff.name}</td>
                                            <td>
                                                <div id="descDiv">${tariff.description}</div>
                                            </td>
                                            <td>${tariff.price}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>