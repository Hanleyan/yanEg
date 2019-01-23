<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<title>Insert title here</title>
</head>

<body>
	<input type="button" id="btn" value="免费获取验证码" />
	<hr>
	<img src="<%=basePath%>image/500.jpg" data-src="" alt="加在不成功"><hr>
	<img src="<%=basePath%>image/2018032617270390.jpg" alt="加在不成功"><hr>
	<img src="<%=basePath%>image/2018032617295519.jpg" alt="加在不成功"><hr>
	<img src="<%=basePath%>image/BIBIBABA.png" alt="加在不成功"><hr>
	<img src="<%=basePath%>image/redpacket.jpg" alt="加在不成功"><hr>
	
</body>
<script type="text/javascript">
	var wait = 60;
	function time(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value = "免费获取验证码";
			wait = 60;
		} else {

			o.setAttribute("disabled", true);
			o.value = "重新发送(" + wait + ")";
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000)
		}
	}
	document.getElementById("btn").onclick = function() {
		time(this);
	}
</script>



</html>