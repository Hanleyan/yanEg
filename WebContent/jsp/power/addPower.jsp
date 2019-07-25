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
<jsp:include page="powerIndex.jsp" flush="true"/>
	<div class="newCaozuo">
		<div class="content" >
			<div style="margin:5px;width:400px;height:440px;background-color: #8e93982e">
				<p style='margin: 20px 0px 0px 218px;color: #d1cff1;font-size: 23px;'>选择权限类型：</p>
				<p style='margin: 20px 0px 0px 218px;color: #d1cff1;font-size: 23px;'>输入权限名称：</p>
				<p style='margin: 20px 0px 0px 195px;color: #d1cff1;font-size: 23px;'>输入权限英文名：</p>
			</div>	
			<div style="margin:5px;width:400px;height:440px;background-color: #8e93982e">
				<select type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='actionTypeId' id='actionTypeId'>
					<option value="0">--请选择权限类型--</option>
					<c:forEach items="${actionTypeList}" var="atlist">
						<option value="${atlist.id}">${atlist.actionTypeName}</option>
					</c:forEach>
				</select>
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='action' id='action'>
				<span id="need1" style="color: red;font-size: 3px;"></span>
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='actionPath' id='actionPath'>
				<span id="need2" style="color: red;font-size: 3px;"></span>
			</div>
		</div>
		<div>
			<button type="button" style="margin: 5px;color: #7f62ea;font-size: 20px; background-color: #f5f0f7;width: 130px;" onclick="submitCaozuo(${userId},${actionId})">确定</button>
		</div>
	</div> 
</div>


<script type="text/javascript">

	function submitCaozuo(userId,actionId){
		var action = $("#action").val();
		var actionPath = $("#actionPath").val();
        var actionTypeId = $("#actionTypeId").val();
		
		if(action == null || action ==""){
	 		 $("#need1").html("*必填项");
	 		 return;
	 	 }
		if(actionPath == null || actionPath ==""){
			 $("#need2").html("*必填项");
			 return;
		 }
	     $.ajax({
	  	   	 url:"<%=basePath%>power/powerAddAction.do",
	         type:"POST",
	         data: {"userId":userId,"actionId":actionId,"action":action,"actionPath":actionPath,"actionTypeId":actionTypeId},
	         
	         success:function(data){
	        	 alert("修改权限后请重新登录！");
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