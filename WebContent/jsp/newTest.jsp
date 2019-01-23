<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<p>自动刷新(每5秒):</p>
<% response.setIntHeader("Refresh", 5); %>
<%
    Integer hitsCount = 
      (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* 第一次访问 */
       out.println("欢迎访问hanley页面!");
       hitsCount = 1;
    }else{
       /* 返回访问值 */
       out.println("欢迎访问hanley页面!");
       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
%>

<p>页面访问量为: <%= hitsCount%></p>
<input type="hidden" name="hitsCount" value="<%= hitsCount%>"/>


Hello World!<br/>
<%
out.println("Your IP address is " + request.getRemoteAddr() +" --"+SessionScope.class);
%>
<br/>
时间戳：<% out.println( session.getCreationTime()); %>
<br/>
 今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</body>
</html>