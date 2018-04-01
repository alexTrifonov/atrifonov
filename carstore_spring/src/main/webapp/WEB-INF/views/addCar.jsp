<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addCar</title>
    <meta charset="UTF-8">
    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script>
        function getDataEngine() {
            $.ajax({
                type : 'GET',
                url : './engine',
                complete : function (data) {
                    var result = '';
                    var engines = JSON.parse(data.responseText);
                    for(var i = 0; i != engines.length; i++) {
                        result += '<option>' + engines[i].engineType + '</option>';
                    }
                    $('#engineSelect')[0].innerHTML = result;
                }
            });
        }
        function getDataDrive() {
            $.ajax({
                type : 'GET',
                url : './drive',
                complete : function (data) {
                    var result = '';
                    var drives = JSON.parse(data.responseText);
                    for(var i = 0; i != drives.length; i++) {
                        result += '<option>' + drives[i].driveType + '</option>';
                    }
                    $('#driveSelect')[0].innerHTML = result;
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
                    $('#bodySelect')[0].innerHTML = result;
                }
            });
        }
        function getDataTransmission() {
            $.ajax({
                type : 'GET',
                url : './transmission',
                complete : function (data) {
                    var result = '';
                    var transmissions = JSON.parse(data.responseText);
                    for(var i = 0; i != transmissions.length; i++) {
                        result += '<option>' + transmissions[i].transmType + '</option>';
                    }
                    $('#transmSelect')[0].innerHTML = result;
                }
            });
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
                    $('#makeSelect')[0].innerHTML = result;
                }
            });
        }
        function getDataModel(reqData) {
            $.ajax({
                type : 'GET',
                url : './model',
                data : reqData,
                complete : function (data) {
                    var result = '';
                    var models = JSON.parse(data.responseText);
                    for(var i = 0; i != models.length; i++) {
                        result += '<option>' + models[i].model + '</option>';
                    }
                    $('#modelSelect')[0].innerHTML = result;
                }
            });
        }

        $(document).ready(function () {
            getDataMake();
            onLoadData = {'makeSelect' : $('#makeSelect').find(":selected").text()};
            getDataModel(onLoadData);
            $('#makeSelect').change(function () {
                onChangeData = { makeSelect: $(this).val() };
                getDataModel(onChangeData);
            });
            getDataBody();
            getDataTransmission();
            getDataEngine();
            getDataDrive();
        })
    </script>
</head>
<body>
    <div class="container">
        <form class="form-horizontal" action="./add" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="control-label col-sm-2" for="makeSelect">Make of car:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="makeCar" id="makeSelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="modelSelect">Model:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="model" id="modelSelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="bodySelect">Body:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="body" id="bodySelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="yearInput">Year:</label>
                <div class="col-sm-10">
                    <td>
                        <input type="text" class="input-sm" name="year" id="yearInput">
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="runningInput">Running:</label>
                <div class="col-sm-10">
                    <td>
                        <input type="text" class="input-sm" name="running" id="runningInput">
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="transmSelect">Transmission:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="transmission" id="transmSelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="engineSelect">Engine:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="engine" id="engineSelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="capacityInput">Capacity:</label>
                <div class="col-sm-10">
                    <td>
                        <input type="text" class="input-sm" name="capacity" id="capacityInput">
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="driveSelect">Drive:</label>
                <div class="col-sm-10">
                    <td>
                        <select size="1" class="form-countrol" name="drive" id="driveSelect"></select>
                    </td>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="costInput">Cost:</label>
                <div class="col-sm-10">
                    <td>
                        <input type="text" class="input-sm" name="cost" id="costInput">
                    </td>
                </div>
            </div>

            <input type="file" name="file" />

            <input type="submit">
        </form>
    </div>
</body>
</html>
