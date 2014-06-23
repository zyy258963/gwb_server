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
<title>编辑文档分类</title>
<script type="text/javascript">
function checkSub() {
	var th = document.editForm;
	if (th.categoryName.value == "") {
		alert("文档分类不能为空！！");
		th.categoryName.focus();
		return false;
	}
 
	th.action = "<%=path%>/AdminAction?type=editCategory";
		th.submit();
	}

</script>
</head>
<body>
	<div style="margin-left: 10px;color: blue;"><b>当前目录：文档分类  &gt;&gt; 编辑文档分类</b></div>
    <div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<center>
		<form action="" id="editForm" target="main" method="post" style="margin-top: 10px;"
			name="editForm">
			<input type="hidden" name="categoryId" value="${categoryId }">
			<table>
				<tr>
					<td align="right">文档分类名称：</td>
					<td align="left"><input type="text" name="categoryName"
						value="${categoryName }"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" value="提交修改"
						onclick="checkSub();">
					</td>
				</tr>

			</table>
		</form>
	</center>
</body>
</html>