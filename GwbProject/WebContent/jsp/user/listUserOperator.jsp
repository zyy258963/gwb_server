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
<title>操作记录查询</title>
<script type="text/javascript">
function checkSelectAll() {
	var checkAllBoox = document.getElementById("checkAll");
	if (checkAllBoox.checked == true) {
		var checkObj = document.getElementsByName("checkuser");
		for ( var i = 0; i < checkObj.length; i++) {
			if (checkObj[i].checked == false) {
				checkObj[i].checked = true;
			}
		}
	} else {
		var checkObj = document.getElementsByName("checkuser");
		for ( var i = 0; i < checkObj.length; i++) {
			if (checkObj[i].checked == true) {
				checkObj[i].checked = false;
			}
		}
	}
}

function deleteMany() {
	var checkObj = document.getElementsByName("checkuser");
	var flag = false;
	var idStr = "";
	for ( var i = 0; i < checkObj.length; i++) {
		if (checkObj[i].checked == true) {
			if (flag) {
				idStr += ",'"+checkObj[i].value + "'";
			}else {
				flag = true;
				idStr += "'"+checkObj[i].value + "'";
			}
		}
	}
	if (!flag) {
		alert("您没有选中任何数据，无法进行批量操作。");
		return false;
	}
	
	if (confirm('确认删除多条记录吗？')){
		var th = document.form1;
		th.action = "<%=path%>/AdminAction?type=deleteManyUserOperator&checkIds="+idStr;
	    th.submit();
	}else{
		return false;
	}
}
</script>
<STYLE type=text/css>
.neon {
	FILTER: glow(color=#002E60,strength=3)
}
BODY {
	MARGIN: 0px
}
BODY {
	MARGIN-TOP: 0px; SCROLLBAR-FACE-COLOR: #005fc5; FONT-SIZE: 15px; BACKGROUND: #ffffff; SCROLLBAR-HIGHLIGHT-COLOR: #799ae1; SCROLLBAR-SHADOW-COLOR: #799ae1; SCROLLBAR-3DLIGHT-COLOR: #005fc5; SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-TRACK-COLOR: #aabfec; SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}
</STYLE>
<LINK href="<%=path %>/images/duan_1.css" type=text/css rel=stylesheet>
</head>
<body bgColor="#ffffff">
<center>
<div style="margin-top: 10px; font-style: normal; color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<form action="" name="form1" method="post" >
		<input type="hidden" name="operatorUserId" value='<c:out value="${operatorUserId}"></c:out>'>
		<!-- <h2>操作记录查询</h2> -->
		<%-- <div style="font-size: 25px;azimuth: center;width: 100%"><b>查询条件设置</b></div>
		<table width="100%" style="border: 1px solid;" border="1" rules="cols">
			<tr>
				<td align="right" class="STYLE7">用户名：</td>
				<td align="left"><input type="text" name="keywords" value="${keywords }"></td>
				<td align="left"><input type="checkbox" name="isAvailable" value="0" <c:if test="${isAvailable == 0 }">checked</c:if>>是否锁定（<b>锁定</b>请选中）</td>
				<td><a href="javascript:search();">查询</a></td>
				<td align="center"><a href="<%=path%>/jsp/user/addUser.jsp" target="main">添加新用户</a>
				</td>
			</tr>
		</table> --%>
		<div style="height: 20px;"></div>
		<div style="font-size: 25px;azimuth: center;width: 800px"><b>查询结果如下</b></div>
		
		<table border="0" rules="all" width="800">
				<tr height="40px;">
					<td colspan="5" align="right" >
						<input type="button"  onclick="deleteMany();" style="color: red;" value="批量删除">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			
		
		<table style="border: 1px solid;" border="1" rules="all" >
			<tr>
				<th><input id="checkAll" type="checkbox" name="checkall"
						value="" onclick="checkSelectAll();">
				</th>
				<th>用户名</th>
				<th>操作类型</th>
				<th>关键字</th>
				<th>操作时间</th>
			</tr>
			<c:if test="${listOperator!=null }">
				<c:forEach var="operator" items="${listOperator}" varStatus="status">
					<tr bgcolor="<c:if test='${status.count%2!=0 }' >#cccccc</c:if>">
						<td align="center"><input type="checkbox" name="checkuser" value="${operator.logId}"
							onclick=""></td>
						<td align="center"><c:out value="${operator.userName}"></c:out></td>
						<td align="center"><c:out value='${operator.logType}'></c:out></td>
						<td align="center"><c:out value="${operator.logInfo}"></c:out></td>	
						<td align="center"><c:out value="${operator.logTs}"></c:out></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${listOperator==null }">
				<tr><td colspan="5" align="center"><c:out value="当前没有用户信息" /></td></tr>
			</c:if>
		</table>
	</form>
</center>
</body>
</html>