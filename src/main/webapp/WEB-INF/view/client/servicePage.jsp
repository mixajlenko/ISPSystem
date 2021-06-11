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
    <title><fmt:message key="iSPManager4"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/client/servicePage?language=RU">
                    RU
                </a>
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/client/servicePage?language=EN">
                    EN
                </a>
            </div>
            <p id="pageLogo">ISPManager</p>
            <div class="mainmenu">
                <ul class="topChange" style="overflow: visible">
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
    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message key="services"/></h1>
                            <br>
                            <p><fmt:message
                                    key="basicInfoService"/></p>
                        </div>
                    </div>
                    <div class="plansBlock">
                        <div class="plansList singePlan">
                            <h4><fmt:message key="complex"/></h4>
                            <c:forEach var="complexTariff" items="${complex}" varStatus="status">
                                <c:if test="${complexTariff.id == 1}">
                                    <a class="plan" href="${pageContext.request.contextPath}/view/client/servicePage"
                                       onclick="return confirm('TEST1')"><p class="planText">${complexTariff.name}</p>
                                    </a>
                                </c:if>
                                <c:if test="${complexTariff.id == 2}">
                                    <a class="plan" href="${pageContext.request.contextPath}/view/client/servicePage"
                                       onclick="return confirm('TEST2')"><p class="planText">${complexTariff.name}</p>
                                    </a>
                                </c:if>
                                <c:if test="${complexTariff.id == 3}">
                                    <a class="plan" href="${pageContext.request.contextPath}/view/client/servicePage"
                                       onclick="return confirm('TEST3')"><p class="planText">${complexTariff.name}</p>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                        <div class="plansList singePlan">
                            <h4><fmt:message key="single"/></h4>
                            <c:forEach items="${services}" var="item" varStatus="status">
                                <a class="btn" id="serv"
                                   href="${pageContext.request.contextPath}/view/client/servicePage?item=${item.name}&serviceId=${item.id}">${item.name}</a>
                            </c:forEach>
                        </div>
                        <div>
                            Download all:
                            <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=pdf&serviceName=All&serviceId=empty">PDF</a>
                            <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=txt&serviceName=All&serviceId=empty">TXT</a>
                            <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=docx&serviceName=All&serviceId=empty">DOCX</a>
                            <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=csv&serviceName=All&serviceId=empty">CSV</a>
                        </div>
                        <c:if test="${subSuccess}">
                            <h4 style="margin: 50px 0 0 0"><fmt:message key="successfullySub"/></h4>
                        </c:if>
                        <c:if test="${subFail}">
                            <h4 style="margin: 50px 0 0 0"><fmt:message key="failSub"/></h4>
                        </c:if>
                        <c:choose>
                        <c:when test="${showTariffs}">
                        <div class="serviceBlock">
                            <div class="mainmenuSort">
                                <ul class="topSort">
                                    <li><a class="btnSort"><fmt:message key="sort"/> ><i
                                            class="fa fa-angle-down"></i></a>
                                        <ul class="subSort">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=name"><fmt:message
                                                        key="az"/></a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=nameR"><fmt:message
                                                        key="za"/></a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=price"><fmt:message
                                                        key="tariffPrice"/></a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <table class="tableService sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_alpha"><fmt:message key="name"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="tariffDescription"/></th>
                                    <th class="sorttable_numeric"><fmt:message key="tariffPrice"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tariffs}" var="item">
                                    <tr class="highlight">
                                        <td>${item.name}</td>
                                        <td>
                                            <div id="descDiv">${item.description}</div>
                                        </td>
                                        <td>${item.price}</td>
                                        <td id="endTd"><a class="updateButton" id="subscribeButton"
                                                          href="${pageContext.request.contextPath}/view/client/servicePage?tariffId=${item.id}"
                                                          onclick="return confirm('
                                                              <fmt:message key="subscribeSubmit1"/> ${item.name}
                                                              <fmt:message key="subscribeSubmit2"/>"><fmt:message
                                                key="subscribe"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                Download:
                                <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=pdf&serviceId=${param.get("serviceId")}&serviceName=${param.get("item")}">PDF</a>
                                <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=txt&serviceId=${param.get("serviceId")}&serviceName=${param.get("item")}">TXT</a>
                                <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=docx&serviceId=${param.get("serviceId")}&serviceName=${param.get("item")}">DOCX</a>
                                <a href="${pageContext.request.contextPath}/view/client/downloadServices?format=csv&serviceId=${param.get("serviceId")}&serviceName=${param.get("item")}">CSV</a>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/styles/js/time.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/rowLight.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/sorttable.js"></script>
</body>
</html>