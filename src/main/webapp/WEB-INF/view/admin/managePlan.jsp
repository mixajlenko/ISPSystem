<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="java">

<head>
    <title>ISPAdmin: Tariffs</title>

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

                    <c:if test="${commandInterface}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1>Tariffs</h1>
                                <br>
                                <p>Update tariff</p>
                            </div>
                        </div>
                        <div class="newTariff">
                            <form method="post"
                                  action="${pageContext.request.contextPath}/view/admin/managePlan?param=${param.get('param')}&id=${param.get("id")}&command=updateCommand">
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("name")}" required
                                               placeholder="name"
                                               name="name">
                                    </label>
                                </p>
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("description")}" required
                                               placeholder="description"
                                               name="description">
                                    </label>
                                </p>
                                <p>
                                    <label>
                                        <input class="tariff1" type="text" value="${param.get("price")}" required
                                               placeholder="price"
                                               name="price">
                                    </label>
                                </p>
                                <input id="submitBtn" type="submit"
                                       onclick="return confirm('Are you sure you want to delete this item?')"
                                       value="Update">
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${!commandInterface}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1>Tariffs</h1>
                                <br>
                                <p>Manage, delete or add tariff</p>
                            </div>
                        </div>
                        <div class="tableTariffs">
                            <table class="table1 sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_numeric">id</th>
                                    <th class="sorttable_alpha">Name</th>
                                    <th class="sorttable_alpha">Description</th>
                                    <th class="sorttable_numeric">Price</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
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
                                               href="${pageContext.request.contextPath}/view/admin/managePlan?id=${item.id}&param=${param.get("param")}&name=${item.name}&description=${item.description}&price=${item.price}&command=update">Update</a>
                                        </td>
                                        <td>
                                            <a class="deleteButton"
                                               href="${pageContext.request.contextPath}/view/admin/managePlan?command=delete&id=${item.id}&param=${param.get('param')}"
                                               onclick="return confirm('Are you sure you want to delete this item?')">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <br>
                            <br>
                            <form class="w3-container" align="center" method="post"
                                  action="${pageContext.request.contextPath}/view/admin/addTariff?param=${param.get('param')}">
                                <input class="btn btnAdd" type="submit" name="add" value="Add new">
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