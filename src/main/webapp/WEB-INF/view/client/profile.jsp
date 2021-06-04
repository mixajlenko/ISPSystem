<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">

<head>
    <title><fmt:message key="iSPManager3"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/profile?language=RU">
                    RU
                </a>
                <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/profile?language=EN">
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
                            <h1><fmt:message
                                    key="myProfile"/></h1>
                            <br>
                            <p><fmt:message
                                    key="basicInfoProfile"/></p>
                            <br>
                        </div>
                    </div>
                    <div class="tableTariffs" id="payment">
                        <div class="editForm">
                            <c:choose>
                                <c:when test="${nameChange}">
                                    <div class="editNameForm">
                                        <h3><fmt:message
                                                key="manageName"/></h3>
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=name">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${firstName}"
                                                           placeholder="<fmt:message
                                key="placeholderFirstName"/>"
                                                           name="fName">
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${lastName}"
                                                           placeholder="<fmt:message
                                key="placeholderSecondName"/>"
                                                           name="lName">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="<fmt:message
                                key="submit"/>" name="changeName">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${phoneChange}">
                                    <div class="editPhoneForm">
                                        <h3><fmt:message
                                                key="managePhone"/></h3>
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=phone">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${phone}"
                                                           placeholder="<fmt:message
                                key="phone"/>"
                                                           name="phone">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="<fmt:message
                                key="submit"/>" name="changePhone">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${emailChange}">
                                    <div class="editEmailForm">
                                        <h3><fmt:message
                                                key="manageEmail"/></h3>
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=email">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="email" required
                                                           value="${email}"
                                                           placeholder="<fmt:message
                                key="placeholderEmail"/>"
                                                           name="email">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="<fmt:message
                                key="submit"/>" name="changeEmail">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${passChange}">
                                    <div class="editPassForm">
                                        <h3><fmt:message
                                                key="managePassword"/></h3>
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=password">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="password" required
                                                           placeholder="<fmt:message
                                key="placeholderOldPass"/>"
                                                           name="oldPass">
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="password" required
                                                           placeholder="<fmt:message
                                key="placeholderNewPass"/>"
                                                           name="newPass">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn " type="submit"
                                                       value="<fmt:message
                                key="submit"/>" name="changePassword">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>