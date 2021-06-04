<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">

<head>
    <title><fmt:message key="iSPManager2"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/paymentSystemPage?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/paymentSystemPage?language=EN">
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
                                        key="manageEmail"/></a>
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
        <div id="time"><span n id="datetime"></span></div>


    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message
                                    key="paymentSystem"/></h1>
                            <br>
                            <p><fmt:message
                                    key="basicInfoPayment"/></p>
                            <br>
                        </div>
                    </div>
                    <div class="tableTariffs" id="payment">
                        <div class="payForm">
                            <div class="pay">
                                <p id="funds"><fmt:message
                                        key="yourFunds"/>: ${fund} USD</p>
                                <form id="payField" align="center" method="post"
                                      action="${pageContext.request.contextPath}/view/client/paymentSystemPage">
                                    <p>
                                        <label>
                                            <input style="border-radius: 11px; font-size: 16px;" type="text" required
                                                   placeholder="0 USD"
                                                   name="<fmt:message
                            key="amount"/>">
                                        </label>
                                    </p>
                                    <input class="btn" id="payBtn" type="submit"
                                           value="Pay" name="<fmt:message
                            key="pay"/>">
                                </form>
                            </div>

                            <div>
                                <table class="table1 sortable" id="paymentTable">
                                    <thead>
                                    <tr>
                                        <th class="sorttable_mmdd"><fmt:message
                                                key="date"/></th>
                                        <th class="sorttable_numeric"><fmt:message
                                                key="status"/></th>
                                        <th class="sorttable_numeric"><fmt:message
                                                key="bill"/></th>
                                        <th class="sorttable_numeric"><fmt:message
                                                key="balance"/></th>
                                        <th class="sorttable_alpha"><fmt:message
                                                key="type"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${paymentHistory}" var="item" varStatus="status">
                                        <tr class="highlight">
                                            <td>${item.date}</td>
                                            <td>
                                                <c:if test="${item.status == 0}">
                                                    <p class="activeStatus"><fmt:message
                                                            key="accepted"/></p>
                                                </c:if>
                                                <c:if test="${item.status == 1}">
                                                    <p class="stoppedStatus"><p class="activeStatus"><fmt:message key="declined"/></p>
                                                </c:if>
                                            </td>
                                            <td>${item.bill}</td>
                                            <td>${item.balance}</td>
                                            <c:if test="${item.type == 'Refund'}">
                                                <td><fmt:message key="typeTable1"/></td>
                                            </c:if>
                                            <c:if test="${item.type == 'Pay for service'}">
                                                <td><fmt:message key="typeTable2"/></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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