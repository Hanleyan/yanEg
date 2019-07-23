<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>LOGIN</title>
<script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    
</script>
<style>
input {
	border: 1px solid #ccc;
	padding: 7px 0px;
	border-radius: 3px;
	padding-left: 5px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

input:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
		
}
.user{   
    width: 270px;   
    min-height: 20px;   
    display: block;   
   /*  background-color: #4a77d4;    */
    border: 1px solid #3762bc;   
    /* color: #fff;    */
    padding: 9px 14px;   
    font-size: 15px;   
    line-height: normal;   
    border-radius: 12px;   
    margin: 20px 0 0 0;   
}

.butt{   
    width: 300px;   
    min-height: 20px;   
    display: block;   
    background-color: #4a77d4;   
    border: 1px solid #3762bc;   
    color: #fff;   
    padding: 9px 14px;   
    font-size: 15px;   
    line-height: normal;   
    border-radius: 12px;   
    margin: 20px 0 0 0;   
}


</style>
</head>
<body style="background-image: url(<%=basePath%>/image/backgroup_image.jpg)">

      <div style="margin: 262px 0px 0px 700px;width:300px">
          <h1 style="color: lavenderblush;margin: 0 0 40px 75px;">联盟米乐多</h1>
          <form method="post" action="<%=basePath%>power/powerLogin.do">
              <div>
                  <input class="user" id="username"  name="username" type="text" placeholder="用户名">
              </div>
              <div >
                   <input class="user" id="pwd"  name="pwd" type="password" placeholder="密码" >
              </div>
              <div>
              	<button class="butt" type="button" id="logins" onclick="login()">登录</button>
              </div>   <!-- onclick="login()" -->
          </form>
          <h1 id="errMsg" style="color:red"></h1>
         
      </div>
  
</body>
<script type="text/javascript">
  function login() {
  	 var username=$("#username").val();
  	 var pwd=$("#pwd").val(); 
  	 
      $.ajax({
   	   	  url:"<%=basePath%>power/powerLogin.do",
          type:"POST",
          data: {username:username,pwd:pwd},
          success:function(data){
              if("success" == data){
            	  window.location.href="<%=basePath%>power/entryPowerIndex.do";
               }else{
            	   $("#errMsg").text(data);
               }
          },
		  error : function(data) {
			alert('error!');
		 }

		});
}

	 
</script>
</html>