<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPManager: Services</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <p id="pageLogo">ISPManager</p>
            <div class="mainmenu">
                <ul class="topChange" style="overflow: visible">
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/mainPageUser">Main page</a></li>
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/servicePage">Services<i
                            class="fa fa-angle-down"></i></a>
                        <ul class="subChange" style="overflow: visible">
                            <c:forEach items="${services}" var="item" varStatus="status">
                                <li>
                                    <a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item.name}&serviceId=${item.id}">${item.name}</a>
                                </li>
                            </c:forEach>
                            </li>
                        </ul>
                    </li>
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/paymentSystemPage">Payments</a></li>
                    <li><a class="btn"
                           href="#">Manage profile<i class="fa fa-angle-down"></i></a>
                        <ul class="subChange" id="profChange">
                            <li><a href="${pageContext.request.contextPath}/view/client/profile?change=name">Name</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/view/client/profile?change=email">Email</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/view/client/profile?change=phone">Phone</a>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/view/client/profile?change=password">Password</a>
                            </li>
                        </ul>
                    </li>
                    <li><a class="btn" href="${pageContext.request.contextPath}/view/client/supportPage">Support</a>
                    </li>
                    <li><a class="logOutBtn" href="${pageContext.request.contextPath}/view/logout">Log
                        Out</a></li>
                </ul>
            </div>
        </div>
        <div id="time"><span id="datetime"></span></div>
    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1>Services</h1>
                        </div>
                    </div>
                    <div class="plansBlock">
                        <div class="plansList singePlan">
                            <h4>Complex plan</h4>
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
                            <h4>Single plan</h4>
                            <c:forEach items="${services}" var="item" varStatus="status">
                                <a class="btn" id="serv" href="${pageContext.request.contextPath}/view/client/servicePage?item=${item.name}&serviceId=${item.id}">${item.name}</a>
                            </c:forEach>
                        </div>
                        <c:choose>
                        <c:when test="${showTariffs}">
                        <div class="serviceBlock">
                            <div class="mainmenuSort">
                                <ul class="topSort">
                                    <li><a class="btnSort">Sort ><i class="fa fa-angle-down"></i></a>
                                        <ul class="subSort">
                                            <li><a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=name">A-Z</a></li>
                                            <li><a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=nameR">Z-A</a></li>
                                            <li><a href="${pageContext.request.contextPath}/view/client/servicePage?item=${item}&serviceId=${paramId}&sort=price">Price</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                            <table class="tableService sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_alpha">Name</th>
                                    <th class="sorttable_alpha">Description</th>
                                    <th class="sorttable_numeric">Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tariffs}" var="item">
                                    <tr class="highlight">
                                        <td>${item.name}</td>
                                        <td><div id="descDiv">${item.description}</div></td>
                                        <td>${item.price}</td>
                                        <td id="endTd"><a class="updateButton" id="subscribeButton" href="${pageContext.request.contextPath}/view/client/servicePage?tariffId=${item.id}" onclick="return confirm('Confirm ${item.name} subscription?')">Subscribe</a></td>
                                    </tr>
                                </c:forEach>
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
</div>
<script src="${pageContext.request.contextPath}/styles/js/time.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/rowLight.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/sorttable.js"></script>
</body>
</html>