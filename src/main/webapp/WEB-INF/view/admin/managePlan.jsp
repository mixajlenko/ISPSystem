<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">
<meta charset="utf-8">

<head>
    <title><fmt:message key="iSPAdmin4"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/managePlan?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/admin/managePlan?language=EN">
                    EN
                </a>
            </div>
            <p id="pageLogo">ISPAdmin</p>
            <div class="mainmenu">
                <ul>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/mainPageAdmin"><fmt:message
                            key="mainPage"/></a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/servicePageAdmin"><fmt:message
                            key="services"/></a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/userPageAdmin"><fmt:message
                            key="clients"/></a></li>
                    <li><a class="scroll-animite logOutBtn"
                           href="${pageContext.request.contextPath}/view/logout"><fmt:message key="logout"/></a></li>
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

                    <c:if test="${commandInterface}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1><fmt:message key="tariffs"/></h1>
                                <br>
                                <p><fmt:message key="updateTariff"/></p>
                            </div>
                        </div>
                        <div class="newTariff">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/view/admin/managePlan?param=${param.get('param')}&id=${param.get("id")}&command=updateCommand&redirect=true">
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("name")}" required
                                               placeholder="<fmt:message key="tariffName"/>"
                                               name="name">
                                    </label>
                                </p>
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("description")}" required
                                               placeholder="<fmt:message key="tariffDescription"/>"
                                               name="description">
                                    </label>
                                </p>
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("price")}" required
                                               placeholder="<fmt:message key="tariffPrice"/>"
                                               name="price">
                                    </label>
                                </p>
                                <input id="submitBtn" type="submit"
                                       onclick="return confirm('<fmt:message key="updateConfirm"/>')"
                                       value="<fmt:message key="update"/>">
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${!commandInterface}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1><fmt:message key="tariffs"/></h1>
                                <br>
                                <p><fmt:message key="infoTariff"/></p>
                            </div>
                        </div>
                        <div class="tableTariffs">
                            <table class="table1 sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_numeric">id</th>
                                    <th class="sorttable_alpha"><fmt:message key="tariffName"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="tariffDescription"/></th>
                                    <th class="sorttable_numeric"><fmt:message key="tariffPrice"/></th>
                                    <th><fmt:message key="edit"/></th>
                                    <th><fmt:message key="delete"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tariffs}" var="item">
                                    <tr class="highlight">
                                        <td><p style="font-size: 16px; text-align: center">${item.id}</p></td>
                                        <td>${item.name}</td>
                                        <td>${item.description}</td>
                                        <td>${item.price}</td>
                                        <td>
                                            <a class="updateButton"
                                               href="${pageContext.request.contextPath}/view/admin/managePlan?id=${item.id}&param=${param.get("param")}&name=${item.name}&description=${item.description}&price=${item.price}&command=update"><fmt:message
                                                    key="update"/></a>
                                        </td>
                                        <td>
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/admin/managePlan?command=delete&id=${item.id}&param=${param.get('param')}"
                                               onclick="return confirm('<fmt:message
                                                       key="deleteConfirm"/>')"><fmt:message key="delete"/></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <form class="w3-container" align="center" method="post"
                                  action="${pageContext.request.contextPath}/view/admin/addTariff?param=${param.get('param')}">
                                <input class="btn btnAdd" type="submit" name="add" value="<fmt:message key="addNew"/>">
                            </form>
                        </div>
                    </c:if>
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