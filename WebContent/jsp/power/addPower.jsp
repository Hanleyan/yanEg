<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>jsp/power/power.css">
</head>
<body>
<div>
	<div class="content">
		<div style="margin:5px;width:400px;height:440px;background-color: #1679dc">
			<p style='margin: 20px 0px 0px 218px;color: #d1cff1;font-size: 23px;'>请添加权限：</p>
			<p style='margin: 20px 0px 0px 117px;color: #d1cff1;font-size: 23px;'>请添加权限页面jsp名：</p>
		</div>	
		<div style="margin:5px;width:400px;height:440px;background-color: #58c566">
			<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='action' id='action'>
			<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='actionPath' id='actionPath'>
		</div>
	</div>
	<div >
		<button style="float:left;margin: 5px;color: #7f62ea;font-size: 20px; background-color: #f5f0f7;margin: 5px 0 0 640px;width: 130px;" onclick="submitCaozuo(${userId},${actionId})">确定</button>
	</div> 
</div>


<script type="text/javascript">
function submitCaozuo(userId,actionId){
	var action = $("#action").val();
	var actionPath = $("#actionPath").val();
	
     $.ajax({
  	   	 url:"<%=basePath%>power/powerAddAction.do",
         type:"POST",
         data: {"userId":userId,"actionId":actionId,"action":action,"actionPath":actionPath},
         
         success:function(data){
        	 alert(data);
             window.location.reload();
           },
			error : function(data) {
				alert('error!');
			}

		});
	
}
</script>
</body>
</html>