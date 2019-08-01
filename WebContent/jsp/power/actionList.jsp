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
						<div style='width:270px; margin: 20px 0px 0px 340px;color: #d1cff1;font-size: 23px;'>权限类型列表<button class="butt" onclick="addPower()">增加一个权限</button></div>
						<table cellpadding="0" cellspacing="0" border="1">
							<thead>
								<tr>
									<th>id</th>
									<th>权限名称</th>
									<th>English</th>
									<th>父级</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${actionList}"  var="aList" varStatus="num">
									<tr>
									<td>${aList.id}</td>
									<td><input type="text" id="actionName_${aList.id}"  value="${aList.action}" disabled="disabled" style="background: #ffffff05;color: #efefef;font-size: 1rem;border: 1px;" ></td>
									<td><input type="text" id="actionPath_${aList.id}"  value="${aList.actionPath}" disabled="disabled" style="background: #ffffff05;color: #efefef;font-size: 1rem;border: 1px;" ></td>
									<%-- <td>${aList.actionTypeName}</td> --%>
									<td><select id="actionTypeId_${aList.id}" disabled="disabled" style="background: #ffffff05;color: #efefef;font-size: 1rem;border: 1px;">
										<option>${aList.actionTypeName}</option>
										<c:forEach items="${actionTypeList}" var="at">
											<option value="${at.id}">${at.actionTypeName}</option>
										</c:forEach>
									</select> </td>
									<td>${aList.createTime}</td>
									<td>
										<button class="updatePtype" id="updatePtype_${aList.id}" onclick="updatePtype(${aList.id})">修改</button>
										<button class="butdo" id="butFalse_${aList.id}" onclick="subFalse(${aList.id})">取消</button>
										<button class="butdo" id="butTrue_${aList.id}" onclick="subTrue(${aList.id})">确定</button>
									</td>
								</tr>
								</c:forEach>
								
							</tbody>
						</table>
					</div>
					<div class="addPower"></div>
				</div>
				
				
			</div>	
		</div>
	</div>
<script type="text/javascript">
	$(".butdo").hide();


	//新增权限
	function addPower(){
		$(".bigBox").remove();//防止多个bigBox出现
	
		var addPowerHtml=" <div class='bigBox' style='background-color: #023061;color: rgb(184, 201, 210);width: 18rem;height: 16rem;margin: 0 0 0 1.2rem;'><div ><h3 style='text-align: center;padding: 2px 0;'>新增权限</h3><div style='padding:0.5rem'>选择权限类型<select type='text' name='actionTypeId' id='actionTypeId' style='height:1.5rem;margin-left:1rem;'><option value='0'>--请选择权限类型--</option><c:forEach items='${actionTypeList}' var='atlist'><option value='${atlist.id}'>${atlist.actionTypeName}</option></c:forEach></select></div><div style='padding:0.5rem'>请输入权限名称<input type='text' name='action'></div><div style='padding:0.5rem'>输入权限英文名<input type='text' name='actionPath'></div><div style='text-align: center;margin: 0 auto;'><span class='errMag' style='color:red'></span></div><div style='text-align: center;margin: 0 auto;'> <span class='BoxFal'>取消&nbsp;&nbsp;</span><span class='BoxSur'>&nbsp;&nbsp;确认</span></div>";
		console.info(addPowerHtml);
		
		$(".addPower").append(addPowerHtml);
        
        $(".BoxFal").click(function () {
            $(".bigBox").hide();
        });
        
        $(".BoxSur").click(function () {
            /* var actionTypeId = $("input[name=actionTypeId]").val(); */
            var actionTypeId = $("#actionTypeId").val();
            var action = $("input[name=action]").val();
            var actionPath = $("input[name=actionPath]").val();
            
            if( actionTypeId == "0"){
            	$(".errMag").text("请选择权限类型");
            	return;
            }
            if( null == action || action == ""){
            	$(".errMag").text("权限名称不能为空");
            	return;
            }
            if( null == actionPath || actionPath == ""){
            	$(".errMag").text("权限英文名称不能为空");
            	return;
            }
           
            $.ajax({
            	 url:"<%=basePath%>power/powerAddAction.do",
   	         	 type:"POST",
	   	         data: {"action":action,"actionPath":actionPath,"actionTypeId":actionTypeId}, //"userId":userId,"actionId":actionId,
	   	         
	   	         success:function(data){
	   	        	 alert("修改权限后请重新登录！");
	   	             window.location.reload();
	   	           },
	   				error : function(data) {
	   					alert('error!');
	   				}
	   	
	   			});
        });
	}
	
	/*点击修改*/
	function updatePtype(id){
		$("#actionName_"+id).attr("disabled",false);
		$("#actionName_"+id).css({"background":"#f5f5f5","color":"#020202"});
		
		$("#actionPath_"+id).attr("disabled",false);
		$("#actionPath_"+id).css({"background":"#f5f5f5","color":"#020202"});
		
		$("#actionTypeId_"+id).attr("disabled",false);
		$("#actionTypeId_"+id).css({"background":"#f5f5f5","color":"#020202"}); 
		
		
		$("#butFalse_"+id).show();
		$("#butTrue_"+id).show();
		$("#updatePtype_"+id).hide();
	}
	/*取消修改*/
	function subFalse(id){
		window.location.reload();
	}
	/*确认修改  单个修改权限类型*/  
	function subTrue(id){
		var actionName = $("#actionName_"+id).val(); 
		var actionPath = $("#actionPath_"+id).val();
		var actionTypeId = $("#actionTypeId_"+id).val();  
		  
		if(null == actionName || actionName ==""){
			alert("权限名称不能为空");
			return;
		}
		if(null == actionPath || actionPath ==""){
			alert("英文名称不能为空");
			return;
		}
		if(null == actionTypeId || actionTypeId ==""){
			alert("权限类型必选一个");
			return;
		}
		$.ajax({
            type:"POST", //请求方式
            data: {id:id,actionName:actionName,actionEName:actionPath,actionTypeId:actionTypeId},   
            url: "<%=basePath%>/power/updateAction.do",
            cache : true, //(默认: true,dataType为script和jsonp时默认为false) jQuery 1.2 新功能，设置为 false 将不缓存此页面。
            
            success:function(data){
            	var jsonReturnObject = JSON.parse(data);
                if(jsonReturnObject.code=="1"){
                     alert("权限有变，请重新登入！"); 
                    window.location.reload();
                }else{
                	alert(jsonReturnObject.message);
                }
            }
        });
	}

</script>
</body>
</html>