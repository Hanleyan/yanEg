<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文件上传</title>
</head>
<body>
 <form action="<%=basePath%>upload.do" encType="multipart/form-data" method="post">
 
	<input type="file" id="newfile" name="newfile1" >
	<input type="file" id="newfile" name="newfile2" >
	<input type="submit">  
	
	<h2 style="color:green">${msg} </h2>
</form>


</body>
<script type="text/javascript">
	
</script>
</html>