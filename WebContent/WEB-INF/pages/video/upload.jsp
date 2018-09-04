<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function init(){
	$("#upload").click(function(){
		var form = $("#formUpload");
		console.log(form);
		var DForm =form[0];
		console.log(DForm);
		DForm.submit();
	});
}
$(init);
</script>
<title>上传ing</title>
<style>

body{
	background-image:url('/Movie/images/upload/back.jpg');
}

[type='file'] {
    display:none;
}

::-webkit-file-upload-button {
	display:none;
}

input { text-indent:0.4em; }
textarea{ padding:5px; }

.dv_sel{
	display:inline-block;
	background: #9CC715;
	border: 1px solid #8B8B8B;
	width:90px; height:32px;
	cursor:pointer; color:#FFF;
	margin:0px;
	font-size:15px;
	text-align:center;
	line-height:32px;
	overflow:hidden;
}
.dv_file{
	display:inline-block;
	width:287px; height:32px;
	background:#E2E2E2;
	border: 1px solid #8B8B8B;
	border-right:none;
	text-align:left;
	line-height:32px;
	margin:0 0 0 10px;
	color:#666;
	overflow:hidden;
	text-indent:0.2em;
}

input{
	width:380px;
	height:32px;
	border:1px solid #B2B2B2;
	margin-left:10px;
	font-family:'微软雅黑';
	font-size:16px;
}

textarea{ 
	width:380px;
	margin-left:10px;
	border:solid 1px #B2B2B2;
	font-size:16px;
	font-family:微软雅黑;
}

label{ margin-left:20px; font-size:25px; }
body{ font-family:微软雅黑; }

select {
  /* Chrome 和 Firefox 里面的边框是不一样的，所以复写了一下 */
  border:solid 1px #B2B2B2;

  /* 很关键：将默认的 select 选择框样式清除 */
  appearance:none;
  -moz-appearance:none;
  -webkit-appearance:none;

  /* 在选择框的最右侧中间显示小箭头图片 */
  background:url( '/Movie/images/drop_arr.jpg') no-repeat scroll right center transparent;

  /* 为下拉小箭头留出一点位置，避免被文字覆盖 */
  padding-right:14px;
  
  width:380px; height:30px;
  font-size:16px; font-family:微软雅黑;
  margin-left:10px;
  color:#B2B2B2;
}
select:hover{ cursor:pointer; }


/* 清除 ie 的默认选择框样式清除，隐藏下拉箭头 */
select::-ms-expand { display: none; }

table{
	border:1px solid #B2B2B2;
	border-collapse:collapse;
	background-color:#FFF;
}
 
#up_box{
	width:560px; height:470px;
	background:#333; margin:0 auto;
}
 
</style>

<script>
function selFile( id ){
	var obj = document.getElementById( id );
	obj.click();
}
function showValue( dvfile, fileObj ){
	
	var dvfile = document.getElementById( dvfile );
	dvfile.innerText = fileObj.value;

}
</script>
</head>

<body>
<div id="up_box">
<form id="formUpload" action="${pageContext.request.contextPath }/cinema/upload"
 enctype="multipart/form-data" method="post">
	<table>
		<tr>
			<td colspan="2" height="65"><label>上传视频</label></td>		
		</tr>
		<tr>
			<td width="120" align="right">视频名称</td>
			<td width="455"><input type="text" name="movie.mvName"/></td>
		</tr>
		<tr>
			<td align="right">视频描述</td>
			<td><textarea name="movie.mvDesc" cols="35" rows="5"/></textarea></td>
		</tr>
		<tr>
			<td align="right">视频类别</td>
			<td>
			<select name="movie.category"/>
				<option value="none">请选择类别</option>
				<c:forEach var="category" items="${categories }" >
				<option value="${category.id }">${category.categoryName }</option>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td align="right">视频时长</td>
			<td><input type="text" name="movie.mvDuration"/></td>
		</tr>
		<tr>
			<td align="right">标签</td>
			<td><input type="text" name="movie.label"/></td>
		</tr>
		<tr>
			<td align="right">视频文件</td>
			<td>
			<input type="file" name="uploader" id="videoFile" 
				style="display:none;" onChange="showValue('dvfile1',this);"/>
			<div id="dvfile1" class="dv_file">请选择文件</div><!--
		--><div class="dv_sel" onclick="selFile('videoFile')"/>选择文件</div>
			</td>
		</tr>
		<tr>
			<td align="right">缩略图</td>
			<td>
			<input type="file" name="uploader" id="imgFile" onChange="showValue('dvfile2',this);"/>
			<div id="dvfile2" class="dv_file">请选择文件</div><!--
		--><div class="dv_sel" onclick="selFile('imgFile')"/>选择文件</div>
			</td>
		</tr>
		<tr>		
			<td colspan="2" align="center" valign="middle" height="80">
			<img src="${pageContext.request.contextPath}/images/img_upload.png" id="upload"/>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>