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
        function getData(reqData) {
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
                }
            });
        }

        $(document).ready(function () {
            onLoadData = {'countrySelect' : $('#countrySelect').find(":selected").text()};
            getData(onLoadData);
            $('#countrySelect').change(function () {
                onChangeData = { countrySelect: $(this).val() };
                getData(onChangeData);
            });
        });

    </script>

</head>
<body>
    <div class="container">
        <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/addServletJSP" method="post" onsubmit="return validate()">
            <div class="form-group">
                <label class="control-label col-sm-2" for="userName">Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userName" placeholder="Enter name" name="name">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userLogin">Login:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userLogin" placeholder="Enter login" name="login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userEmail">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="input-sm" id="userEmail" placeholder="Enter email" name="email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userRole">Role:</label>
                <div class="col-sm-10">
                    <select size="1" class="form-countrol" name="role_name" id="userRole">
                        <c:forEach items="${roles}" var="role">
                            <option>${role.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="countrySelect">Country:</label>
                <div class="col-sm-10">
                    <select size="1" class="form-countrol" name="country" id="countrySelect">
                        <c:forEach items="${countries}" var="country">
                            <option>${country.countryName}</option>
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
                    <input type="text" class="input-sm" id="userDate" placeholder="DD MM YYYY, HH:MM:SS" name="createDate"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userPassword">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="input-sm" id="userPassword" placeholder="Enter password" name="password" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userConfirmPassword">Confirm password:</label>
                <div class="col-sm-10">
                    <input type="password" class="input-sm" id="userConfirmPassword" placeholder="Enter password" name="confirmPassword" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Add new user</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
