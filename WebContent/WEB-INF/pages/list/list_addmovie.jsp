<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<script type="text/javascript" src="${ctxPath }/js/jquery-3.3.1.min.js">
</script>
<script type="text/javascript">
<!--

//-->
$(init);
function init(){
	$(".mv_box").click(function(){
		var mv_bar = $(this).children("div")[0];
		$(mv_bar).toggleClass("mv_bar");
		console.log(mv_bar);
		var checkbox = $(mv_bar).children("input")[0];
		 if($(checkbox).attr("checked")==undefined || $(checkbox).attr("checked")==true){
			 console.log("选中");
			 console.log($(checkbox).attr('checked'));
			 $(checkbox).attr("checked",true);
		 } else{
			 console.log("不选中");
			 console.log($(checkbox).attr('checked'));
			 $(checkbox).attr("checked",false);
		 }
		console.log($(checkbox).attr('checked'));
	});
}
</script>
<style>
body{
	margin:0px;
	font-family:微软雅黑;
	background:#FFF;
}

#page_head{
	font-size:18px;
	width:1240px; height:85px;
	margin:0px auto;
	vertical-align:middle;
	line-height:75px;
}

#nav{
	width:100%;
	height:45px;
	background:#333;
	color:#FFF;
	line-height:45px;	
}

#nav > div{
	width:1240px; height:45px;
	/* ----  background:#E2E2E2;  ---- */
	text-align:left;
	text-indent:0.8em;
	margin:0 auto;
}


/* ------------------------------------------------------------------------------------------------------- */

#d_body{
	width:1070px; height:700px;
	margin:auto;
	border-left: 1px solid #B2B2B2;
	border-right: 1px solid #B2B2B2;
	background:#FEFEFE;
}

#menu{ width:250px; height:100%; background:#D2D2D2; float:left; }
#menu ul{
	list-style:none;
	margin:0px;
	padding:0px;
	color:#E2E2E2;
	font-family:'微软雅黑';
	text-indent:0.5em;
	text-align:left;
}

#menu ul > li{
	background:#666;
	width:220px; height:35px;
	margin-bottom:1px;
	border-left: 30px solid #4D4D4D;
	line-height:35px;
}

/* -------------------------------------------------------------------------------------- */

#d_right{
	width:820px;  /* 1240 - 250 --> 990 */
	float:left;
	text-align:left;
	/* background:#222; */
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
	height:560px;
	/*  background:#E2E2E2;  */
	margin:5 0 0 25px;
}

.mv_box{
	display:inline-block;
	width:176px;
	height:122px;
	border:1px solid #D2D2D2;
	line-height:122px;	
	position:relative;
	margin-right:15px;
	margin-bottom:15px;
	background:#FFF;
}


.mv_box > .mv_logo{
	display:block;
	width:167px; height:116px;	
	left:4px; top:3px;
	position:absolute;
	cursor:pointer;
}

.mv_box .mv_bar{
	width:168px;
	height:22px;
	background-image:url('/Movie/images/select.png');
	left:4px; bottom:3px;
	position:absolute;
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

</style>

<script>
var url = [
    'none',
	'/User/infoManage',           
    '/User/editPass',
    '/cinema/list',
    '/ListIndex/view',
    '/PlayList/view'
];
//jsp/list_create.jsp

function gotoPage( index ){
	window.location = '/Movie'+ url[ index ];
}
function gotoUrl(url){
	window.location = url;
}
function addMovie(){
	var movieForm = document.getElementById("movieForm");
	movieForm.submit();
}
</script>

<!-- 顶头的标题栏 -->
<div style="text-align:center;">
	
	<div id="page_head">
		<div style="float:left;margin-top:10px;">
			<img src="${pageContext.request.contextPath }/images/logo.jpg"/>
		</div>
		<div style="float:right;margin-right:10px;">
			当前用户: ${user.name }&nbsp;&nbsp;&nbsp;
			<img src="${pageContext.request.contextPath }/images/btn_user.jpg"/>
			<a href="${pageContext.request.contextPath }/User/loginout">退出</a>
		</div>
		<div style="clear:both;"></div>
	</div>
	
	
	<div id="nav">
		<div>
			用户管理 > 我的帐号
		</div>
	</div>
	
	<div id="d_body">
	
		<div id="menu">
			<ul>
				<li onClick="gotoPage(1);">个人资料</li>
				<li onClick="gotoPage(2);">修改密码</li>
				<li onClick="gotoPage(3);">视频管理</li>
				<li onClick="gotoPage(4);">列表管理</li>
			</ul>
		</div>
		
		<div id="d_right">
			<div class="d_bar">列表管理 > 列表编辑 > 添加视频到 </div>
			<div class="search_bar">
			<img src="${pageContext.request.contextPath }/images/btn_add2.png" 
			style="margin-right:30px;"
			onclick="addMovie();"
			/>
			<input name="keyWord" size="25"/><img src="${pageContext.request.contextPath }/images/btn_search2.png"/>			
			</div>
			<div id="mv_list">
			<form action="${ctxPath}/ListIndex/addMovies?listId=${listIndex.id}" method="post" id="movieForm">
			<c:forEach  var="movie" items="${movieList}">
				<div class="mv_box">
					<img 
					class="mv_logo"
					src="${pageContext.request.contextPath }/ResGetter/get?user=${user.name}&movieId=${movie.id}&type=img&extName=${movie.imgExtName}"
					/>
					<div class=""><input type="checkbox" name="movieId" style="display:none;" value="${movie.id}"></div>
				</div>
			</c:forEach>
			</form>
			</div>
			
			<c:set var="pageTemp" value="${mvPage.start }"></c:set>
			<div style="margin:0 170px;">
				<span class="pagebtn" style="margin-left:25px;cursor:pointer;"  
				onclick="window.location='/Movie/ListIndex/toAddMoviePage?page=${mvPage.page<=1?1:mvPage.page-1}';"
				>上一页</span>
				
				<c:forEach begin="${mvPage.start}" end="${mvPage.start+4}">
				<c:set var="pageNum" value="${mvPage.end }"></c:set>
				<c:if test="${pageTemp<=mvPage.end }">
				<c:set var="pageNum" value="${pageTemp }"></c:set>
				</c:if>
				<c:set var="onStyle" value="" />
				<c:if test="${pageTemp == mvPage.page}">
					<c:set var="onStyle" value="on" />
				</c:if>
				<span class="pagebtn  ${onStyle}" 
				style="cursor:pointer;" 
				onclick="window.location='/Movie/ListIndex/toAddMoviePage?page=${pageNum}';">${pageNum}</span>
				<c:set var="pageTemp" value="${pageTemp+1 }"></c:set>
				</c:forEach>
				
				<span class="pagebtn" 
				style="cursor:pointer;"  
				onclick="window.location='/Movie/ListIndex/toAddMoviePage?page=${mvPage.page>=mvPage.totalPage?mvPage.totalPage:mvPage.page+1}';">
				下一页</span>
			</div>
			
		</div>
		
		
	</div>
</div>
