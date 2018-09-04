<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#list_mod{
	width:30%; height:100%;
	background:#999;
}
#title{
	width:122px; height:35px;
	background:#454545; line-height:35px;
	color:white; text-indent:1.0em;
	border-right:2px solid #000;
	float:left;
}
#t2{
	background:#191919; 
	width:280px; height:35px;
	float:left;
}

#list_box{
	background:#000; margin-left:2px;
	width:383px;  height:423px;
	padding-top:2px; overflow: auto;
}

.list_item{
	width:377px; height:80px;
	background:#0B090A;
	margin-left:2px; margin-bottom:3px;
}

/* ---------------------------------------- */
.mv_logo{
	width:132px; height:80px;
	background:gray; float:left;
	margin-right:3px; position:relative;
}
.mv_logo img{ 
	position:absolute; 
	width:132px; height:80px;
	}

.mv_logo .bar{
	width:132px; height:22px;
	background:url('images/back.png');
	position:absolute; bottom:0px;
	color:#FFF; font-size:14px;
	line-height:22px; text-indent:1.0em;
}

/* ---------------------------------------- */
.mv_info{
	width:242px; height:80px;
	background:#333333; float:left;
	color:#FFF; font-size:15px;
}
</style>
<title>Insert title here</title>
<script type="text/javascript">
//如果你要播视频, 你调用这个方法, path 要传一个影片的 URL 地址
function nativeForJs( path ){		
    var ret = getFlashPlayer( "mplayer" ).callFuncByJS( path );   
}
//getFlashPlayer 参数: 是 swf 文件 的 id
function getFlashPlayer( flashObj ) {
    if ( navigator.appName.indexOf( "Microsoft" ) != -1 ) {  
        return window[ flashObj ];
    } else {
        return document[ flashObj ];
    }
}
function doPlay( path ){
    nativeForJs( path );
}
</script>
</head>
<body>
<%--
640 470
720 520
 --%>

<object name="mplayer" id="mplayer" type="application/x-shockwave-flash" 
		data="${pageContext.request.contextPath }/swf/player.swf" width="640" height="470" 
		style="float:left;"
		> 
		<param name="allowScriptAccess" value="always"/>
		<param name="movie" value="${pageContext.request.contextPath }/swf/player.swf" />
		<param name="quality" value="high" />
		<param name="scale" value="noScale" />
		<param name="wmode" value="transparent" />
		<embed name="mplayer" src="${pageContext.request.contextPath }/swf/player.swf" quality="high"
			pluginspage="http://www.macromedia.com/go/getflashplayer"   
			type="application/x-shockwave-flash" width="640" height="470" 
			allowScriptAccess="always" wmode="transparent" />
</object>

<div id="list_mod" style="float:right;margin-right: 280px">
	<div style="background:#454545;height:41px;">
		<div id="title">播放列表</div>
		<div id="t2"></div>
	</div>	
	<div id="list_box">
	
		<c:forEach var="movie" items="${movies }">
		<div class="list_item">
			<!-- 132 * 80 -->
			<div class="mv_logo"
			onclick="doPlay( '${ctxPath}/ResGetter/get?user=${user.name}&movieId=${movie.id}&type=video&extName=${movie.extName}' );"
			>
				<img src="${ctxPath}/ResGetter/get?user=${user.name}&movieId=${movie.id}&type=img&extName=${movie.imgExtName}"/>
				<div class="bar">
				${movie.mvDuration } 正在播放
				</div>
			</div>
			<div class="mv_info">
				<div style="margin-left:10px;margin-top:5px;">
				${movie.mvName }<br/>
				${movie.mvDesc }
				</div>
			</div>			
		</div>
		</c:forEach>
		
	</div>	
</div>
<%-- <input type="button" value="播放" 
onclick="doPlay( '${ctxPath}${movie.videoURL}' );
alert('${ctxPath}${movie.videoURL}');"> --%>
</body>
</html>