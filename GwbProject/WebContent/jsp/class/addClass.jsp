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
	if (th.className.value == "") {
		alert("专业名称不能为空！！");
		th.className.focus();
		return false;
	}
	if (th.categoryId.options.selectedIndex <= 0) {
		alert("请选择文档分类！！");
		return false;
	}
	th.action = "<%=path%>/AdminAction?type=addClass";
		th.submit();
	}
</script>
</head>
<body>
	<div style="margin-left: 10px;color: blue;"><b>当前目录：专业管理  &gt;&gt; 添加新专业</b></div>
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
					<td align="right">专业名称：</td>
					<td align="left"><input type="text" name="className" value="${className }"></td>
				</tr>
				<tr>
					<td align="right">所属文档分类：</td>
					<td align="left"><select name="categoryId">
							<option value="0">----请选择文档分类----</option>							
							<c:forEach items="${listCategoryMaps }" var="maps">
								<c:forEach items="${maps}" var="categoryMap">
									<option value="${categoryMap.key}" <c:if test="${categoryMap.key == categoryId }">selected="selected"</c:if> >${categoryMap.value}</option>
								</c:forEach>
							</c:forEach>
					</select> </td>
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