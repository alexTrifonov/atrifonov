<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 02.01.2018
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Name</th>
            <th>login</th>
            <th>email</th>
            <th>date of creation</th>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate.format(requestScope.formatter)}"></c:out></td>
                <td><a href="${pageContext.servletContext.contextPath}/editServletJSP?id=${user.id}">edit</a> </td>
                <td><a href="${pageContext.servletContext.contextPath}/delete?id=${user.id}">delete</a> </td>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.servletContext.contextPath}/addServletJSP" method="get">
        <input type="submit" value="add new user"/>
    </form>
</body>
</html>
