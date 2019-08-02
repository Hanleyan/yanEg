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
	<script type="text/javascript">
        $(document).ready(function(){
            $("#treeview").treeview({
                toggle: function() {
                    console.log("%s was toggled.", $(this).find(">span").text());
                }
            });
        });
	</script>
	<style type="text/css">
		ul li{
		list-style: none;

	}
	</style>
</head>
<body class="body">
	<%-- ${position}
<hr>
${actionList} --%>
	<div class="topage">
		<div class="topContent">欢迎<span style="color:#dad081">${sessionScope.USER_CONTEXT.username}</span>    您的身份：<span style="color:#CCFFFF">${sessionScope.USER_POSITION.position}</span>
		<a href="<%=basePath%>power/powerLoginOut.do" style="color: #8297a9; float: right;">退出</a></div>
	</div>

	<div class="content">
		<div class="contentLeft cl"> <!-- class="contentLeft"  style="margin:10px;width:400px;height:1004px;background-color: #003366" -->  <!-- style="height:1004px;" -->
			<div class="topContent">您可以操作的权限(${sessionScope.USER_RIGHT_SIZE})</div>
			<input type="hidden" id="actionListSize" value=${sessionScope.USER_RIGHT_SIZE}>
			
			<div id="main">
				<ul class="filetree" id="treeview" style="font-size: 1.2rem" ><%--class="buttonColumn"--%>
					<c:forEach items="${sessionScope.USER_RIGHT_TYPE}" var="atList" varStatus="num">
						<li>
								<%-- <a href="" class="powerAction"> (${num.index+1})  ${atList.actionTypeName} </a>  --%>

							<span class="folder" style="margin: 0 0 5px 2px;">${atList.actionTypeName}</span><%--class="powerAction"--%>
							<ul style="background-color: rgba(3, 255, 194, 0.1);"><%--class="ul" id="at_${num.index+1}"  display: none;--%>
								<c:forEach items="${atList.actionList}" var="act">
									<li>
										<span class="file" style="font-size: 1rem;">
											<a href="<%=basePath%>power/entryTakeAction.do?userId=${sessionScope.USER_CONTEXT.id}&actionId=${act.id}&actionPath=${act.actionPath}" 
												style="color: rgba(160, 199, 212, 0.95);">${act.action}</a>
										</span>

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
			
		</div>

		<div class="contentRight cl">
			<div class="topContent" >您的操作  <span class="tarbar"></span></div>
			<div class="powerCaozuo">
				
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	/* 根据权限的数量来增加页面高度 */
	/* var listSize = $("#actionListSize").val(); 
	if(listSize > 22){
		var higt = listSize * 38 + "px";
		$(".cl").css({ "height": higt});
	} */
	
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