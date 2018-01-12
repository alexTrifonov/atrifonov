<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 02.01.2018
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/addServletJSP" method="post">
    <table>
        <tr>
            <td>Name : </td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>login : </td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>email : </td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>role :</td>
            <td>
                <select size="1" name="role_name">
                    <c:forEach items="${roles}" var="role">
                        <option>${role.roleName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>createDate : </td>
            <td><input type="text" name="createDate"/></td>
        </tr>
        <tr>
            <td>password : </td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>confirm password : </td>
            <td><input type="password" name="confirmPassword"/></td>
        </tr>
    </table>
    <br/>
    <input type="submit" value="add new user"/>
</form>
</body>
</html>
