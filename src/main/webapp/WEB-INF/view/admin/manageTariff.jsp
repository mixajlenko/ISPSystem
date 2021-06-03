<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPAdmin: New tariff</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 70%">
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
                            <h1>Tariffs</h1>
                            <br>
                            <p>Tariff registration</p>
                        </div>
                    </div>
                    <div class="newTariff">
                        <form method="post"
                              action="${pageContext.request.contextPath}/view/admin/addTariff?param=${param.get('param')}">
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="name"
                                           name="name">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="description"
                                           name="description">
                                </label>
                            </p>
                            <p>
                                <label>
                                    <input class="tariff1" type="text" required placeholder="price"
                                           name="price">
                                </label>
                            </p>
                            <input id="submitBtn" type="submit" value="Add">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>