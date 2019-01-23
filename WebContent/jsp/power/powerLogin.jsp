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
<title>Insert title here</title>
<script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
        
            <div class="logo_box">
                <h1>权限中心</h1>
                <form name="f" method="post" action="<%=basePath%>power/powerLogin.do">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input  id="username"  name="username" type="text" placeholder="用户名">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                         <input id="pwd"  name="pwd" type="password" placeholder="密码">
                    </div>
                    <div class="mb2"><button type="submit" id="logins" >登录</button></div>   <!-- onclick="login()" -->
                </form>
                <h1 style="color:red">${msg}</h1>
                <h1 style="color: green">${please}</h1>
            </div>

            <div class="foots">
            
            </div>
        </div>
    </div>

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
              alert(data);
              if("success" == data){
            	  window.location.href="<%=basePath%>power/entryPowerIndex.do?username="+username+"&pwd="+pwd;
            	 }
            },
			error : function(data) {
				alert('error!');
			}

		});
}  

	 
</script>
</html>