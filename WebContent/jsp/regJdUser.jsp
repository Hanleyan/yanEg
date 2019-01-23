<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="../../css/regJdUser.css" >
</head>
<body>
<div style="margin-left:600px;margin-top:50px; background-color: #dedad3;width:300px;height:600px">
	<div>    <h3 style="margin-left:130px">Reg</h3>    </div>
	
	<form action="http://192.168.1.165:8078/bibibaba/appUser/submitRegJDUser.do" method="post">
	<!-- <form Action="http://192.168.1.164:8080/yanEg/submitRegJDUser.do" method="post"> -->
	
	<div><label>用户名：</label>   
		<input  type="text" name="userName"/>
	</div>
	<div><label>密码：</label>   
		<input  type="text" name="passWord"/>
	 </div>
	<div><label>sex：</label>
		<input  type="radio"  name="sex" value="男"/>男
		<input  type="radio"  name="sex"  value="女"/>女
	</div>
	<div><label>birthday：</label>
		<input  type="text" name="birthday"/>
	            </div>
	<div><label>age：</label>  
	<input  type="text" name="age"/>          </div>
	<div><label>regTime：</label>     
	<input  type="text" name="createTime"/>       </div>
	<div><label>卡号：</label>     
	<input  type="text" name="bankNo"/>       </div>
	<div><label>省：</label>   
	<input  type="text" name="pro"/>         </div>
	<div><label>市：</label>   
	<input  type="text" name="city"/>         </div>
	<div><label>详细地址：</label>   
	<input  type="text" name="address"/>         </div>
	<div><label>email：</label>   
	<input  type="text" name="email"/>         </div>
	<div><label>是否同意协议：</label> 
	<input  type="checkbox" name="isAgree" value="1"/>           </div>
	
	<div><input  type="submit" />           </div>

</form>

</div>
</body>
</html>