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
		<div class="content">
			<div style="margin:5px;width:400px;height:440px;background-color: #8e93982e">
				<p style='margin: 20px 0px 0px 280px;color: #d1cff1;font-size: 23px;'>姓名：</p>
				<p style='margin: 20px 0px 0px 280px;color: #d1cff1;font-size: 23px;'>性别：</p> 
				<p style='margin: 20px 0px 0px 280px;color: #d1cff1;font-size: 23px;'>年龄：</p>
				<p style='margin: 20px 0px 0px 280px;color: #d1cff1;font-size: 23px;'>密码：</p>
				<p style='margin: 20px 0px 0px 280px;color: #d1cff1;font-size: 23px;'>职位：</p>
			</div>	
			<div style="margin:5px;width:400px;height:440px;background-color: #58c566">
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='username' id='username'>
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='sex' id='sex'>
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='age' id='age'>
				<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='password' id='password'>
				<select style='margin: 18px 0px 0px 8px;height:32px;color: #000000;font-size: 23px;' name='positionId' id='positionId'>
					<option value="0">--请选择职位--</option>
					<c:forEach items="${positionList}" var="plist">
						<option value="${plist.id}">${plist.position}</option>
					</c:forEach>
				</select><br>
				<input style='margin: 18px 0px 0px 8px' type="checkbox" name="checkbox" id="checkbox">是否同意把职位的权限赋给用户
			</div>
		</div>
		<div >
			<button style="margin: 5px;color: #7f62ea;font-size: 20px; background-color: #f5f0f7;width: 130px;" onclick="submitCaozuo(${userId},${actionId})">确定</button>
		</div> 
	</div>
</div>

<script type="text/javascript">

	/* $(document).on("click","input[name=checkbox]",function(){
  	 	$(this).prop("checked");
  	}); */

	function submitCaozuo(userId,actionId){
		var username = $("#username").val();
		var sex = $("#sex").val();
		var age = $("#age").val();
		var password = $("#password").val();
		var positionId = $("#positionId").val();
		
		var isFlag = document.getElementById("checkbox").checked;
		
		/* if($("input[name=checkbox]:checked")){
			var isFlag = true;
		}else{
			var isFlag = false;
		}  */
		
		
		alert(isFlag);
		
	     $.ajax({
	  	   	 url:"<%=basePath%>power/addUser.do",
	         type:"POST",
	         data: {"userId":userId,"actionId":actionId,"username":username,"sex":sex,"age":age,"password":password,"positionId":positionId,"isFlag":isFlag},
	         
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