<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <title>ISPManager: Main page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <p id="pageLogo">ISPManager</p>
            <div class="mainmenu">
                <ul class="topChange">
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/mainPageUser">Main page</a></li>
                    <li><a class="btn"
                           href="${pageContext.request.contextPath}/view/client/servicePage">Services<i
                            class="fa fa-angle-down"></i></a>
                        <ul class="subChange">
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
        <script src="${pageContext.request.contextPath}/styles/js/time.js"></script>

    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1>Main page</h1>
                            <br>
                            <p>Basic information about clients and services</p>
                        </div>
                    </div>
                    <div class="mainPageBlocks">
                        <c:if test="${!showTariff}">
                        <div class="serviceBlock" id="clientMainPageBlock1">
                            <h4>Profile info</h4>
                            <p class="boldFont">Name:</p>
                            <br>
                            <p>${user.firstName} ${user.secondName}</p>
                            <br>
                            <p class="boldFont">User ID:</p>
                            <br>
                            <p> ${user.id}</p>
                            <br>
                            <p class="boldFont">Email:</p>
                            <br>
                            <p> ${user.email}</p>
                            <br>
                            <p class="boldFont">Wallet:</p>
                            <br>
                            <p> ${user.wallet} USD</p>
                            <br>
                            <p class="boldFont">Status:</p>
                            <br>
                            <c:if test="${user.status == 0}">
                                <p class="stoppedStatus"> Blocked</p>
                                <br>
                            </c:if>
                            <c:if test="${user.status == 1}">
                                <p class="activeStatus"> Active</p>
                            </c:if>
                        </div>
                        <div class="serviceBlock" id="clientMainPageBlock2">
                            <h4>My tariffs</h4>
                            <table class="tableService" id="subService">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Status</th>
                                    <th>Next Bill</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userTariffs}" var="item">
                                    <tr class="highlight">
                                        <td><p>${item.key.name}</p></td>
                                        <td>
                                            <c:if test="${item.value.status == 0}">
                                                <p class="stoppedStatus">Stopped</p>
                                            </c:if>
                                            <c:if test="${item.value.status == 1}">
                                                <p class="activeStatus">Active</p>
                                            </c:if>
                                        </td>
                                        <td>
                                            <p>${item.value.nextBill}</p>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton" id="moreBtn"
                                               href="${pageContext.request.contextPath}/view/client/mainPageUser?command=more&Tid=${item.key.id}">More</a>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/client/paymentSystemPage?command=payForTariff&id=${item.key.id}&price=${item.key.price}"
                                               onclick="return confirm('Do you confirm ${item.key.price} USD payment for ${item.key.name} service?')">Pay</a>
                                        </td>
                                        <td class="endTdUserInfo" style="width: 50px">
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/client/mainPageUser?command=unsubscribe&id=${item.key.id}"
                                               onclick="return confirm('Do you confirm unsubscribe from ${item.key.name} service?')">Unsubscribe</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </c:if>
                            <c:if test="${showTariff}">
                            <div class="serviceBlock" style="width: 50%">
                                <h4>Tariff info</h4>
                                <table class="tableService sortable">
                                    <thead>
                                    <tr>
                                        <th class="sorttable_alpha">Name</th>
                                        <th class="sorttable_alpha">Description</th>
                                        <th class="sorttable_numeric">Price</th>
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