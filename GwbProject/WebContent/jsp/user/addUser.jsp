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
<title>添加新用户</title>
<script type="text/javascript">
function checkSub() {
	var th = document.addForm;
	if (th.userName.value == "") {
		alert("用户名不能为空！！");
		th.userName.focus();
		return false;
	}
	if (th.password.value == "") {
		alert("密码不能为空！！");
		th.password.focus();
		return false;;
	}
	if (th.password1.value == "") {
		alert("重复密码不能为空！！");
		th.password1.focus();
		return false;;
	}
	if (th.password.value != th.password1.value) {
		alert("两次密码输入不一致！！");
		th.password1.focus();
		return false;;
	}
	if (th.telephone.value == "") {
		alert("电话号码不能为空！！");
		th.telephone.focus();
		return false;;
	}
	th.action = "<%=path%>/AdminAction?type=addUser";
		th.submit();
	}
</script>
</head>
<body>
	<div style="margin-left: 10px;color: blue;"><b>当前目录：用户管理  &gt;&gt; 添加新用户</b></div>
	<div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<center>
		<form action="" id="addForm" target="main" method="post" style="margin-top: 10px;"
			name="addForm">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td align="left"><input type="text" name="userName" value="${userName }"></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td align="left"><input type="password" name="password" value="${password }"></td>
				</tr>
				<tr>
					<td align="right">重复密码：</td>
					<td align="left"><input type="password" name="password1" value="${password1 }">
					</td>
				</tr>
				<tr>
					<td align="right">电话：</td>
					<td align="left"><input type="text" name="telephone" value="${telephone }">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" value="提交"
						onclick="checkSub();">
					</td>
				</tr>

			</table>
		</form>
	</center>
</body>
</html>