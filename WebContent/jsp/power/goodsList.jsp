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
<jsp:include page="powerIndex.jsp" flush="true"/>
	<div class="newCaozuo">
		<div class="content">
			<div style="margin:5px;width:800px;height:440px;background-color: #8e93982e">
				<p style='margin: 20px 0px 0px 340px;color: #d1cff1;font-size: 23px;'>商品列表</p>
				<c:forEach items="${goodsTypeList}" var="list">
					<p style='margin: 20px 0px 0px 117px;color: #d1cff1;font-size: 23px;'>
						<a href="<%=basePath%>power/showUserDetail.do?execUserId=${list.id}&userName=${list.goodsTypeName}" style="color: #d1cff1;text-decoration:none;">${list.goodsTypeName}
							<span style="font-size: 12px;color: #adb5d2;">(${list.goodsTypeName})</span>
						</a>
					</p>
				</c:forEach>
			</div>	
		</div>
	</div>
<script type="text/javascript">

</script>
</body>
</html>