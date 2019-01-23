<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>纯jquery版三级联动</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){ */
$(function(){
	$(".one").change(function(){ 
		$(".two option:gt(0)").remove();
		$(".three option:gt(0)").remove();
		var pro = $(".one").val();
		if(pro == "jiangxi"){
			$(".two").append("<option value='n'>南昌<option value='j'>九江<option value='s'>上饶"); 
			$(".two").change(function(){
				$(".three option:gt(0)").remove();
				var city = $(".two").val();
				if(city == "n"){
					$(".three").append("<option>进贤<option>新建<option>三阳");
				}else if(city == "j"){
					$(".three").append("<option>九江县1<option>九江县2<option>九江县3");
				}else if(city == "s"){
					$(".three").append("<option>上饶县<option>玉山县<option>山青山");
				}
			});
		}else if(pro == "shanghai"){
			$(".two").append("<option value='b'>宝山<option value='p'>浦东新区<option value='h'>虹口"); 
			$(".two").change(function(){
				$(".three option:gt(0)").remove();
				var city = $(".two").val();
				if(city == "b"){
					$(".three").append("<option>淞南镇<option>顾村公园<option>宝山万达");
				}else if(city == "p"){
					$(".three").append("<option>陆家嘴<option>外滩<option>世纪公园");
				}else if(city == "h"){
					$(".three").append("<option>赤峰路<option>足球场<option>四平路");
				}
			});
		}else if(pro == "beijing"){
			$(".two").append("<option value='t'>天安门<option value='g'>故宫<option value='b'>北京五环");
			$(".two").change(function(){
				$(".three option:gt(0)").remove();
				var city = $(".two").val();
				if(city == "t"){
					$(".three").append("<option>天安门1<option>天安门2<option>天安门3");
				}else if(city == "g"){
					$(".three").append("<option>故宫1<option>故宫2<option>故宫3");
				}else if(city == "b"){
					$(".three").append("<option>北京五环1<option>北京五环2<option>北京五环3");
				}
			});
		}else if(pro == "hunan"){
			$(".two").append("<option value='c'>长沙<option value='z'>株洲<option value='h'>衡阳");
			$(".two").change(function(){
				$(".three option:gt(0)").remove();
				var city = $(".two").val();
				if(city == "c"){
					$(".three").append("<option>长沙1<option>长沙2<option>长沙3");
				}else if(city == "z"){
					$(".three").append("<option>株洲1<option>株洲2<option>株洲3");
				}else if(city == "h"){
					$(".three").append("<option>衡阳1<option>衡阳2<option>衡阳");
				}
			});
		}
	});
});

</script>
</head>
<body>
	
	<select class="one"><option>--请选择省--</option>
		<option value="jiangxi">江西</option>
		<option value="shanghai">上海</option>
		<option value="beijing">北京</option>
		<option value="hunan">湖南</option>
	</select >
	<select class="two"><option>--请选择市--</option>
		
	</select>
	<select class="three"><option>--请选择县--</option>
		
	</select>
		
</body>

</html>