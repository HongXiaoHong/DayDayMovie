<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/user_1.css"
	type="text/css" />
<style>
#d_right {
	width: 820px; /* 1240 - 250 --> 990 */
	float: left;
	text-align: left;
	/* background:#999; */
	/*
	原来的设置为100%
	height: 100%;
	*/
	height: 120%;
}
#menu {
	height: 120%;
}

#d_right .d_bar {
	width: 100%;
	height: 36px;
	font-family: '微软雅黑';
	background: #E2E2E2;
	line-height: 36px;
	text-indent: 0.5em;
}

.search_bar {
	line-height: 36px;
	width: 795px;
	height: 36px;
	text-align: right;
}

.search_bar img, input {
	vertical-align: middle;
	height: 27px;
}

.clear {
	clear: both;
}

.pagebtn {
	border: 1px solid #666;
	padding: 0 10px;
	height: 30px;
	line-height: 30px;
	margin-right: 10px;
	margin-top: 0px;
	background: #FFF;
}

.frmButton {
	width: 95px;
	height: 35px;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
	font-family: '微软雅黑';
	background: rgb(233, 166, 0);
	text-align: center;
	line-height: 33px;
}

table {
	font-family: '微软雅黑';
	font-size: 16px;
	border: none;
}

table td {
	border: none;
}

[type='text'] {
	width: 220px;
	text-indent: 0.3em;
	font-family: '微软雅黑';
	font-size: 16px;
	background: #E5E5E5;
	border: 1px solid #666;
}

select {
	width: 353px;
	font-family: '微软雅黑';
	height: 32px;
	font-size: 16px;
	text-indent: 0.3em;
	background: #E5E5E5;
	border: 1px solid #666;
}

.td1 {
	width: 85px;
	text-align: right;
	padding-right: 5px;
}
</style>
<script>
	function doSubmit() {
		var form = document.getElementById('frmMovie');
		form.submit();
	}
</script>
</head>
<body>
	<!-- 顶头的标题栏 -->
	<div style="text-align: center;">




		<style>
a:link {
	color: #160764;
	text-decoration: none;
}

a:visited {
	color: #160764;
}

a:hover {
	color: green;
}

a:active {
	color: #160764;
}
</style>
		<script>
			function gotoURL(url) {
				window.location = '/Movie' + url;
			}
		</script>
		<div id="page_head">
			<div style="float: left; margin: 10px 7px;">
				<img src="${pageContext.request.contextPath }/images/logo.jpg" />
			</div>
			<div style="float: right; margin-right: 10px;">
				<img id="upload"
					src="${pageContext.request.contextPath }/images/btn_upload.jpg"
					onclick="gotoURL('${pageContext.request.contextPath }/Movie/showUpload');" />
				<a href="${pageContext.request.contextPath }/Movie/showUpload">上传</a>&nbsp;&nbsp;
				当前用户: ${user.name }&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp; <img
					src="${pageContext.request.contextPath }/images/btn_user.jpg" />&nbsp;&nbsp;<a
					href="${pageContext.request.contextPath }/User/loginout">退出</a>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div id="nav">
			<div>用户管理 > 我的帐号</div>
		</div>

		<div id="d_body" style="height: 450px;">




			<script>
				var url = [ 'none', '/User/infoManage', '/User/editPass',
						'/cinema/list', '/ListIndex/view' ];
			</script>
			<div id="menu">
				<ul>
					<li style="color: #FFF; background-color: #888;"
						onClick="gotoURL( url[1] );">个人资料</li>
					<li onClick="gotoURL( url[2] );">修改密码</li>
					<li onClick="gotoURL( url[3] );">视频管理</li>
					<li onClick="gotoURL( url[4] );">列表管理</li>
				</ul>
			</div>




			<div id="d_right" style="background: white;">
				<div class="d_bar">个人资料 > 影片管理 > 影片编辑</div>
				<div id="mv_list">
					<!-- /PlayList/add  -->
					<div style="margin-left: 25px; margin-top: 5px;">
						<form id="frmMovie"
							action="${pageContext.request.contextPath }/cinema/editMovie?movieId=${movie.id}"
							method="post">
<!-- 							<input type="hidden" name="id"
								value="yk5uhvpk11po10i6bk2863ch716e0ge7" /> -->
							<table>
								<tr>
									<td width="120" align="right">视频名称</td>
									<td width="455"><input type="text" name="mvName" value="${movie.mvName }"/></td>
								</tr>
								<tr>
									<td align="right">视频描述</td>
									<td><textarea name="mvDesc" cols="35" rows="5"
									style="font-family: '微软雅黑';font-size: 16px;"/>
									${movie.mvDesc}</textarea></td>
								</tr>
								<tr>
									<td align="right">视频分类</td>
									<td><select name="category" />
										<option value="none">请选择类别</option> <c:forEach var="category"
											items="${categories }">
											<option value="${category.id }"
											 ${movie.category eq category.id ? 'selected':'' }>
											${category.categoryName }</option>
										</c:forEach> </select></td>
								</tr>
								<tr>
									<td align="right">视频时长</td>
									<td><input type="text" name="mvDuration" value="${movie.mvDuration }"/></td>
								</tr>
								<tr>
									<td align="right">标签</td>
									<td><input type="text" name="label" value="${movie.label }"/></td>
								</tr>
								<tr>
									<td align="right">缩略图</td>
									<td>
										<%-- 
											原来的路径是src="ResGetter/get?user=andy&movieId=d04" 
										--%> 
										<img class="mv_logo"
										src="${pageContext.request.contextPath }/ResGetter/get?user=${user.name}&
										movieId=${movie.id }&type=img&extName=${movie.imgExtName}" 
										onClick="alert('Hello');" 
										style=" display:inline-block;
												width:150px; height:100px;
												border:1px solid #D2D2D2;
												position:relative;
												margin-right:15px;
												margin-top:5px;
												margin-bottom:0;
												padding:0;
												background:#FFF;"/>
									</td>
								</tr>
								
								<tr>
									<td colspan="2" style="height: 55px; text-align: center;margin:0;padding:0;">
										<div class="frmButton" onclick="doSubmit();">修改</div>
										<div class="frmButton" onclick="gotoURL( '/cinema/edit?movieId=${movie.id}' );">重置</div>
									</td>
								</tr>
								<input type="hidden" name="uploader"
									value="yk5uhvpk11po10i6bk2863ch716e0ge7" />
							</table>
						</form>
					</div>
				</div>

			</div>

		</div>

		<!-- 静态包含 -->


	</div>
</body>
</html>