<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>create</title>
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
	width:100%;
	height:36px;
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

#mv_list{
	width:790px;
	height:480px;
	margin-top:15px;
	/*  background:#E2E2E2;  */
}

.mv_box{
	display:inline-block;
	width:765px;
	height:122px;
	border:1px solid #D2D2D2;
	line-height:122px;	
	position:relative;
	margin-right:15px;
	margin-bottom:15px;
	background:#FFF;
}

.mv_box .mv_logo{
	display:block;
	width:168px; height:116px;	
	left:4px; top:3px;
	position:absolute;
	cursor:pointer;
}

.mv_box .mv_desc{	
	height:50px;	
	left:185px; top:3px;
	position:absolute;
	line-height:22px;
	font-size:15px;
}

.mv_box .mv_desc label{	
	color:#666;
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

#btnCreate{
	width:120px;
	height:33px;
	font-size:15px;
	font-family:'微软雅黑';
	cursor:pointer;
}
#txtList{
	font-size:16px;
	font-family:'微软雅黑';
}
</style>
</head>
<body>
<!-- 顶头的标题栏 -->
<div style="text-align:center;">

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
			当前用户: ${user.name }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;
			<img src="${pageContext.request.contextPath }/images/btn_user.jpg"/>&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/User/loginout">退出</a>
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
		var url = [ 'none',
		        	'/User/infoManage',           
		            '/User/editPass',
		            '/cinema/list',
		            '/ListIndex/view' ];
		</script>
		<div id="menu" style="background:B3B3B3;">
			<ul>
				<li style="color:#FFF;background-color:#999;" onClick="gotoURL( url[1] );">个人资料</li>
				<li onClick="gotoURL( url[2] );">修改密码</li>
				<li onClick="gotoURL( url[3] );">视频管理</li>
				<li onClick="gotoURL( url[4] );">列表管理</li>
			</ul>
		</div>
		<div id="d_right" style="background:#FFF;">
			<div class="d_bar">列表管理 > 创建新列表</div>
			<div id="mv_list">
				<!-- /PlayList/add  -->
				<div style="margin-left:25px;">
				<form action="${pageContext.request.contextPath }/ListIndex/add" method="post">
  <input id="txtList" type="text" name="listName" style="width:350px;height:27px;"/>
  <input type="hidden" name="creator" value="${user.id }"/>
  <input id="btnCreate" type="submit" value="创建播放列表"/>	
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