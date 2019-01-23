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
<div style="background-color: #5c7324">
	<div class="content">
		<div style="margin:5px;width:800px;height:440px;background-color: #05101d">
			<p style='margin: 20px 0px 0px 340px;color: #d1cff1;font-size: 23px;'>职员列表</p>
			<c:forEach items="${userList}" var="list">
				<p style='margin: 20px 0px 0px 117px;font-size: 23px;'><a href="<%=basePath%>power/showUserDetail.do?userId=${list.id}&userName=${list.username}" style="color: #d1cff1;text-decoration:none;" >${list.username}<span style="font-size: 12px;color: #adb5d2;">(${list.position} ${list.actionNum}个权限)</span></a></p>
			</c:forEach>
		</div>	
	</div>
	<%-- <div >
		<button style="float:left;margin: 5px;color: #7f62ea;font-size: 20px; background-color: #f5f0f7;margin: 5px 0 0 640px;width: 130px;" onclick="submitCaozuo(${userId},${actionId})">确定</button>
	</div>  --%>
</div>


<script type="text/javascript">
	<%-- function submitCaozuo(userId,actionId){
		var action = $("#action").val();
		var actionPath = $("#actionPath").val();
		
		$.ajax({
	 	   	 url:"<%=basePath%>power/powerAddAction.do",
	        type:"POST",
	        data: {"userId":userId,"actionId":actionId,"action":action,"actionPath":actionPath},
	        
	        success:function(data){
	       	 alert(data);
	            //window.location.reload();
	          },
				error : function(data) {
					alert('error!');
				}
	
			});
	  
	} --%>
</script>
</body>
</html>