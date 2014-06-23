<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"   %>
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
<title>编辑文档信息</title>

</head>
<body>
	<div style="margin-left: 10px;color: blue;"><b>当前目录：广告管理  &gt;&gt; 查看广告详细</b></div>
	<div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<center>
			<table>
				<tr>
					<td align="right">显示位置：</td>
					<td align="left"><select name="adPoisition">
							<option value="1" <c:if test="${ad.adPosition==1}">selected="selected"</c:if>>----首页----</option>
							<option value="2" <c:if test="${ad.adPosition==2}">selected="selected"</c:if> >----专业页面----</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">广告名称：</td>
					<td align="left"><input type="text" name="adName" value="${ad.adName }">
					</td>
				</tr>
				<tr>
					<td align="right">广告图片：</td>
					<td align="left"><img name="adPic" src="${adSrc}" width="400px" height="300px">
					</td>
				</tr>
				<tr>
					<td align="right">广告链接：</td>
					<td align="left"><input type="text" name="adUrl" value="${ad.adUrl }">
					</td>
				</tr>
				
			</table>
	</center>
</body>
</html>