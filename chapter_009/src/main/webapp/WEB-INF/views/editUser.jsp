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
    <table>
        <tr>
            <td>Name : </td>
            <td><input type="text" name="name" value="${name}"/></td>
        </tr>
        <tr>
            <td>login : </td>
            <td>
                <input type="hidden" name="lastLogin" value="${login}"/>
                <input type="text" name="login" value="${login}"/>
            </td>
        </tr>
        <tr>
            <td>email : </td>
            <td><input type="text" name="email" value="${email}"/></td>
        </tr>
        <tr>
            <input type="hidden" name="lastRole" value="${requestScope.user.roleName}"/>
            <c:if test="${sessionScope.role == \"admin\"}">
                <td>role :</td>
                <td>
                    <select size="1" name="role_name">
                        <c:set var="userRole" value="${requestScope.user.roleName}"/>
                        <option selected value="${requestScope.user.roleName}">${requestScope.user.roleName}</option>
                        <c:forEach items="${requestScope.roles}" var="role">
                            <c:if test="${role.roleName != userRole}">
                                <option>${role.roleName}</option>
                            </c:if>

                        </c:forEach>
                    </select>
                </td>
            </c:if>
            <c:if test="${sessionScope.role != \"admin\"}">
                <input type="hidden" name="role_name" value="${requestScope.user.roleName}"/>
            </c:if>
        </tr>
        <tr>
            <td>createDate : </td>
            <td><input type="text" name="createDate" value="${createDate}"/></td>
        </tr>
        <tr>
            <td>password : </td>
            <td><input type="password" name="password" value="${requestScope.user.password}"/></td>
        </tr>
        <tr>
            <td>confirm password : </td>
            <td><input type="password" name="confirmPassword" value="${requestScope.user.password}"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="update user">
</form>
</form>
</body>
</html>
