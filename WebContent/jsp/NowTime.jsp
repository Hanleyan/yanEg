<html>
<body>

<input type="text" id="clock" />
<script type="text/javascript">
var int=self.setInterval("clock()",1000);
function clock()
{
var d=new Date();
var t=d.toLocaleTimeString();
document.getElementById("clock").value=t;
}
</script>

<button onclick="int=window.clearInterval(int)">停止</button>

</body>
</html>