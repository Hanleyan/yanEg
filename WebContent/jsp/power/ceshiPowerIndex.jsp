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
<body class="body">

	<div class="topage">
		<div class="topContent">您的身份：<span style="color:#CCFFFF">${position.position}</span></div>
	</div>

	<div class="content">
		<div class="contentLeft">
			<div class="topContent">您的权限</div>
			<div class="buttonColumn">
				<a href="" class="lookup">所有的职位</a>
				<a href="lookup">所有的职员</a>
			</div>
			<hr>
			<div class="buttonColumn">
				 <c:forEach items="${actionList}" var="aList" varStatus="num"> 
					 <a href="javascript:void(0)" class="powerAction" onclick="caozuo(${user.id},${aList.id},'${aList.actionPath}')"> (${num.index+1})  ${aList.action} </a> 
				 </c:forEach> 
			</div>
		</div>

		<div class="contentRight">
			<div class="topContent">您的操作</div>
			<div class="powerCaozuo">
				<%-- <iframe tab-id="1" src='<%=basePath%>jsp/power/addPosition.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
				<iframe tab-id="2" src='<%=basePath%>jsp/power/addPosition1.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe> --%>
			</div>
			<div class="ContentSub">
			</div>
		</div>
	</div>
</body>
<script>
function caozuo(userId,actionId,actionPath){
	$(".powerCaozuo").empty();

	var actPath = '<%=basePath%>'+'jsp/power/'+actionPath;
	
	//alert(userId+" "+actionId+" "+actPath);
	
	$(".powerCaozuo").append("<iframe class='iframeStyle' src='"+actPath+"' frameborder='0' scrolling='yes'></iframe>");
	
	
}


</script>
</html>