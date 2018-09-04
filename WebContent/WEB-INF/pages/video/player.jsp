<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<object name="mplayer" id="mplayer" type="application/x-shockwave-flash" 
		data="${pageContext.request.contextPath }/swf/player.swf" width="640" height="470" > 
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
<input type="button" value="播放" 
onclick="doPlay( '${ctxPath}${movie.videoURL}' );
alert('${ctxPath}${movie.videoURL}');">
</body>
</html>