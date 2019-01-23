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
    <title>Rich Man</title>
    <script src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
    <%--<script src="<%=basePath%>js/jquery.js"></script>--%>
    <script src="<%=basePath%>js/time.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />
    <script type="text/javascript">

        function login(){
            var userPhone = $("#userPhone").val();
            var verificationCode = $("#verificationCode").val();
            var dealerid = $("#dealerid").val();
            var authorizationCode = $("#authorizationCode").val();

            if(null == userPhone || userPhone == ""){
                alert("手机号不能为空！");
                return false;
            }
            if(null == verificationCode || verificationCode == ""){
                alert("验证码不能为空！");
                return false;
            }
            if(null == authorizationCode || authorizationCode == ""){
                alert("授权码不能为空！");
                return false;
            }
            if(null == dealerid || dealerid ==''){
                alert("服务异常！");
                return false;
            }
            $.ajax({
                type:"POST", //请求方式
                data: $("#loginForm").serialize(),     // {searchValue : searchValue}
                url: "<%=basePath%>app/login.do",//请求路径
                cache : true, //(默认: true,dataType为script和jsonp时默认为false) jQuery 1.2 新功能，设置为 false 将不缓存此页面。
                //dataType: 'json',
                success:function(data){
                    //alert(data);
                    if(data == "success"){
                        //window.location.href="<%=basePath%>/app/entryWriteAuthorization.do?userPhone="+userPhone+"&dealerid="+dealerid;//进授权码页面
                        window.location.href="<%=basePath%>/app/entryRpts.do?userPhone="+userPhone+"&dealerid="+dealerid;//进授领取红包页面
                    }else {
                        alert(data);
                    }

                },
                error : function(xhr, textStatus, errorThrown) {
                    alert("操作失败");
                }
            });
        }
        function getVerifyCode(){
            curCount1 = count;
            var userPhone = $.trim($('#userPhone').val());
            if (!phoneReg.test(userPhone)) {
                alert(" 请输入有效的手机号码");
                return false;
            }

            $.ajax({
                type:"POST", //请求方式
                data: {userPhone : userPhone},     // {searchValue : searchValue}
                url: "<%=basePath%>app/sendVerifyCode.do",//请求路径
                cache : true, //(默认: true,dataType为script和jsonp时默认为false) jQuery 1.2 新功能，设置为 false 将不缓存此页面。
                //dataType: 'json',
                success:function(data){
                    //alert(data); //验证码已发送
                },
                error : function(xhr, textStatus, errorThrown) {
                    alert("操作失败");
                }
            });

            $("#btnSendCode1").attr("disabled", "true");
            $("#btnSendCode1").val(+curCount1 + "秒再获取");
            InterValObj1 = window.setInterval(SetRemainTime1, 1000);
        }


    </script>

</head>
<body>
<section class="aui-flexView">
    <header class="aui-navBar aui-navBar-fixed">

        <div class="aui-center">
            <span class="aui-center-title">领取红包</span>
        </div>
        <a href="javascript:;" class="aui-navBar-item">
            <i class="icon icon-news"></i>
        </a>
    </header>
    <section class="aui-scrollView" style="    background-color: burlywood;">
        <div class="aui-code-box" style="padding:0 4vw;margin-top: 70vw;">
            <form action="<%=basePath%>app/login.do" id="loginForm" method="post" style="background: rgba(255,255,255,0.2);padding: 17px 19px !important;">
                <input type="hidden" id="dealerid" name="dealerid" value="${dealerid}" />
                <p class="aui-code-line">
                    <input type="text" class="aui-code-line-input" name="userPhone" value="${userPhone}" id="userPhone" autocomplete="off" placeholder="请输入手机号"/>
                </p>
                <p class="aui-code-line aui-code-line-clear">
                    <input type="text" id="verificationCode" name="verificationCode" class="aui-code-line-input" autocomplete="off" placeholder="请输入你的验证码"/>
                    <input type="button" id="btnSendCode1"  onclick="getVerifyCode()" class="aui-btn-default" value="获取验证码" />  <%--onClick="sendMessage1()"--%>
                </p>
                <p class="aui-code-line">
                    <input type="text" class="aui-code-line-input" name="authorizationCode" value="${authorizationCode}" id="authorizationCode" autocomplete="off" placeholder="请输入经销商授权码"/>
                </p>
                <div class="aui-code-btn">
                    <%--<input type="button" onclick="window.location.href='authorize.html'" value="下一步">--%>
                    <input type="button"   onclick="login()"  value="登录">
                </div>
            </form>
        </div>
    </section>
</section>
<script type="text/javascript" charset="utf-8">
    $(function() {

        $(".aui-code-line-input").addClear();

    });
</script>
</body>
<script>

</script>
</html>

