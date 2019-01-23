<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/12/3
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body >
<h2 align="center"><font color=red>用户注册页面</font></h2>
<form id="registform" action="regist" method="post">
    <table align="center" border="1">
        <tr>
            <td>账号:</td>
            <td><input id="usercode" type="text" name="usercode"></td>
        </tr>
        <tr>
            <td>昵称:</td>
            <td><input id="username" type="text" name="username"></td>
        </tr>
        <tr>
            <td>密&nbsp;&nbsp;码:</td>
            <td><input id="pswd1" type="password" name="userpswd"></td>
        </tr>
        <tr>
            <td>确&nbsp;&nbsp认&nbsp;&nbsp密&nbsp;&nbsp;码:</td>
            <td><input id="pswd2" type="password" name="userpswd2" id="userpswd2"></td>
        </tr>
        <tr>
            <td><input id="regist" type="button" value="注册" name="login"></td>
            <td><input type="reset" value="重置" name="reset"></td>
        </tr>
    </table>
    <p align="center"><a href="login" color=blue>登录</a></p>
</form>

</body>
<script>
    $(function(){
        $("#regist").click(function(event){
            debugger;
            usercode = $("#usercode").val();
            username = $("#username").val();
            pswd1 = $("#pswd1").val();
            pswd2 = $("#pswd2").val();
            if(usercode == "" || username == "" || pswd1 == "" || pswd2 == "" ){
                alert("注册信息不能有空项");
            }else if (pswd1 != pswd2){
                alert("两次输入的密码不一致");
            }else{
                $("#registform").submit();
            }
        });
    });



</script>
</html>