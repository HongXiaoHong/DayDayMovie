<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>

<style>

body{
	margin:0px;
	font-family:微软雅黑;
	background:#FFF;
}

#page_head{
	font-size:18px;
	width:1070px; height:85px;
	margin:0px auto;
	vertical-align:middle;
	line-height:75px;
	/*  background:#666;  */
}

#nav{
	width:1070px;
	height:45px;
	background:#333;
	color:#FFF;
	line-height:45px;
	margin:0px auto;
}

#nav > div{
	width:1070px; height:45px;
	/* ----  background:#E2E2E2;  ---- */
	text-align:left;
	text-indent:0.8em;
	margin:0 auto;
}


/* ------------------------------------------------------------------------------------------------------- */

#d_body{
	width:1070px; height:750px;
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

#menu ul > li:hover{
	background:#555;
	cursor:pointer;
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
	width:795px; height:38px;
	text-align:right;
	/*  background:#E5E5E5;  */
}
.search_bar > #btnCreate,#btnSearch{
	display:inline-block;
	vertical-align:middle;
	height:27px;
}
.search_bar > #inpKeyWord{	
	height:21px;
	vertical-align:middle;
}

#tips{
	text-align:right;
	margin:10 0px;
	height:30px; width:100%;
	/*  background:#F2F2F2;  */
	line-height:30px;
}

#mv_list{
	width:790px;
	height:560px;
	/*  background:#E2E2E2;  */
	margin:0px 25px;
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
a:link {   
    color: #DEDEFF;   
    text-decoration:none;   
}
a:visited{ color: #DEDEFF; }
a:hover{ color: #FFF33E; }
a:active{ color: #DEDEFF; }
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
function doSeacher(){
	var seacher = document.getElementById("seacher");
	seacher.submit();
}
</script>

<!-- 顶头的标题栏 -->
<div style="text-align:center;">
	
	<div id="page_head">
		<div style="float:left;margin:10px 7px;">
			<img src="${ctxPath }/images/logo.jpg"/>
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
			<div class="d_bar">列表管理 > 列表编辑 > 列表视图</div>
			<form id="seacher" action="${ctxPath }/ListIndex/search" method="post">
			<div class="search_bar">				
				<img id="btnCreate" src="${pageContext.request.contextPath }/images/btn_newlist.png"
				 style="margin-right:15px;cursor:pointer;"
				 onClick="window.location='${ctxPath}/ListIndex/toCreateListPage';"/>			
				<input id="inpKeyWord" name="page.keyWord" size="35"/><!--
				--><img id="btnSearch" src="${pageContext.request.contextPath }/images/btn_search2.png"
						onclick="doSeacher();"
				/>			
			</div>
			</form>
			<div id="tips">
				<!-- 
				我在 Controller 层中下发如下数据:				
					req.setAttribute( "mvList", list ); -->
				<label style="margin:0 28px;">当前列表数: ${size }</label>
			</div>			
			
			<div id="mv_list">	
			<c:forEach var="listIndex" items="${ allListIndex }">			
				<div class="mv_box">
				<c:choose>
				<c:when test="${ empty listIndex.list }">
				 <c:set var="path" value="${ctxPath}/images/d01.png"></c:set>
				 </c:when>
				<c:otherwise>
				<c:set var="path" value="${ctxPath}/ResGetter/get?user=${user.name}&
							movieId=${listIndex.list[0].movieId }&type=img&extName=jpg"></c:set>
				</c:otherwise>
				</c:choose>
					<img class="mv_logo" 
						src="${path}"
					 	onclick="gotoUrl('${ctxPath}/ListIndex/toPlayPage?listId=${listIndex.id}');"/>
					
					
					<div class="mv_desc">
						列表名称: <label>${listIndex.listName }</label><br/>
						列表视频数: <label>${listIndex.listNum }</label><br/>
						创建时间: <label>${listIndex.createDate }</label><br/><br/>
						<img 
						src="${pageContext.request.contextPath }/images/list_edit.png"
						style="cursor:pointer;"
						onclick = "gotoUrl('${ctxPath}/ListIndex/edit?id=${listIndex.id }&num=${listIndex.listNum }');"
						/>
						<img 
						src="${pageContext.request.contextPath }/images/list_del.png"
						style="cursor:pointer;"
						onclick="gotoUrl('${ctxPath}/ListIndex/delete?id=${listIndex.id }');"
						/>
					</div>				
					
				</div>	
			</c:forEach>			
			</div>
			
			<c:set var="pageTemp" value="${mvPage.start }"></c:set>
			<div style="margin:0 170px;">
				<span class="pagebtn" 
				style="margin-left:25px;cursor:pointer;"  
				onclick="window.location='/Movie/ListIndex/view?page=${mvPage.page<=1?1:mvPage.page-1}';"
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
				onclick="window.location='/Movie/ListIndex/view?page=${pageNum}';">${pageNum}</span>
				<c:set var="pageTemp" value="${pageTemp+1 }"></c:set>
				</c:forEach>
				
				<span class="pagebtn" 
				style="cursor:pointer;" 
				onclick="window.location='/Movie/ListIndex/view?page=${mvPage.page>=mvPage.totalPage?mvPage.totalPage:mvPage.page+1}';">
				下一页</span>
			</div>
			
		</div>		
		
	</div>
</div>
