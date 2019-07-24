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
<link rel="stylesheet" type="text/css" href="<%=basePath%>jsp/power/power.css">

	<link rel="stylesheet" href="<%=basePath%>jquery-treeview-master/jquery.treeview.css" type="text/css"/>
	<script src="<%=basePath%>jquery-treeview-master/jquery.treeview.js" type="text/javascript"></script>

<style type="text/css">
	ul li{
	list-style: none;

}
</style>
</head>
<body class="body">
	<div id="main">
		<ul class="filetree" id="treeview" ><%--class="buttonColumn"--%>
			<c:forEach items="${actionTypeList}" var="atList" varStatus="num">
				<li>
						<%-- <a href="" class="powerAction"> (${num.index+1})  ${atList.actionTypeName} </a>  --%>

					<span class="folder">${atList.actionTypeName}</span><%--class="powerAction"--%>
					<ul><%--class="ul" id="at_${num.index+1}"--%>
						<c:forEach items="${atList.actionList}" var="act">
							<li>
								<span class="file" >${act.action}</span> <%--id="orderPage"--%>
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>

			<%-- <c:forEach items="${sessionScope.USER_RIGHT}" var="aList" varStatus="num">
				<a href="<%=basePath%>power/entryTakeAction.do?userId=${user.id}&actionId=${aList.id}&actionPath=${aList.actionPath}" class="powerAction"> (${num.index+1})  ${aList.action} </a>
			</c:forEach>
			 --%>
		</ul>
	</div>
</body>

<script type="text/javascript">

	/* 根据权限的数量来增加页面高度 */
	var listSize = $("#actionListSize").val(); 
	if(listSize > 22){
		var higt = listSize * 38 + "px";
		$(".cl").css({ "height": higt});
	}
	
	/*$(".ul").hide();*/
	
	/*function atType(at_id){
		alert(at_id);
		var str = $("#at_"+at_id).html();
        console.info(str);
        $("#at_"+at_id).show();
	}*/

	<%-- function caozuo(userId,actionId,actionPath){
		$(".powerCaozuo").empty();
		var actPath = '<%=basePath%>power/entryTakeAction.do?userId='+userId+'&actionId='+actionId+'&actionPath='+actionPath;
		$(".powerCaozuo").append("<iframe class='iframeStyle' src='"+actPath+"' frameborder='0' scrolling='yes'></iframe>");  /* class='iframeStyle' */
		
	} --%>
	


</script>
</html>