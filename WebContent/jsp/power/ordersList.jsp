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
	
<style type="text/css">
	.butt{   
	    min-height: 20px;   
	    background-color: #4a77d4;   
	    border: 1px solid #3762bc;   
	    color: #fff;   
	    font-size: 0.5rem;   
	    line-height: normal;   
	    border-radius: 12px;   
	    margin: 0 0 0 20px;   
	}
</style>
</head>
<body>
<jsp:include page="powerIndex.jsp" flush="true"/>
	<div class="newCaozuo">
		<div class="content">
			<div style="background-color: #8e93982e">
				<span style="font-size: 1.2rem;color: #ccccef;"><span style="color: #9cf9e0;">菜单定位：</span>${power.actionType.actionTypeName} --> ${power.action.action}</span>
				<div class="content">
					<div>
						<div style='width:270px; margin: 1rem 0px 0px 25rem;color: #d1cff1;font-size: 23px;'>订单统计</div>
						<table cellpadding="0" cellspacing="0" border="1">
							<thead>
								<tr>
									<th>id</th>
									<th>用户</th>
									<th>订单号</th>
									<th>订单金额</th>
									<th>订单支付状态</th>
									<th>订单下单方式</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ordersList}"  var="oList" varStatus="num">
								<tr>
									<td>${oList.id}</td>
									<td>${oList.userName}</td>
									<td>${oList.orderNo}</td>
									<td>${oList.ordersPrice}</td>
									<td>${oList.orderPayTypeDesc}</td>
									<td>${oList.orderSourceDesc}</td>
									<td>${oList.createTime}</td>
									<td>
										<button class="detail" id="detail_${oList.id}" onclick="detail(${oList.id})">详情</button>
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					<div class="addPtype"></div>
				</div>
				
				
			</div>	
		</div>
	</div>
<script type="text/javascript">

</script>
</body>
</html>