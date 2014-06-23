<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>欢迎界面</title>
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body>	
	<table width="100%" >
		<tr height="100px;">
			<td></td>
		</tr>
		<tr>
			<td align="center"><span style="color: red;font-size: 30px;">欢迎登录<br><br>请选择左侧菜单进行相应的操作</span></td>
		</tr>
	
	</table>


</body>
</html>