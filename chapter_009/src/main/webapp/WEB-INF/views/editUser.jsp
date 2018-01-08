<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 04.01.2018
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.servletContext.contextPath}/editServletJSP" method="post">
        <c:set var="id" value="${requestScope.user.id}"></c:set>
        <c:set var="name" value="${requestScope.user.name}"></c:set>
        <c:set var="login" value="${requestScope.user.login}"></c:set>
        <c:set var="email" value="${requestScope.user.email}"></c:set>
        <c:set var="createDate" value="${requestScope.user.createDate.format(requestScope.formatter)}"></c:set>
        <input type="hidden" name="id" value="${id}"/>
        Name : <input type="text" name="name" value="${name}"/>
        login : <input type="text" name="login" value="${login}">
        email : <input type="text" name="email" value="${email}">
        createDate : <input type="text" name="createDate" value="${createDate}"/>
        <input type="submit" value="update user">
    </form>
</body>
</html>
