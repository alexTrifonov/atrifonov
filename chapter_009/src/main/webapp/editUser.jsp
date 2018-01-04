<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
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
    <%int idInt = Integer.parseInt((String) session.getAttribute("id"));%>
    <%User user = UserStore.INSTANCE.getUser(idInt);%>
    <form action="<%=request.getContextPath()%>/editServlJSP" method="post">
        <input type="hidden" name="id" value="<%=user.getId()%>"/>
        Name : <input type="text" name="name" value="<%=user.getName()%>"/>
        login : <input type="text" name="login" value="<%=user.getLogin()%>">
        email : <input type="text" name="email" value="<%=user.getEmail()%>">
        createDate : <input type="text" name="createDate" value="<%=user.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss", new Locale("en")))%>"/>
        <input type="submit" value="update user">
    </form>
</body>
</html>
