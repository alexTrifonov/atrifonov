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
        Name : <input type="text" name="name"/>
        login : <input type="text" name="login"/>
        email : <input type="text" name="email"/>
        createDate : <input type="text" name="createDate"/>
        <input type="submit" value="add new user"/>
    </form>
</body>
</html>
