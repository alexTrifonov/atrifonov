<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
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
        <% for (User user : UserStore.INSTANCE.getUsers()) {%>
        <tr>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"))%></td>
            <td><a href="<%=request.getContextPath()%>/editServlJSP?id=<%=user.getId()%>">edit</a> </td>
            <td><a href="<%=request.getContextPath()%>/delete?id=<%=user.getId()%>">delete</a> </td>
        </tr>
        <%}%>
    </table>
    <form action="<%=request.getContextPath()%>/addServlJSP" method="get">
        <input type="submit" value="add new user"/>
    </form>
</body>
</html>
