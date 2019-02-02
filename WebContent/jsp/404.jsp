<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>Document</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="error">
<span>${message}</span>
    <img src="<%=basePath%>/image/404.png" alt="" style="width: 60%;height: 50%;">
</div>
</body>
<script>
    window.onload=function(){
        var designwidth=750;
        var fontSize=100;
        function fontsize(){
            var relWidth=document.documentElement.clientWidth;
            relFontSize=fontSize*relWidth/designwidth;
            document.getElementsByTagName('html')[0].style.fontSize=relFontSize+'px';
        }
        fontsize();
        window.onresize=fontsize;
    }
</script>
</html>

