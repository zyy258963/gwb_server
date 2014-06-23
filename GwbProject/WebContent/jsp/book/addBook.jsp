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
	if (trimStr(th.bookName.value) == "" ) {
		alert("文档名称不能为空！！");
		th.bookName.focus();
		return false;
	}
	if (th.categoryId.options.selectedIndex <= 0) {
		alert("请选择文档分类！！");
		return false;
	}
	if (th.classId.options.selectedIndex <= 0) {
		alert("请添加专业分类！！");
		return false;
	}
	if (trimStr(th.bookUrl.value) == "" ) {
		alert("文档内容不能为空！！");
		th.bookUrl.focus();
		return false;
	}
	th.action = "<%=path%>/AdminAction?type=addBook";
	th.submit();
	}
	
function changeClassId(){
	var th = document.addForm;
	th.action = "<%=path%>/AdminAction?type=changeBookClass&action=add";
	th.submit();
	}
</script>
</head>
<body>
	<div style="margin-left: 10px; color: blue;">
		<b>当前目录：文档管理 &gt;&gt; 添加新文档</b>
	</div>
	<div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<center>
		<form action="" id="addForm" target="main" method="post"  enctype="multipart/form-data"
			style="margin-top: 50px;" name="addForm">
			<table>
				<tr>
					<td align="right">所属文档分类：</td>
					<td align="left"><select name="categoryId"
						onchange="changeClassId();">
							<option value="0">----请选择文档分类----</option>
							<c:forEach items="${listCategoryMaps }" var="cateMaps">
								<c:forEach items="${cateMaps }" var="map">
									<option value="${map.key }"
										<c:if test="${map.key == categoryId }">selected</c:if>>${map.value}</option>
								</c:forEach>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">所属专业：</td>
					<td align="left"><select name="classId">
							<option value="0">----请选择专业----</option>
							<c:forEach items="${listClassMaps }" var="classMaps">
								<c:forEach items="${classMaps }" var="map">
									<option value="${map.key }"
									<c:if test="${map.key == classId }">selected</c:if>>${map.value}</option>
								</c:forEach>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">文档名称：</td>
					<td align="left"><input type="text" name="bookName" value="${bookName }">
					</td>
				</tr>
				<tr>
					<td align="right">文档描述：</td>
					<td align="left"><textarea rows="5" cols="30" name="bookDesc" >${bookDesc }</textarea>
					</td>
				</tr>
				<tr>
					<td align="right">文档内容：</td>
					<td align="left"><input type="file" name="bookUrl" value="">
					
					<!-- //		版本2-----单独上传文件然后指定目录 -->
					<%-- <select name="bookUrl" size="10" >
						<c:forEach items="${listFiles}" var="f" varStatus="fStatus">
							<option value="${f}" <c:if test="${fStatus.index%2==0 }">style="background-color: #cccccc"</c:if> >${f}</option>
						</c:forEach>
					</select> --%>
					<%-- <textarea rows="10" cols="100" name="bookUrl">
						<c:forEach items="${listFiles}" var="f" varStatus="fStatus">
							${f}
						</c:forEach>
					</textarea> --%>
					
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