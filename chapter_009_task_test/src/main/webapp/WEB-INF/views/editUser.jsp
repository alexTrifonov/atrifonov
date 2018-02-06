<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/stylesheet" src="bootstrap-4.0.0-dist/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var userName = document.getElementById("userName").value;
            var userLogin = document.getElementById("userLogin").value;
            var country = document.getElementById("country").value;
            var city = document.getElementById("city").value;
            var street = document.getElementById("street").value;
            var houseNumber = document.getElementById("houseNumber").value;
            var flatNumber = document.getElementById("flatNumber").value;


            if(userName == '' || userLogin == '' || country == '' || city == ''
                    || street == '' || houseNumber == '' || flatNumber == '') {
                result = false;
            }
            if(!result) {
                alert("Fields mustn't are empty.")
            }
            return result;
        }
    </script>
</head>
<body>
    <div class="container">
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/editUser" method="post" onsubmit="return validate()">
            <c:set var="id" value="${requestScope.user.id}"></c:set>
            <c:set var="name" value="${requestScope.user.name}"></c:set>
            <c:set var="login" value="${requestScope.user.login}"></c:set>

            <input type="hidden" name="id" value="${user.id}"/>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userName">Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userName" value="${user.name}" name="name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userLogin">Login:</label>
                <div class="col-sm-10">
                    <input type="hidden" name="lastLogin" value="${user.login}"/>
                    <input type="text" class="input-sm" id="userLogin" value="${user.login}" name="login"/>
                </div>
            </div>
            <div class="form-group">
                <input type="hidden" name="lastRole" value="${user.role.id}">
                <c:if test="${sessionScope.role == \"admin\"}">
                    <label class="control-label col-sm-2" for="userRole">Role:</label>
                </c:if>
                <div class="col-sm-10">
                    <c:if test="${sessionScope.role == \"admin\"}">
                        <select size="1" class="form-control" name="role_name" id="userRole">
                            <c:set var="userRole" value="${user.role.name}"/>
                            <option selected value="${userRole}">${userRole}</option>
                            <c:forEach items="${roles}" var="role">
                                <c:if test="${role.name != userRole}">
                                    <option>${role.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${sessionScope.role != \"admin\"}">
                        <input type="hidden" name="role_name" value="${user.role.name}" />
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userMusicDel">Delete music:</label>
                <div class="col-sm-10">
                    <select multiple class="form-control" name="music_name_del[]" id="userMusicDel">
                        <c:forEach items="${userMusics}" var="music">
                            <option value="${music}">${music}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userMusicAdd">Add music:</label>
                <div class="col-sm-10">
                    <select multiple class="form-control" name="music_name_add[]" id="userMusicAdd">
                        <c:forEach items="${addMusics}" var="music">
                            <option value="${music}">${music}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="country">Country:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="country" value="${user.address.country}" name="country">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="city">City:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="city" value="${user.address.city}" name="city">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="street">Street:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="street" value="${user.address.street}" name="street">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="houseNumber">Number of house:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="houseNumber" value="${user.address.houseNumber}" name="houseNumber">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="flatNumber">Number of flat:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="flatNumber" value="${user.address.flatNumber}" name="flatNumber">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userPassword">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="input-sm" id="userPassword" value="${user.password}" name="password" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Edit user</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
