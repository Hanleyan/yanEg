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
						<div style='width:270px; margin: 20px 0px 0px 170px;color: #d1cff1;font-size: 23px;'>权限类型列表<button class="butt" onclick="addPowerType()">增加一个权限类型</button></div>
						<table cellpadding="0" cellspacing="0" border="1">
							<thead>
								<tr>
									<th>id</th>
									<th>类型名称</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${actionTypeList}"  var="atList" varStatus="num">
									<%-- <p style='margin: 20px 0px 0px 117px;font-size: 23px;'><a href="<%=basePath%>power/showUserDetail.do?execUserId=${list.id}&userName=${list.username}" style="color: #d1cff1;text-decoration:none;" >${list.username}<span style="font-size: 12px;color: #adb5d2;">(${list.position} ${list.actionNum}个权限)</span></a></p>
									 --%><tr>
									<td>${atList.id}</td>
									<td><input type="text" id="actionTypeName_${atList.id}" name="actionTypeName" value="${atList.actionTypeName}" disabled="disabled" style="background: #ffffff05;color: #efefef;font-size: 1rem;border: 1px;" ></td>
									<td>${atList.createTime}</td>
									<td>
										<button class="updatePtype" id="updatePtype_${atList.id}" onclick="updatePtype(${atList.id})">修改</button>
										<button class="butdo" id="butFalse_${atList.id}" onclick="subFalse(${atList.id})">取消</button>
										<button class="butdo" id="butTrue_${atList.id}" onclick="subTrue(${atList.id})">确定</button>
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
	$(".butdo").hide();


	//新增权限类型
	function addPowerType(){
		$(".bigBox").remove();//防止多个bigBox出现
		var addPtypeHtml=" <div class='bigBox' style='background-color: #023061;color: rgb(184, 201, 210);width: 15rem;height: 7.5rem;margin: 0 0 0 1.2rem;'> <h3 style='text-align: center;padding: 2px 0;'>请输入权限类型</h3> <div class='txtTip' style='text-align: center;padding: 2px 0;'>"
		+"<input type='text' name='powerTypeName'></div>"+
		" <div style='text-align: center;margin: 0 auto;'><span class='errMag' style='color:red'></span></div>"+
		"  <div style='text-align: center;margin: 0 auto;'> <span class='BoxFal'>取消&nbsp;&nbsp;</span><span class='BoxSur'>&nbsp;&nbsp;确认</span></div></div>";
        $(".addPtype").append(addPtypeHtml);
        
        $(".BoxFal").click(function () {
            $(".bigBox").hide();
        });
        
        $(".BoxSur").click(function () {
            var powerTypeName = $("input[name=powerTypeName]").val();
            if(null == powerTypeName || powerTypeName == ""){
            	$(".errMag").text("权限类型不能为空！");
            	return;
            }
            $.ajax({
                type:"POST", //请求方式
                data: {powerTypeName:powerTypeName},   
                url: "<%=basePath%>/power/addPowerType.do",
                cache : true, //(默认: true,dataType为script和jsonp时默认为false) jQuery 1.2 新功能，设置为 false 将不缓存此页面。
                
                success:function(data){
                	var jsonReturnObject = JSON.parse(data);
                    if(jsonReturnObject.code=="1"){
                        alert(jsonReturnObject.message);
                        window.location.reload();
                    }else{
                    	alert(jsonReturnObject.message);
                    }
                }
            });
        });
	}
	
	/*点击修改*/
	function updatePtype(id){
		$("#actionTypeName_"+id).attr("disabled",false);
		$("#actionTypeName_"+id).css({"background":"#f5f5f5","color":"#020202"});
		
		$("#butFalse_"+id).show();
		$("#butTrue_"+id).show();
		$("#updatePtype_"+id).hide();
	}
	/*取消修改*/
	function subFalse(id){
		window.location.reload();
		/* $("#actionTypeName_"+id).attr("disabled",true);
		
		$("#butFalse_"+id).hide();
		$("#butTrue_"+id).hide();
		$("#updatePtype_"+id).show(); */
	}
	/*确认修改  单个修改权限类型*/  
	function subTrue(id){
		var actionTypeName = $("#actionTypeName_"+id).val();
		if(null == actionTypeName || actionTypeName ==""){
			alert("类型名称不能为空");
			return;
		}
		$.ajax({
            type:"POST", //请求方式
            data: {id:id,powerTypeName:actionTypeName},   
            url: "<%=basePath%>/power/updatePowerType.do",
            cache : true, //(默认: true,dataType为script和jsonp时默认为false) jQuery 1.2 新功能，设置为 false 将不缓存此页面。
            
            success:function(data){
            	var jsonReturnObject = JSON.parse(data);
                if(jsonReturnObject.code=="1"){
                    /* alert(jsonReturnObject.message); */
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