<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function check() {  
    // 获取文件
    var f = document.getElementById("f").files;  
    // 获取文件后缀
    var suffixname = f[0].name.substr(f[0].name.lastIndexOf(".")).toLowerCase();

    // 文件后缀名
    alert("[文件后缀名]:"+suffixname );
    //上次修改时间  
    alert("[上次修改时间]:" + f[0].lastModifiedDate); 
    var crtTime = new Date(f[0].lastModifiedDate);
    crt.
  	//文件创建时间  
    alert("[文件创建时间]:" + f[0].getAsDataURL);  
    //名称  
    alert("[名称]:" + f[0].name);  
    //大小 字节  
    alert("[大小 字节]:" + f[0].size);  
    //类型  
    alert("[类型]:" + f[0].type);  
}  

	</script>
<body>
	<form id="form1" runat="server">
		<div>
			<input type="file" name="f" id="f" /> <input type="button" name="aa"
				id="aa" value="测试" onclick="javascript: check();" />
		</div>
	</form>

</body>
</html>