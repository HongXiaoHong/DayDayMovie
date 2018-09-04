<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/idx_body.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/idx_ibox.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/idx_news.css"/>
<style>

label{ margin-left:20px; font-size:25px; }
body{ font-family:微软雅黑; background:#FFF;margin:0px; }

#txt_search{
	border:1px #D2D2D2 solid;
	background:#E2E2E2;
	width:300px; height:39px;
	font-size:18px;
	font-family:'微软雅黑';
	text-indent:0.5em;	
}

input,img{ vertical-align:middle; }

#page_head{
	font-size:18px;
	width:1240px; height:85px;
	margin:0px auto;
	vertical-align:middle;
	line-height:75px;
}
/*
原来的
width:820px
*/
#dv_search{
	float:left;
	width:800px; height:39px;	
	text-align:right;
	vertical-align:middle;
	line-height:75px;
	margin: 20px auto;
}

/* ------------------------------- NAV ------------------------------- */

#nav_bar{ 
	width:100%; height:43px;
	background:#333;
	line-height:43px;
	text-align:center;	
}
#nav_bar > div{
	width:1240px; height:43px;
	/* background:#666; */
	margin:0 auto;	
}
#nav_bar > div > ul{
	color:#FFF;
	font-size:18px;
	list-style:none;
	heigth:43px;
	margin:0px;
	padding:0px;
}
#nav_bar > div > ul > li{
	heigth:43px;
	list-style:none;
	display:inline-block;
	float:left;
	padding-left:20px;
	margin:0px;
} 
.li_sp{ color:999; }

a:link {   
    color: #DEDEFF;   
    text-decoration:none;   
}
a:visited{ color: #DEDEFF; }
a:hover{ color: #FFF33E; }
a:active{ color: #DEDEFF; }

.clear{ clear:both; }

</style>
</head>



<body>
<!-- 顶头的标题栏 -->
<div style="text-align:center;">
	<div id="page_head">
		<div style="float:left;margin-top:10px;">
			<img src="${pageContext.request.contextPath}/images/logo.jpg"/>
		</div>
		<div id="dv_search">
			<input type="text" id="txt_search" style="margin:0 auto;"/><!--
		 --><img src="${pageContext.request.contextPath}/images/btn_search.jpg"/>
		</div>
		<div style="float:right;margin-right:5px;margin-top:20px;">
			<img src="${pageContext.request.contextPath}/images/btn_upload.jpg"/>
			<c:choose>
			<c:when test="${empty user }">
			 上传&nbsp;&nbsp;
			</c:when>
			<c:otherwise><a href="${pageContext.request.contextPath }/cinema/toUploadPage">上传&nbsp;&nbsp;</a></c:otherwise>
			</c:choose>
			
			<img src="${pageContext.request.contextPath}/images/btn_user.jpg"/>
			<c:choose>
			<c:when test="${empty user }">
			<a href="${pageContext.request.contextPath }/pages/login.jsp">登陆</a>
			</c:when>
			<c:otherwise><a href="${pageContext.request.contextPath }/User/infoManage">${user.name }</a></c:otherwise>
			</c:choose> 
			&nbsp;&nbsp;| 
			&nbsp;&nbsp;
			<c:choose>
				<c:when test="${empty user }"><a href="${pageContext.request.contextPath }/pages/regist.jsp">注册</a></c:when>
				<c:otherwise><a href="${pageContext.request.contextPath }/User/loginout">退出</a></c:otherwise>
			</c:choose>
			
		</div>
		<div style="clear:both;"></div>
	</div>
	
	<div id="nav_bar">
		<div>
			<ul>
				<li style="padding:0px;"><a href="#">影院首页</a></li><li class="li_sp">|</li>
				<li><a href="#">电影</a></li><li class="li_sp">|</li>						
				<li><a href="#">动漫</a></li><li class="li_sp">|</li>			
				<li><a href="#">MTV</a></li><li class="li_sp">|</li>				
				<li><a href="#">资讯</a></li><li class="li_sp">|</li>				
				<li><a href="#">专题</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 第一栏目模块 -->
	<div id="mod_news">
	
		<div style="float:left;">
		
			<div id="imgbox">
				<div class="direct left"></div>
				<div class="direct right"></div>
				<div id="bar"></div>				
				<div id="info">幻想三国 2016</div>				
				<ul>
					<li><a href="#">1</a></li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
				</ul>
			</div>
		
			<div style="float:left;position:relative;margin-left:18px;">
				
					<div class="mod1_box">
						<img src="${pageContext.request.contextPath}/images/mod1/d01.jpg"/>
					</div>
					
					<div class="mod1_box" style="margin-left:15px;">
						<img src="${pageContext.request.contextPath}/images/mod1/d02.jpg"/>
					</div>				
					<div class="clear"></div>
					<div class="mod1_box" style="margin-top:15px;">
						<img src="${pageContext.request.contextPath}/images/mod1/d03.jpg"/>
					</div>
					
					<div class="mod1_box" style="margin:15px 0 0 15px;">
						<img src="${pageContext.request.contextPath}/images/mod1/d04.jpg"/>
					</div>
				
			</div>
			
			<div class="clear"></div>
			<div style="float:left;position:relative;margin-top:15px;">
			
				<div class="mod1_box">
					<img src="${pageContext.request.contextPath}/images/mod1/d05.jpg"/>
					<div class="bar">泰坦尼克号</div>
				</div>
				
				<div class="mod1_box" style="margin-left:16px;">
					<img src="${pageContext.request.contextPath}/images/mod1/d06.jpg"/>
					<div class="bar">美国队长 2</div>
				</div>				
				
				<div class="mod1_box" style="margin-left:16px;">
					<img src="${pageContext.request.contextPath}/images/mod1/d07.jpg"/>
					<div class="bar">壮志凌云</div>
				</div>
				
				<div class="mod1_box" style="margin-left:16px;">
					<img src="${pageContext.request.contextPath}/images/mod1/d08.jpg"/>
					<div class="bar">天使与魔鬼</div>
				</div>
			
			</div> 
			
		</div>
		
		<div class="news_right">
			<div>
				<img src="${pageContext.request.contextPath}/images/mod1/windows.jpg"/>
			</div>
			<div class="ad">
				<img src="${pageContext.request.contextPath}/images/mod1/ad01.jpg"/>
			</div>
			<div class="ad">
				<img src="${pageContext.request.contextPath}/images/mod1/ad02.jpg"/>
			</div>
		</div>
		<div style="clear:both;height:15px;"></div>

		<div style="width:1230px;background:#FFF;height:322px;">
			<!--  电影栏的左边块  -->
			<div style="float:left;width:931px;">
				<div style="height:45px;">
					<div style="float:left;">
						<img src="${pageContext.request.contextPath}/images/lg_movie.png" style="vertical-align:middle;"/>
						<span style="display:inline-block;text-align:left;width:70px;font-size:30px;vertical-align:middle;">电影</span>
						<span style="font-size:17px;vertical-align:middle;">
							蝙蝠侠对暗黑战神  |  回到未来
						</span>
					</div>
					<div style="float:right;font-size:16px;line-height:45px;">更多</div>
				</div>
				
				<div style="height:277px;background:#F2F2F2;">
					<div class="mv_box" style="margin-left:0px;">
						<div class="mv_img">
							<img src="${pageContext.request.contextPath}/images/mod2/mv01.png"/>
						</div>
					</div>
					<div class="mv_box" style="margin-left:21px;">
						<div class="mv_img">
							<img src="${pageContext.request.contextPath}/images/mod2/mv02.png"/>
						</div>
					</div>
					<div class="mv_box" style="margin-left:21px;">
						<div class="mv_img">
							<img src="${pageContext.request.contextPath}/images/mod2/mv03.png"/>
						</div>
					</div>
					<div class="mv_box" style="margin-left:21px;">
						<div class="mv_img">
							<img src="${pageContext.request.contextPath}/images/mod2/mv04.png"/>
						</div>
					</div>
				</div>				
			</div>
			<div class="mv_top" style="width:280px;float:right;">
				<div id="title">
					<span>电影榜</span>					
					<img src="${pageContext.request.contextPath}/images/mod2/new.png"/>					
				</div>
				<div id="o_box">
					<div style="margin:4px auto;width:268px;">
						<div class="mbar" style="margin-top:0px;">
							<div class="mbox">
								<img src="${pageContext.request.contextPath}/images/mod2/li01.jpg"/>
							</div>
							<div class="word">魔戒 | 指环王<br/>奇幻史诗巨作</div>
						</div>
						
						<div class="mbar">
							<div class="mbox">
								<img src="${pageContext.request.contextPath}/images/mod2/li02.jpg"/>
							</div>
							<div class="word">霍比特人</div>
						</div>
						
						<div class="mbar">
							<div class="mbox">
								<img src="${pageContext.request.contextPath}/images/mod2/li03.jpg"/>
							</div>
							<div class="word">魔法奇缘</div>
						</div>
						
						<div class="mbar">
							<div class="mbox">
								<img src="${pageContext.request.contextPath}/images/mod2/li04.jpg"/>
							</div>
							<div class="word">星河战队</div>
						</div>
					</div>
				</div>
			</div>
				
		</div>
		<BR/><BR/><BR/><BR/>
		<BR/><BR/><BR/><BR/>
		
	</div>
	

</div>
</body>


</html>