<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Car Store</title>
    <meta charset="UTF-8">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script>
        function checkSold(data) {
            var result;
            if (data[0] === 'true') {
                result = '<td>' + '<input type="checkbox" checked disabled>' + '</td>';
            } else {
                result = '<td>' + '<input type="checkbox" disabled>' + '</td>';
            }
            if (data[1] === 'true') {
                result += '<td>' + '<a href="./edit?idCar=' + data[2] +'">edit</a>' + '</td>';
            }
            return result;
        }
        function renderImage(data) {
            var result;
            if (data != '') {
                result = '<td>' + '<img src=' + data + ' width="100" height="70"></td>';
            } else {
                result = '<td></td>';
            }
            return result;
        }
        function changeCar() {
            $.ajax({
                type : 'POST',
                url : './edit',
                data : {'sold' : 'test'},
                complete : function (data) {
                }
            });
        }
        function getData(reqData) {
            $.ajax({
                type : 'GET',
                url : './table',
                data : reqData,
                complete : function (data) {
                    var result = '';
                    var cars = JSON.parse(data.responseText);
                    for (var i = 0; i != cars.length; i++) {
                        result += '<tr>' + renderImage(cars[i].nameImg)
                                + '<td>' + cars[i].makeCar.make + ', '
                                + cars[i].autoModel.model + ', '
                                + cars[i].body.bodyType + ', '
                                + cars[i].year + ', '
                                + cars[i].running + ', '
                                + cars[i].transmission.transmType + ', '
                                + cars[i].engine.engineType + ', '
                                + cars[i].cubicCapacity.toFixed(1) + ', '
                                + cars[i].drive.driveType + ', '
                                + cars[i].cost
                                + '</td>'
                                + checkSold([cars[i].status, cars[i].seller.login, cars[i].id])
                                + '</tr>';
                    }
                    $('#tableTasks').find('tr:gt(0)').remove();
                    $('#tableTasks tr:last').after(result);
                }
            })
        }
        function getDataMake() {
            $.ajax({
                type : 'GET',
                url : './make',
                complete : function (data) {
                    var result = '';
                    var makes = JSON.parse(data.responseText);
                    for(var i = 0; i != makes.length; i++) {
                        result += '<option>' + makes[i].make + '</option>';
                    }
                    $('#selectMake').append(result);
                }
            });
        }
        function getDataBody() {
            $.ajax({
                type : 'GET',
                url : './body',
                complete : function (data) {
                    var result = '';
                    var bodies = JSON.parse(data.responseText);
                    for(var i = 0; i != bodies.length; i++) {
                        result += '<option>' + bodies[i].bodyType + '</option>';
                    }
                    $('#selectBody').append(result);
                }
            });
        }

        $(document).ready(function () {
            getData({
                'viewPhoto': 'false',
                'makeCar' : 'Make',
                'body' : 'Body'
            });
            getDataMake();
            getDataBody();
            $('#Search').on('click', function () {
                getData({
                    'viewPhoto': $('#viewPhoto').prop('checked'),
                    'makeCar' : $('#selectMake option:selected').text(),
                    'body' : $('#selectBody option:selected').text()
                });
            })
        })
    </script>
</head>
<body>
    <form action="./login" method="get">
        <div class="form-group">
            <input type="hidden" name="exit" value="true"/>
            <input type="submit" class="btn btn-warning" value="exit"/>
        </div>
    </form>
    <br/>
    <br/>
    <form action="./addCar" method="get">
        <button type="submit">Add new car</button>
    </form>

    <br/>
    <br/>
    <div>
        <label for="viewPhoto">withPhoto</label>
        <input type="checkbox" name="withPhoto" id="viewPhoto" value="withPhoto">
        <label for="selectMake">Make of car</label>
        <select size="1" id="selectMake" name="makeCar">
            <option>Make</option>
        </select>
        <label for="selectBody">Body</label>
        <select size="1" id="selectBody" name="body">
            <option>Body</option>
        </select>
        <button type="submit" id="Search">Search</button>
    </div>
    <br/>
    <br/>

    <table id="tableTasks" name="carTable">
        <tr>
            <th></th>
            <th></th>
            <th>sold</th>
            <th></th>
        </tr>
    </table>
</body>
</html>
