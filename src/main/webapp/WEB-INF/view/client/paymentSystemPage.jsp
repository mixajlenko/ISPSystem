<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPManager: Main page</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
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
        <div id="time"><span n id="datetime"></span></div>


    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1>Payment System</h1>
                            <br>
                        </div>
                    </div>
                    <div class="tableTariffs" id="payment">
                        <div class="payForm">
                            <div class="pay">
                                <p id="funds">Your founds: ${fund} USD</p>
                                <form id="payField" align="center" method="post"
                                      action="${pageContext.request.contextPath}/view/client/paymentSystemPage">
                                    <p>
                                        <label>
                                            <input style="border-radius: 11px; font-size: 16px;" type="text" required placeholder="0 USD"
                                                   name="amount">
                                        </label>
                                    </p>
                                    <input class="btn" id="payBtn" type="submit"
                                           value="Pay" name="pay">
                                </form>
                            </div>

                            <div>
                                <table class="table1 sortable" id="paymentTable">
                                    <thead>
                                    <tr>
                                        <th class="sorttable_mmdd">Date</th>
                                        <th class="sorttable_numeric">Status</th>
                                        <th class="sorttable_numeric">Bill</th>
                                        <th class="sorttable_numeric">Balance</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${paymentHistory}" var="item" varStatus="status">
                                        <tr class="highlight">
                                            <td>${item.date}</td>
                                            <td>${item.status}</td>
                                            <td>${item.bill}</td>
                                            <td>${item.balance}</td>
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