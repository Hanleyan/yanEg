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
		font-size: 12px;
		color: #BFBFBF;
		display: block;
		letter-spacing: 2px;
		padding: 30px 20px;
	}
</style>  
</head>  
<body>  
<h2>ååºå¼å¡ç</h2>  

 <div class="card" style="background-color: #0000ff69">
    <img  src="<%=basePath%>image/2018032617295519.jpg" alt=""/>
    <div>
        <span>æç ´ä¼ä¸é´å£åï¼æä¾ä¾¿æ·çæ¥å¥æ¹å¼ï¼å®ç°ä¸ä¼ä¸ï¼ä¸ååçãä¸åç±»åé´çå®¶çµçæ°æ®äºèäºéåæ°æ®åå</span>
    </div>
</div>
  
</body>  
</html>  