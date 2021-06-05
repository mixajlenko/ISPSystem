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
    <title><fmt:message key="iSPAdmin6"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/userPageAdmin?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/userPageAdmin?language=EN">
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
    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1><fmt:message key="clients"/></h1>
                            <br>
                            <p><fmt:message key="clientsPageInfo"/></p>
                        </div>
                    </div>
                    <div class="tableTariffs" id="clientsTable">
                        <c:if test="${!moreInfo}">
                            <table class="table1 sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_numeric">id</th>
                                    <th class="sorttable_alpha"><fmt:message key="placeholderFirstName"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="placeholderSecondName"/></th>
                                    <th class="sorttable_numeric"><fmt:message key="phone"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="placeholderEmail"/></th>
                                    <th class="sorttable_numeric"><fmt:message key="wallet"/></th>
                                    <th class="sorttable_numeric"><fmt:message key="status"/></th>
                                    <th><fmt:message key="setStatus"/></th>
                                    <th><fmt:message key="delete"/></th>
                                    <th><fmt:message key="moreInfo"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${users}" var="item" varStatus="status">
                                    <tr class="highlight">
                                        <td>${item.id}</td>
                                        <td>${item.firstName}</td>
                                        <td>${item.secondName}</td>
                                        <td>${item.phone}</td>
                                        <td>${item.email}</td>
                                        <td>${item.wallet}</td>
                                        <td>${item.status}</td>
                                        <c:if test="${item.status == 1}">
                                            <td><a class="deleteButton"
                                                   href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=block&Uid=${item.id}"
                                                   onclick="return confirm('<fmt:message key="blockClientConfirm"/>')"><fmt:message key="block"/></a>
                                            </td>
                                        </c:if>
                                        <c:if test="${item.status == 0}">
                                            <td><a class="updateButton"
                                                   href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=unlock&Uid=${item.id}"
                                                   onclick="return confirm('<fmt:message key="unblockClientConfirm"/>')"><fmt:message key="unblock"/></a>
                                            </td>
                                        </c:if>
                                        <td>
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=delete&Uid=${item.id}"
                                               onclick="return confirm('<fmt:message key="deleteClientConfirm"/>')"><fmt:message key="delete"/></a>
                                        </td>
                                        <td>
                                            <a class="deleteButton" id="moreBtn"
                                               href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=more&Uid=${item.id}"><fmt:message key="more"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <form class="w3-container" align="center" method="post"
                                  action="${pageContext.request.contextPath}/view/admin/manageUsers">
                                <input class="btn btnAdd" type="submit" name="add" value="<fmt:message key="registerClient"/>">
                            </form>
                        </c:if>
                        <c:if test="${moreInfo}">
                            <h4><fmt:message key="clientTariffs"/></h4>
                            <br>
                            <table class="tableService" id="subService">
                                <thead>
                                <tr>
                                    <th><fmt:message key="name"/></th>
                                    <th><fmt:message key="status"/></th>
                                    <th><fmt:message key="nextBill"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userTariffs}" var="item">
                                    <tr class="highlight">
                                        <td><p>${item.key.name}</p></td>
                                        <td>
                                            <c:if test="${item.value.status == 0}">
                                                <p class="stoppedStatus"><fmt:message key="stopped"/></p>
                                            </c:if>
                                            <c:if test="${item.value.status == 1}">
                                                <p class="activeStatus"><fmt:message key="active"/></p>
                                            </c:if>
                                        </td>
                                        <td>
                                            <p>${item.value.nextBill}</p>
                                        </td>

                                        <td class="endTdUserInfo">
                                            <c:if test="${item.value.status == 0}">
                                                <a class="updateButton"
                                                   href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=more&operation=Activate&Uid=${param.get("Uid")}&Tid=${item.key.id}"
                                                   onclick="return confirm('<fmt:message key="active"/> ${item.key.name} <fmt:message key="activateTariffConfirm"/>')"><fmt:message key="activate"/></a>
                                            </c:if>
                                            <c:if test="${item.value.status == 1}">
                                                <a class="deleteButton"
                                                   href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=more&operation=Deactivate&Uid=${param.get("Uid")}&Tid=${item.key.id}"
                                                   onclick="return confirm('<fmt:message key="deactivate"/> ${item.key.name} <fmt:message key="activateTariffConfirm"/>')"><fmt:message key="deactivate"/></a>
                                            </c:if>
                                        </td>
                                        <td class="endTdUserInfo">
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=more&operation=Unsubscribe&Uid=${param.get("Uid")}&Tid=${item.key.id}"
                                               onclick="return confirm('<fmt:message key="unsubscribeConfirm"/> ${item.key.name} <fmt:message key="payConfirm3"/>')"><fmt:message key="unsubscribe"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

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