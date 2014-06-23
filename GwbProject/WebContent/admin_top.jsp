<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<base href="<%=basePath%>">
<title>管理页面</title>
<script type="text/javascript" >
function logout() {
	var th = document.logoutForm;
	if(confirm("确定要退出登录？")){
		th.action = "<%=path%>/LoginAction?type=logout";
		th.submit();
	}	
}
</script>
<base target="main">
<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<form action="" method="post" id="logoutForm" name="logoutForm"  target="_top">
	<table width="100%" height="64" border="0" cellpadding="0"
		cellspacing="0" class="admin_topbg">
		<tr>
			<td width="61%" height="64"><img src="images/logo.gif"
				width="262" height="64">
			</td>
			<td width="39%" valign="top">
			
				<table width="100%" border="0"
						cellspacing="0" cellpadding="0">
					
					<tr>
						<td width="74%" height="64" class="admin_txt"><b><c:out value="${user_name }"></c:out> </b>
							您好,感谢登陆使用！</td>
						<td width="22%"><img src="images/out.gif" alt="安全退出" onclick="logout();"
								width="46" height="20" border="0">
						</td>
						<td width="4%">&nbsp;</td>
					</tr>
					<tr>
						<td height="19" colspan="3">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
