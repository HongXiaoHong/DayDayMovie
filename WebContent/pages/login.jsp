<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>用户登陆</title>
<style>
input {
	text-indent:0.4em; margin-left:10px;
	width:300px; height:32px;
	border:1px solid #B2B2B2;
	font-size:16px; font-family:'微软雅黑';
}
label { margin-left:20px; font-size:25px; }
body {
	font-family:微软雅黑; margin:0px;
	background-image:url('http://localhost:8080/Movie/images/back.jpg');
}
table {
	border:1px solid #B2B2B2;
	border-collapse:collapse;
	background-color:#FFF;
} 
#outbox {
	background:#333;
	margin:0px 0px;

	position:absolute;
	top:50%; left:50%;
	margin:-115px 0 0 -250px;

	text-align:center;
}
#up_box {
	width:500px; height:230px;
	margin:auto auto;
	display:table-cell;
} 
#btnLogin:hover{ cursor:pointer; }
.head{
	text-align:left; text-indent:0em;
	background:#9CC715; height:55px;
}
</style>
<script>
function gotoSub(){
	var form = document.getElementById( 'userForm' );
	form.submit();	
}

function keyFunc( e ){
	var keyNum = window.event ? e.keyCode : e.which;
	if( keyNum==13 ){
		gotoSub();
		return true;
	}
	return false;
}

function init(){
	//document.body.clientWidth 
	var height = document.body.clientHeight;
	var box = document.getElementById( 'up_box' );
	//box.style.marginTop = (height - 230)/2 +'px';
}

</script>
</head>
<body onKeyUp="return keyFunc(event);" onload="init();">
<div id="outbox">
	<div id="up_box">
		<table>
			<form id="userForm" action="/Movie/User/login"
				accept-charset="UTF-8" method="post">
			<tr>
				<td colspan="2" class="head"><label>用户登陆</label></td>		
			</tr>
			<tr>
				<td colspan="2" style="height:20px;"></td>
			</tr>
			<tr>
				<td width="125" style="text-align:right;">用户名称</td>
				<td width="375" style="text-align:left;"><input type="text" name="user.name"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">用户密码</td>
				<td style="text-align:left;"><input type="password" name="user.pass"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center" valign="middle" height="90">
				<img id="btnLogin" src="../images/img_login.png" onClick="gotoSub();"/>
				</td>
			</tr>
			</form>
		</table>
	</div>
</div>
</body>

</html>