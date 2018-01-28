<%@ page import="ru.job4j.servlets.User" %>
<%@ page import="ru.job4j.servlets.UserStore" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="ru.job4j.servlets.Country" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!--script type="text/javascript" src="js/jquery-3.3.1.min.js"></script-->
    <script>
        function validate() {
            var result = true;
            var userName = document.getElementById("userName").value;
            var userLogin = document.getElementById("userLogin").value;
            var userEmail = document.getElementById("userEmail").value;
            var userDate = document.getElementById("userDate").value;
            if(userName == '' || userLogin == '' || userEmail == '' || userDate == '') {
                result = false;
            }
            if(!result) {
                alert("Fields mustn't are empty.")
            }
            return result;
        }
    </script>

    <script>

        function getData(reqData, firstChoice) {
            $.ajax({
                type : 'GET',
                url : './city',
                data : reqData,
                complete : function (data) {
                    var result = '';
                    var cities = JSON.parse(data.responseText);
                    for(var i = 0; i != cities.length; i++) {
                        result += '<option>' + cities[i].nameCity + '</option>';
                    }

                    $('#citySelect')[0].innerHTML = result;

                    if(firstChoice) {
                        $('#citySelect').val('${requestScope.userCityName}');
                    }
                }
            });
        }

        $(document).ready(function () {
            onLoadData = {'countrySelect' : '${requestScope.userCountryName}'};
            getData(onLoadData, true);
            $('#countrySelect').change(function () {
                onChangeData = { countrySelect: $(this).val() };
                getData(onChangeData, false);
            });
        });

    </script>
</head>
<body>
    <div class="container">
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/editServletJSP" method="post" onsubmit="return validate()">
            <c:set var="id" value="${requestScope.user.id}"></c:set>
            <c:set var="name" value="${requestScope.user.name}"></c:set>
            <c:set var="login" value="${requestScope.user.login}"></c:set>
            <c:set var="email" value="${requestScope.user.email}"></c:set>
            <c:set var="createDate" value="${requestScope.user.createDate.format(requestScope.formatter)}"></c:set>
            <input type="hidden" name="id" value="${id}"/>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userName">Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userName" value="${name}" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userLogin">Login:</label>
                <div class="col-sm-10">
                    <input type="hidden" name="lastLogin" value="${login}"/>
                    <input type="text" class="input-sm" id="userLogin" value="${login}" name="login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userEmail">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userEmail" value="${email}" name="email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userRole">Role:</label>
                <input type="hidden" name="lastRole" value="${requestScope.user.roleName}"/>
                <div class="col-sm-10">
                    <c:if test="${sessionScope.role == \"admin\"}">
                        <select size="1" name="role_name" class="form-countrol" id="userRole">
                            <c:set var="userRole" value="${requestScope.user.roleName}"/>
                            <option selected value="${requestScope.user.roleName}">${requestScope.user.roleName}</option>
                            <c:forEach items="${requestScope.roles}" var="role">
                                <c:if test="${role.roleName != userRole}">
                                    <option>${role.roleName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${sessionScope.role != \"admin\"}">
                        <input type="hidden" name="role_name" value="${requestScope.user.roleName}"/>
                    </c:if>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="countrySelect">Country:</label>
                <div class="col-sm-10">
                    <select size="1" class="form-countrol" name="country" id="countrySelect">
                        <c:set var="userCountryName" value="${requestScope.userCountryName}"/>
                        <option selected value="${userCountryName}">${userCountryName}</option>
                        <c:forEach items="${countries}" var="country">
                            <c:if test="${country.countryName != userCountryName}">
                                <option>${country.countryName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="citySelect">City:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="city" id="citySelect"></select>
                    </td>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userDate">Date of create:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userDate" value="${createDate}" name="createDate"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userPassword">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="input-sm" id="userPassword" value="${requestScope.user.password}" name="password" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userConfirmPassword">Confirm password:</label>
                <div class="col-sm-10">
                    <input type="password" class="input-sm" id="userConfirmPassword" value="${requestScope.user.password}" name="confirmPassword" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Update user</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
