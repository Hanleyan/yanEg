<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/12/3
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body>
    <h1>登陆成功</h1>

    <div >
      国家 <select id="one" name="one" >
            <option value="0">请选择</option>

        </select>
    </div>

    <div >
      省份或直辖市  <select id="two" name="two">
            <option value="0">请选择</option>

        </select>
    </div>
    <div >
       地级市 <select id="three" name="three">
            <option value="0">请选择</option>

        </select>
    </div>
    <div >
      区或县  <select id="four" name="four" >
            <option value="0">请选择</option>

        </select>
    </div>

</body>
<script>
    $(function(){
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/demo_ssm/getFirstArea",
            success: function (data) {
                debugger;
                for(var i = 0 ; i < data.length ; i++){
                    oneNmae =  data[i].name;
                    oneId = data[i].id;
                    option = "<option value='"+oneId + "' > " + oneNmae + "</option>";
                    $("#one").append(option);

                }
            },
            dataType: "json"
        });
    });
</script>

<script>
    $('#one').change(function(){
        debugger;
        oneId = $("#one").val();
        oneNmae = $("#one").find("option:selected").text();

        $("#two").empty();
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/demo_ssm/getArea",
            data: {
                id : oneId,
                name: oneNmae,
                level : 2
            },
            success: function (data) {
                debugger;
                $("#two").append("<option value='0'>请选择</option>");
                for(var i = 0 ; i < data.length ; i++){
                    twoNmae =  data[i].name;
                    twoId = data[i].id;
                    option = "<option value='"+twoId + "' > " + twoNmae + "</option>";
                    $("#two").append(option);
                }
                $("#three").empty();
                $("#four").empty();
                $("#three").append("<option value='0'>请选择</option>");
                $("#four").append("<option value='0'>请选择</option>");
            },
            dataType: "json"
        });
    });

    $('#two').change(function(){
        debugger;
        twoId = $("#two").val();
        twoNmae = $("#two").find("option:selected").text();

        $("#three").empty();
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/demo_ssm/getArea",
            data: {
                id : twoId,
                name: twoNmae,
                level : 3
            },
            success: function (data) {
                debugger;
                $("#three").append("<option value='0'>请选择</option>");
                for(var i = 0 ; i < data.length ; i++){
                    threeNmae =  data[i].name;
                    threeId = data[i].id;
                    option = "<option value='"+threeId + "' > " + threeNmae + "</option>";
                    $("#three").append(option);
                }
                $("#four").empty();
                $("#four").append("<option value='0'>请选择</option>");
            },
            dataType: "json"
        });
    })

    $('#three').change(function(){
        debugger;
        threeId = $("#three").val();
        threeNmae = $("#three").find("option:selected").text();

        $("#four").empty();
        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/demo_ssm/getArea",
            data: {
                id : threeId,
                name: threeNmae,
                level : 4
            },
            success: function (data) {
                debugger;
                $("#four").append("<option value='0'>请选择</option>");
                for(var i = 0 ; i < data.length ; i++){
                    fourNmae =  data[i].name;
                    fourId = data[i].id;
                    option = "<option value='"+fourId + "' > " + fourNmae + "</option>";
                    $("#four").append(option);
                }

            },
            dataType: "json"
        });
    })




</script>
</html>
