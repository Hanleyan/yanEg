<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>导入excel</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="导入excel">
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
</head>
<script type="text/javascript">



    var User = function() {
        this.init = function() {
            //模拟上传excel  
            $("#uploadEventBtn").unbind("click").bind("click", function() {
                $("#uploadEventFile").click();
            });
            $("#uploadEventFile").bind("change", function() {
                $("#uploadEventPath").attr("value", $("#uploadEventFile").val());
            });
        };
        //点击上传钮  
        this.uploadBtn = function() {
            var uploadEventFile = $("#uploadEventFile").val();
            if (uploadEventFile == '') {
                alert("请择excel,再上传");
            } else if (uploadEventFile.lastIndexOf(".xls") < 0) {//可判断以.xls和.xlsx结尾的excel  
                alert("只能上传Excel文件");
            } else {
                var url = "excel/import.do";
                var formData = new FormData($('form')[0]);
                user.sendAjaxRequest(url, "POST", formData);
            }
        };
        this.sendAjaxRequest = function(url, type, data) {
            $.ajax({
                url : url,
                type : type,
                data : data,
                cache : true,
                //dataType : "json",
                success : function(data) {
                    alert(data);
                },
                error : function(result) {
                    alert(result.message);
                },
                cache : false,
                contentType : false,
                processData : false
            });
        };
    };
    var user;
    $(function() {
        user = new User();
        user.init();
    });
</script>
<body>
	<div style="display: flex;flex-direction: row">
		<form enctype="multipart/form-data" id="batchUpload"  action="/excel/import" method="post" class="form-horizontal">    
        <button class="btn btn-success btn-xs" id="uploadEventBtn" style="height:26px;"  type="button" >择文件</button>  
        <input type="file" name="file"  style="width:0px;height:0px;" id="uploadEventFile">  
        <input id="uploadEventPath"  disabled="disabled"  type="text" placeholder="请择excel表" style="border: 1px solid #e6e6e6; height: 26px;width: 200px;" />                                           
    </form>  
    <button type="button" class="btn btn-success btn-sm"  onclick="user.uploadBtn()" ">上传</button> 
	</div>
    
  </body>
</html>