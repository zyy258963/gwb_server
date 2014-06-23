<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<title>添加新文档</title>
<script type="text/javascript">
function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

function checkSub() {
	var th = document.addForm;
	if (trimStr(th.adPic.value) == "" ) {
		alert("广告图片不能为空！！");
		th.adPic.focus();
		return false;
	}
	if (trimStr(th.adPic.value) == "" ) {
		alert("图片链接不能为空！！");
		th.adPic.focus();
		return false;
	}
	th.action = "<%=path%>/AdminAction?type=addAd";
	th.submit();
	}
	
</script>
</head>
<body>
	<div style="margin-left: 10px; color: blue;">
		<b>当前目录：广告管理 &gt;&gt; 添加新广告</b>
	</div>
	<center>
		<form action="" id="addForm" target="main" method="post"  enctype="multipart/form-data"
			style="margin-top: 50px;" name="addForm">
			<table>
				<tr>
					<td align="right">显示位置：</td>
					<td align="left"><select name="adPosition">
							<option value="1">----首页----</option>
							<option value="2">----专业页面----</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">广告名称：</td>
					<td align="left"><input type="text" name="adName" >
					</td>
				</tr>
				<tr>
					<td align="right">广告图片：</td>
					<td align="left"><input type="file"  name="adPic">
					</td>
				</tr>
				<tr>
					<td align="right">广告链接：</td>
					<td align="left"><input type="text" name="adUrl" >
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><input type="button" value="提交"
						onclick="checkSub();"></td>
				</tr>

			</table>
		</form>
	</center>
</body>
</html>