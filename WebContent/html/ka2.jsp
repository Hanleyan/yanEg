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
<style>  
body {margin:25px;}   
div.polaroid {   
  width: 40%;   
  background-color: white;   
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);   
  margin-bottom: 25px;   
}   
div.container {   
  text-align: center;   
  padding: 10px 20px;   
}   
</style>  
</head>  
<body>  
<h2>ååºå¼å¡ç</h2>  
<div class="polaroid">  
	
  <img src="<%=basePath%>image/2018032617295519.jpg" alt="Norway" style="width:100%">  
  <div class="container" style="background-color: blue">  
    <p>The Troll's tongue in Hardanger, Norway</p>  
  </div>  
</div>  
</body>  
</html>  