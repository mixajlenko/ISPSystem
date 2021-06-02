<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPAdmin: Clients</title>

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
    </header>
    <!-- Slider area Start -->
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="slidertext">
                            <h1>Clients</h1>
                            <br>
                            <p>Manage, delete or register new clients</p>
                        </div>
                    </div>
                    <div class="tableTariffs" id="clientsTable">
                        <table class="table1 sortable">
                            <thead>
                            <tr>
                                <th class="sorttable_numeric">id</th>
                                <th class="sorttable_alpha">First Name</th>
                                <th class="sorttable_alpha">Second Name</th>
                                <th class="sorttable_numeric">Phone</th>
                                <th class="sorttable_alpha">Email</th>
                                <th class="sorttable_numeric">Wallet</th>
                                <th class="sorttable_numeric">Status</th>
                                <th>Set status</th>
                                <th>Delete</th>
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
                                               href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=block&id=${item.id}"
                                               onclick="return confirm('Are you sure you want to block this client?')">Block</a>
                                        </td>
                                    </c:if>
                                    <c:if test="${item.status == 0}">
                                        <td><a class="updateButton"
                                               href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=unlock&id=${item.id}"
                                               onclick="return confirm('Are you sure you want to unlock client?')">Unlock</a>
                                        </td>
                                    </c:if>
                                    <td>
                                        <a class="deleteButton"
                                           href="${pageContext.request.contextPath}/view/admin/userPageAdmin?command=delete&id=${item.id}"
                                           onclick="return confirm('Are you sure you want to delete client?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <br>
                        <form class="w3-container" align="center" method="post"
                              action="${pageContext.request.contextPath}/view/admin/manageUsers">
                            <input class="btn btnAdd" type="submit" name="add" value="Register client">
                        </form>
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