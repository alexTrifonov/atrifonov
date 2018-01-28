<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                    <th>Name</th>
                    <th>login</th>
                    <th>email</th>
                    <th>role</th>
                    <th>date of creation</th>
                    <th>country</th>
                    <th>city</th>
                    <th>action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td><c:out value="${user.name}"></c:out></td>
                        <td><c:out value="${user.login}"></c:out></td>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td><c:out value="${user.roleName}"></c:out></td>
                        <td><c:out value="${user.createDate.format(requestScope.formatter)}"></c:out></td>
                        <td><c:out value="${user.country}"></c:out> </td>
                        <td><c:out value="${user.city}"></c:out> </td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/editServletJSP?id=${user.id}">edit</a>
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
        <form action="${pageContext.servletContext.contextPath}/addServletJSP" method="get">
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
