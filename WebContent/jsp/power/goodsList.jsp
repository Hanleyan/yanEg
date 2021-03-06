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
<style>
	.goods{
	    width: 9rem;
	    height: 7rem;
	    background-color: #ffff0040;
	    padding: 0.5rem;
	    margin: 0.5rem;
	}
	
	.card img {
		width: 100%;
	}
	.card{
		height: 270px;
		width: 260px;
		background-color: white;
		box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		border-radius: 4px;
		display: inline-block;
		margin-left: 18px;
		vertical-align: top;
	}
	.card span {
		font-size: 10px;
		color: #BFBFBF;
		display: block;
		letter-spacing: 2px;
		/* padding: 30px 20px; */
	}
</style>
<body>
<jsp:include page="powerIndex.jsp" flush="true"/>
	<div class="newCaozuo">
		<!-- <div class="content"> -->
			<div style="width:100rem;background-color: #8e93982e">
				<span style="font-size: 1.2rem;color: #ccccef;"><span style="color: #9cf9e0;">菜单定位：</span>${power.actionType.actionTypeName} --> ${power.action.action}</span>
				<p style='margin: 2rem 0 0 22rem;color: #d1cff1;font-size: 23px;'>商品列表</p>
				<div style="display: flex;flex-direction:row">
					<div class="goodsLeft" style="background-color: #ff99000f;width: 10rem" >
						<c:forEach items="${goodsTypeList}" var="list">
							<p style='margin: 20px 0px 0px 10px;color: #d1cff1;font-size: 23px;'>
								<a href="javascript:;" onclick=getGoodsList(${list.id}) style="color: #d1cff1;text-decoration:none;">${list.goodsTypeName}
								</a>
							</p>
						</c:forEach>
					</div>
					<div class="goodsright" style="display: flex;flex-direction:column;width:88rem;background-color: #dcdcdc17; margin: 1rem 0 0 1rem;color: #d1cff1">
						<!-- 商品展示 -->
						<c:forEach items="${goodsArrayList}" var="dList">
							<div style="display: flex;flex-direction:row;margin-bottom: 1rem;">
								<c:forEach items="${dList}" var="goodsList">
									<div class="card" style="background-color: #1e10f37d">
									<img  src="<%=basePath%>image/2018032617295519.jpg" alt=""/>
								    <div>
								         <!-- <span> -->
									        <ul style="padding: 0 0 0 0.5rem">
									        	<li><label style="color:#e4393c;font-size: 20px;">￥${goodsList.goodsPrice}</label></li>
												<li><label style="color:yellow; font-size: 12px;">&nbsp;${goodsList.goodsName}</label></li>
									        	<li><label style="font-size: 12px;color: #d1cff1;"><strong>&nbsp;${goodsList.storage}条评价</strong></label></li>
									        </ul>
								         <!-- </span> 	 -->				    
								    </div>
								</div>
								</c:forEach>
							</div>
						</c:forEach>
						
						
					</div>
				</div>
				
			</div>	
		<!-- </div> -->
	</div>
<script type="text/javascript">
	
	function getGoodsList(goodsTypeId){
		alert(goodsTypeId);
		$.ajax({
			url:"<%=basePath%>power/goodsListByGoodsTypeId.do",
			data:{goodsTypeId:goodsTypeId},
			type:"POST",
			success:function(data){
				console.info(data);//json形式
				console.info(JSON.parse(data));//转为对象
				alert(JSON.parse(data))
				
			},
			error:function(){
				
			}
		});
		
	}
</script>
</body>
</html>