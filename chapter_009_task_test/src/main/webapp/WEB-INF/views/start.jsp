<%@ page import="ru.job4j.fan.User" %>
<%@ page import="ru.job4j.fan.UserStore" %>
<%@ page import="ru.job4j.fan.Role" %>
<%@ page import="ru.job4j.fan.Address" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/stylesheet" src="bootstrap-4.0.0-dist/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #e1ebff;
        }
        p {
            font-family: Arial, Verdana, sans-serif;
            font-size: 13pt;
            color: #333;
        }
        h2 {
            font-family: "Times New Roman", serif;
            font-size: 15pt;
            color: darkslategrey;
        }
        table {
            width: 60%;
        }
    </style>
</head>
<body>
<div class="container">
    <p>You are logged in as : ${sessionScope.login}</p>
    <h2>Users</h2>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th style="width: 7%">Name</th>
                <th style="width: 7%">login</th>
                <th style="width: 7%">role</th>
                <th style="width: 34%">address</th>
                <th style="width: 35%">music list</th>
                <th style="width: 10%">action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td><c:out value="${user.name}"></c:out></td>
                    <td><c:out value="${user.login}"></c:out></td>
                    <td><c:out value="${user.role.name}"></c:out></td>
                    <td><c:out value="${user.address}"></c:out></td>
                    <td><c:out value="${user.musicListString}"></c:out></td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/editUser?id=${user.id}">edit</a>
                        <c:if test="${sessionScope.role == \"admin\"}">
                            <a href="${pageContext.servletContext.contextPath}/delete?id=${user.id}">delete</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<br/>



<div class="container">
    <form action="${pageContext.servletContext.contextPath}/addUser" method="get">
        <div class="form-group">
            <c:if test="${sessionScope.role == \"admin\"}">
                <input type="submit" class="btn btn-success" value="add new user"/>
            </c:if>
        </div>
    </form>
    <form action="${pageContext.servletContext.contextPath}/login" method="get">
        <div class="form-group">
            <input type="hidden" name="exit" value="true"/>
            <input type="submit" class="btn btn-warning" value="exit"/>
        </div>

    </form>
</div>

</body>
</html>
