<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<div style="margin:5px;width:800px;height:2000px;background-color: #8e93982e">
				<p style='margin: 20px 0px 0px 340px;color: #d1cff1;font-size: 23px;'>${userName}的权限(${userActListSize})</p>
				<div >
					<button style="color: #7f62ea;font-size: 20px; margin: 5px 0 0 640px;width: 100px;" onclick="submitCaozuo(${userId},${actionId},${execUserId},'${userName}')">确定修改</button>
				</div><!-- float:left; background-color: #f5f0f7;-->
				
					<p style='margin: 20px 0px 0px 117px;color: #d1cff1;font-size: 23px;'><input type="checkbox" id="checkboxAll" name="checkboxAll">全选/全不选</p>
				<c:forEach items="${allActionList}" var="list" varStatus="num">
					<p style='margin: 20px 0px 0px 117px;color: #d1cff1;font-size: 23px;'>
						<input type="checkbox" id="checkbox" name="checkbox" value="${list.id}" <c:forEach items="${userActList}" var="ulist"><c:if test="${list.id == ulist.id}">checked</c:if></c:forEach>>
						<span style="font-size:15px;color: #b0d07d;">(${num.index+1})</span>${list.action}
					</p>
				</c:forEach>
				
			</div>	
		</div>
	</div>
<script type="text/javascript">

    //全选 (要用input中name属性)
    $(document).on("click","input[name=checkboxAll]",function(){
    	 var sta = $(this).prop("checked");
    	 $("input[name=checkbox]").prop("checked",sta);
       
    });
    $(document).on("click","input[name=checkbox]",function(){
    	 if($("input[name=checkbox]:checked").length==$("input[name=checkbox]").length){
    		 $("input[name=checkboxAll]").prop("checked",true);
    	 }else{
    		 $("input[name=checkboxAll]").prop("checked",false);
    	 }
    });
	   	
    //修改用户权限
	function submitCaozuo(userId,actionId,execUserId,userName){
    	alert("来了");
    	var actionIds = [];
    	$("input[name=checkbox]:checked").each(function() {
			actionIds.push($(this).val());
		});
    	alert("actionIds:"+actionIds);
    	if(confirm("您确定修改"+userName+"的权限吗？")){
			//window.location.href="SystemdeleteUser.do?ids=${u.id}"+ids;
			$.ajax({
		  	   	 url:"<%=basePath%>power/updateUserPower.do",
		         type:"POST",
		         traditional : true,//数组
		         data: {"userId":userId,"actionId":actionId,"execUserId":execUserId,"actionIds":actionIds},
		         
		         success:function(data){
		        	 alert(data);
		             //window.location.reload();
		           },
					error : function(data) {
						alert('error!');
					}

				});
		}
	}
</script>
</body>
</html>