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
    <form class="form-horizontal" action="${pageContext.servletContext.contextPath}/addUser" method="post" onsubmit="return validate()">
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
            <label class="control-label col-sm-2" for="userRole">Role:</label>
            <div class="col-sm-10">
                <select size="1" class="form-countrol" name="role_name" id="userRole">
                    <c:forEach items="${roles}" var="role">
                        <option>${role.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="userMusic">Music:</label>
            <div class="col-sm-10">
                <select multiple class="form-control" name="music_name[]" id="userMusic" required>
                    <c:forEach items="${musics}" var="music">
                        <option value="${music.type}">${music.type}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Country:</label>
            <div class="col-sm-10">
                <input type="text" class="input-sm" id="country" placeholder="Enter country" name="country">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="city">City:</label>
            <div class="col-sm-10">
                <input type="text" class="input-sm" id="city" placeholder="Enter city" name="city">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="street">Street:</label>
            <div class="col-sm-10">
                <input type="text" class="input-sm" id="street" placeholder="Enter street" name="street">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="houseNumber">Number of house:</label>
            <div class="col-sm-10">
                <input type="text" class="input-sm" id="houseNumber" placeholder="Enter number of house" name="houseNumber">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="flatNumber">Number of flat:</label>
            <div class="col-sm-10">
                <input type="text" class="input-sm" id="flatNumber" placeholder="Enter number of flat" name="flatNumber">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="userPassword">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="input-sm" id="userPassword" placeholder="Enter password" name="password" required>
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
