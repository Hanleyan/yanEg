<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>省市区三级菜单联动</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
<h2><省市区三级菜单联动></h2>
请选择：
<select id='a'></select>
<select id='b'></select>
<select id='c'></select>
</body>
<script type="text/javascript">
    var a=[
        {'id':0,'text':'请选择省'},
        {'id':1,'text':'广东'},
        {'id':2,'text':'湖南'}
    ];

    var b=[
        {'id':0,'aid':0,'text':'请选择市'},

        {'id':1,'aid':1,'text':'广州'},
        {'id':2,'aid':1,'text':'佛山'},

        {'id':3,'aid':2,'text':'长沙'},
        {'id':4,'aid':2,'text':'永州'}


    ];


    var c=[
        {'id':0,'bid':0,'text':'请选择区'},

        {'id':1,'bid':1,'text':'广州a区'},
        {'id':2,'bid':1,'text':'广州b区'},


        {'id':3,'bid':2,'text':'佛山a区'},
        {'id':4,'bid':2,'text':'佛山b区'},

        {'id':5,'bid':3,'text':'长沙a区'},
        {'id':6,'bid':3,'text':'长沙b区'},

        {'id':7,'bid':4,'text':'永州a区'},
        {'id':8,'bid':4,'text':'永州b区'}
    ];



    function selecta(){
        var select=$("#a");
        var len=a.length;
        for(var i=0;i<len;i++){
            var option='<option value="'+a[i]['id']+ '">'+a[i]['text']+'</option>';
            select.append(option);
        }
    }


    function selectb(aid){
        var select=$("#b");
        select.empty();
        var len=b.length;
        for(var i=0;i<len;i++){

            if(b[i]['aid']==aid){
                var option='<option value="'+b[i]['id']+ '">'+b[i]['text']+'</option>';
                select.append(option);
            }


        }
    }




    function selectc(bid){
        var select=$("#c");
        select.empty();
        var len=c.length;
        for(var i=0;i<len;i++){

            if(c[i]['bid']==bid){
                var option='<option value="'+c[i]['id']+ '">'+c[i]['text']+'</option>';
                select.append(option);
            }


        }
    }



    $(document).ready(function(){
        selecta();
        selectb(0);
        selectc(0);

        $("#a").change(function(){
            var aid=$(this).val();
            selectb(aid);
            var bid=$("#b").val();
            selectc(bid);

        })

        $("#b").change(function(){
            var bid=$(this).val();
            selectc(bid);
        })


    })

</script>
</html>