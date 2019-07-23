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
	<%-- ${position}
<hr>
${actionList} --%>
	<div class="topage">
		<div class="topContent">欢迎<span style="color:#dad081">${sessionScope.USER_CONTEXT.username}</span>    您的身份：<span style="color:#CCFFFF">${sessionScope.USER_POSITION.position}</span></div>
	</div>

	<div class="content">
		<div class="contentLeft cl"> <!-- class="contentLeft"  style="margin:10px;width:400px;height:1004px;background-color: #003366" -->  <!-- style="height:1004px;" -->
			<div class="topContent">您的权限(${sessionScope.USER_RIGHT_SIZE})</div>
			<input type="hidden" id="actionListSize" value=${sessionScope.USER_RIGHT_SIZE}>
			<div class="buttonColumn">
				<%-- <c:forEach items="${actionList}" var="aList" varStatus="num"> --%>
				<c:forEach items="${sessionScope.USER_RIGHT}" var="aList" varStatus="num">
					<%-- <a href="javascript:void(0)" class="powerAction" onclick="caozuo(${user.id},${aList.id},'${aList.actionPath}')"> (${num.index+1})  ${aList.action} </a>  --%>
					<a href="<%=basePath%>power/entryTakeAction.do?userId=${user.id}&actionId=${aList.id}&actionPath=${aList.actionPath}" class="powerAction"> (${num.index+1})  ${aList.action} </a> 
				</c:forEach>
			</div>
		</div>

		<div class="contentRight cl">
			<div class="topContent">您的操作</div>
			<div class="powerCaozuo">
				
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	/* 根据权限的数量来增加页面高度 */
	var listSize = $("#actionListSize").val(); 
	if(listSize > 22){
		var higt = listSize * 38 + "px";
		$(".cl").css({ "height": higt});   
	}

	<%-- function caozuo(userId,actionId,actionPath){
		$(".powerCaozuo").empty();
		var actPath = '<%=basePath%>power/entryTakeAction.do?userId='+userId+'&actionId='+actionId+'&actionPath='+actionPath;
		$(".powerCaozuo").append("<iframe class='iframeStyle' src='"+actPath+"' frameborder='0' scrolling='yes'></iframe>");  /* class='iframeStyle' */
		
	} --%>
	


</script>
</html>