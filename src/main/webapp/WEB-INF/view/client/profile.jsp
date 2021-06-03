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
                            <h1>My profile</h1>
                            <br>
                        </div>
                    </div>
                    <div class="tableTariffs" id="payment">
                        <h3>My Profile</h3>
                        <div class="editForm">
                            <c:choose>
                                <c:when test="${nameChange}">
                                    <div class="editNameForm">
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=name">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${firstName}"
                                                           placeholder="First Name"
                                                           name="fName">
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${lastName}"
                                                           placeholder="Second Name"
                                                           name="lName">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="Change Name" name="changeName">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${phoneChange}">
                                    <div class="editPhoneForm">
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=phone">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="text" required
                                                           value="${phone}"
                                                           placeholder="Phone"
                                                           name="phone">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="Change Phone" name="changePhone">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${emailChange}">
                                    <div class="editEmailForm">
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=email">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="email" required
                                                           value="${email}"
                                                           placeholder="Email"
                                                           name="email">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn" type="submit"
                                                       value="Change Email" name="changeEmail">
                                            </div>
                                        </form>
                                    </div>
                                </c:when>
                                <c:when test="${passChange}">
                                    <div class="editPassForm">
                                        <form class="editFormChild" method="post"
                                              action="${pageContext.request.contextPath}/view/client/profile?change=password">
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="password" required
                                                           placeholder="Old Password"
                                                           name="oldPass">
                                                </label>
                                            </p>
                                            <p>
                                                <label>
                                                    <input class="profileEditorFields" type="password" required
                                                           placeholder="New Password"
                                                           name="newPass">
                                                </label>
                                            </p>
                                            <div>
                                                <input class="btn editProfileBtn " type="submit"
                                                       value="Change Password" name="changePassword">
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