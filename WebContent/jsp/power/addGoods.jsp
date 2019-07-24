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
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品名称：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品类型：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品库存量：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品供货量：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品单价(元)：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品会员单价(元)：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>折扣(元)：</p>
				<p style='margin: 20px 0 0 280px;color: #d1cff1;font-size: 23px;'>商品简介：</p>
			</div>
			<div style="margin:5px;width:400px;height:440px;background-color: #58c566">
				<form id="form">
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='goodsName' id='goodsName'>
					<select style='margin: 18px 0px 0px 8px;height:32px;color: #000000;font-size: 23px;' name='goodsTypeId' id='goodsTypeId'>
						<option value="0">--请选择商品类型--</option>
						<c:forEach items="${goodsTypeList}" var="plist">
							<option value="${plist.id}">${plist.goodsTypeName}</option>
						</c:forEach>
					</select>
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='storage' id='storage'>
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='goodsAmout' id='goodsAmout'>
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='goodsPrice' id='goodsPrice'>
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='goodsVipPrice' id='goodsVipPrice'>
					<input type='text' style='margin: 18px 0px 0px 8px;color: #000000;font-size: 23px;' name='goodsDiscount' id='goodsDiscount'>
					<textarea name='goodsDescription' id='goodsDescription' cols="30" rows="10"></textarea>
					<br>
				</form>

			</div>
		</div>
		<div >
			<button style="margin: 5px;color: #7f62ea;font-size: 20px; background-color: #f5f0f7;width: 130px;" onclick="submitCaozuo(${actionId})">确定</button>
		</div> 
	</div>
</div>

<script type="text/javascript">

	/* $(document).on("click","input[name=checkbox]",function(){
  	 	$(this).prop("checked");
  	}); */

	function submitCaozuo(actionId){
		var goodsName = $("#goodsName").val();
		var goodsTypeId = $("#goodsTypeId").val();
		var storage = $("#storage").val();
		var goodsAmout = $("#goodsAmout").val();
		var goodsPrice = $("#goodsPrice").val();
		var goodsVipPrice = $("#goodsVipPrice").val();
		var goodsDiscount = $("#goodsDiscount").val();
		var goodsDescription = $("#goodsDescription").val();

		//var goodsInfo = {goodsName: goodsName, goodsTypeId: goodsTypeId, storage:storage,goodsAmout:goodsAmout,goodsPrice:goodsPrice,goodsVipPrice:goodsVipPrice,goodsDiscount:goodsDiscount,goodsDescription:goodsDescription};
		
	     $.ajax({
	  	   	 url:"<%=basePath%>power/addGoods.do",
	         type:"POST",
	         //data: {"userId":userId,"actionId":actionId,"username":username,"sex":sex,"age":age,"password":password,"positionId":positionId,"isFlag":isFlag},
	         data:{goodsName: goodsName, goodsTypeId: goodsTypeId, storage:storage,goodsAmout:goodsAmout,goodsPrice:goodsPrice,goodsVipPrice:goodsVipPrice,goodsDiscount:goodsDiscount,goodsDescription:goodsDescription,actionId:actionId},
	         success:function(data){
	        	 alert(data);
	        	 window.location.reload();
	           },
				error : function() {
					alert('error!');
				}
	
			});
		
	}
</script>
</body>
</html>