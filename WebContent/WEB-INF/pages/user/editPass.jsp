<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/user_1.css" type="text/css"/>
<style>
#d_right{
	width:820px;  /* 1240 - 250 --> 990 */
	float:left;
	text-align:left;
	/* background:#999; */
	height:100%;
}

#d_right .d_bar{
	width:100%; height:36px;
	font-family:'微软雅黑';
	background:#E2E2E2;
	line-height:36px;	
	text-indent:0.5em;
}

.search_bar{
	line-height:36px;
	width:795px; height:36px;
	text-align:right;
}
.search_bar img,input{
	vertical-align:middle;
	height:27px;
}

.clear{ clear:both; }

.pagebtn{
	border:1px solid #666;
	padding: 0 10px;
	height:30px; line-height:30px;
	margin-right:10px;
	margin-top:0px;
	background:#FFF;
}

.frmButton{
	width:95px; height:35px; display:inline-block;
	font-size:15px; cursor:pointer;
	font-family:'微软雅黑'; background:rgb(233,166,0);
	text-align:center; line-height:33px;
}

table {
	font-family:'微软雅黑'; font-size:16px; border:none;
}

table td { border:none; }

[type='text']{
	width:220px; text-indent:0.3em;	font-family:'微软雅黑';
	font-size:16px; background:#E5E5E5; border:1px solid #666;
}

select {
	width:353px; font-family:'微软雅黑'; height:32px;
	font-size:16px; text-indent:0.3em;
	background:#E5E5E5; border:1px solid #666;
}

.td1 {
	width:85px; text-align:right; padding-right:5px;
}
</style>
<script>
function doSubmit(){
	var form = document.getElementById('frmMovie');
	form.submit();
}
</script>
</head>
<body>
<!-- 顶头的标题栏 -->
<div style="text-align:center;">

	


<style>
a:link { color: #160764; text-decoration:none; }
a:visited{ color: #160764; }
a:hover{ color: green; }
a:active{ color: #160764; }
</style>
<script>
function gotoURL( url ){
	window.location = '/Movie'+ url;
}
</script>
	<div id="page_head">
		<div style="float:left;margin:10px 7px;">
			<img src="${pageContext.request.contextPath }/images/logo.jpg"/>
		</div>
		<div style="float:right;margin-right:10px;">
			<img id="upload" src="${pageContext.request.contextPath }/images/btn_upload.jpg" onclick="gotoURL('${pageContext.request.contextPath }/Movie/showUpload');"/> 
			<a href="${pageContext.request.contextPath }/Movie/showUpload">上传</a>&nbsp;&nbsp;
			当前用户: ${user.name }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
			<img src="${pageContext.request.contextPath }/images/btn_user.jpg"/>&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/User/loginout">退出</a>
		</div>
		<div style="clear:both;"></div>
	</div>	
	<div id="nav">
		<div>
			用户管理 > 我的帐号
		</div>
	</div>

	<div id="d_body" style="height:450px;">
	
		


<script>
var url = [ 'none', '/User/infoManage', '/User/editPass', '/cinema/list', '/ListIndex/view' ];       
</script>
<div id="menu">
	<ul>
		<li style="color:#FFF;background-color:#888;" onClick="gotoURL( url[1] );">个人资料</li>
		<li onClick="gotoURL( url[2] );">修改密码</li>
		<li onClick="gotoURL( url[3] );">视频管理</li>
		<li onClick="gotoURL( url[4] );">列表管理</li>
	</ul>
</div>



		
		<div id="d_right" style="background:white;">
			<div class="d_bar">个人资料 > 修改密码</div>
			<div id="mv_list">
				<!-- /PlayList/add  -->
				<div style="margin-left:25px;margin-top:25px;">
				<form id="frmMovie" action="${pageContext.request.contextPath }/User/editPass" method="post">
					<input type="hidden" name="id" value="yk5uhvpk11po10i6bk2863ch716e0ge7"/>
					<table>
						<tr>
							<td class="td1">旧密码</td>
							<td><input type="text" name="oldPass"/></td>
						</tr>
						<tr>
							<td class="td1">新密码</td>
							<td><input type="text" name="newPass"/></td>
						</tr>
						<tr>
							<td class="td1">确认密码</td>
							<td>
								<input type="text" name="comPass"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="height:55px;text-align:center;">
							<div class="frmButton" onclick="doSubmit();">修改</div>
							<div class="frmButton" onclick="gotoURL( '/User/editPass' );">重置</div>
							</td>							
						</tr>
				   		<input type="hidden" name="uploader" value="yk5uhvpk11po10i6bk2863ch716e0ge7"/>
				    </table>
				</form>
				</div>
			</div>

		</div>
		
	</div>
    
	<!-- 静态包含 -->
    
<div id="d_foot">		
	版权所有: 艾力软件工作室&nbsp;&nbsp;
	关于我们&nbsp;&nbsp;
	友情链接&nbsp;&nbsp;
	寻求合作&nbsp;&nbsp;
	广告服务
</div>
	
</div>
</body>
</html>