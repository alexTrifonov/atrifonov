<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h3>Login with Username and Password</h3>
    <c:out value="${error}"></c:out>
    <form id="formLogin" action="<c:url value="/login" />" method="POST">
        <table>
            <tr>
                <td>username:</td>
                <td><input type="text" name="username" value='' autofocus></td>
            </tr>
            <tr>
                <td>password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
