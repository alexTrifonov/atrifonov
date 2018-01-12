<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandr
  Date: 10.01.2018
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/login" method="post">
    Login : <input type="text" name="login"/><br/>
    Password : <input type="password" name="password"/><br/>
    <input type="submit" value="log in"/>
</form>
</body>
</html>