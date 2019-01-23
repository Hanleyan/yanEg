<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<!-- 顶部开始 -->
	    <div class="container">
	        <div class="logo"><a href="./index.html">个人库管理</a></div>
	        <div class="left_open">
	            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
	        </div>
	
	        <ul class="layui-nav right" lay-filter="">
	          <li class="layui-nav-item">
	            <a href="javascript:;">admin</a>
	            <dl class="layui-nav-child"> <!-- 二级菜单 -->
	              <dd><a href="./login.jsp">退出</a></dd>
	            </dl>
	          </li>
	
	        </ul>
	        
	    </div>
	<!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>商安信</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="<%=basePath%>jsp/pamSearch.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人查询</cite>

                        </a>
                    </li >
                </ul>
                <%--<ul class="sub-menu">
                    <li>
                        <a _href="bibibaba/appUser/userList.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户管理</cite>
                            
                        </a>
                    </li >
                    <li>
                        <a _href="bibibaba/order/orderPage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>订单管理</cite>
                            
                        </a>
                    </li>
                    <li>
                        <a _href="bibibaba/rate/ratingPage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>评估管理</cite>

                        </a>
                    </li>
                </ul>--%>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>资方</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="bibibaba/inter_user/interUserPage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>资方列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>还款信息</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="order-list.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>订单状态</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="bibibaba/risk/riskuserList.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>风险中心</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>SP管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="bibibaba/dealer/dealerPage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>SP列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="cate.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>车辆管理</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="cate.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>保险信息</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="cate.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>维修、保养信息</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>城市联动</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="city.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>三级地区联动</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>系统工具</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="sys/encrypt/EncryptLocalpage.do">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>Des3加密工具</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="admin-role.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>加密工具2</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="admin-cate.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>加密工具3</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="admin-rule.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>加密工具4</cite>
                        </a>
                    </li >
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>系统设置</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="echarts1.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置1</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="echarts2.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置2</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts3.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置3</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts4.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置4</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts5.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置5</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts6.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置6</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts7.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置7</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="echarts8.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>系统设置8</cite>
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>图标字体</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="unicode.html">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>图标对应字体</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>查询首页</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <%--<iframe src='./welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>--%>
                    <iframe src='<%=basePath%>jsp/pamSearch.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">BIBIBABA RISKLINK 详情<a href="http://www.zenwide.cn/" target="_blank">汽车金融</a></div>
    </div>
    <!-- 底部结束 -->

</body>
</html>