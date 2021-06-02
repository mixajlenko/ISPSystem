<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPAdmin: Services</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlock">
            <p id="pageLogo">ISPAdmin</p>
            <div class="mainmenu">
                <ul>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/mainPageAdmin">Main page</a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/servicePageAdmin">Services</a></li>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/admin/userPageAdmin">Clients</a></li>
                    <li><a class="scroll-animite logOutBtn" href="${pageContext.request.contextPath}/view/logout">Log
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
                            <h1>Service menu</h1>
                            <br>
                            <p>Manage tariffs and plans</p>
                        </div>
                    </div>
                    <div class="serviceTable">
                        <p><c:forEach items="${services}" var="item">
                            <a href="${pageContext.request.contextPath}/view/admin/managePlan?param=${item.id}">${item.name}</a><br>
                        </c:forEach></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>