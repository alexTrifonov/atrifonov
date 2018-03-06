<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<form method="post" action="./edit">
    <label for="soldCar">Sold :</label>
    <select size="1" name="sold" id="soldCar">
        <option>true</option>
        <option>false</option>
    </select>
    <input type="hidden" name="idCar" value="${requestScope.idCar}"/>
    <input type="submit">
</form>
</body>
</html>
