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
<title>收藏夹查询</title>
<script type="text/javascript">
	function search(){
		var th = document.form1;
		th.action = "<%=path%>/servlet/UserAction?type=list";
		th.submit();
	}
	
	function turnToPage(pageType){
		var th = document.form1;
		if (pageType==1) {
			th.action = "<%=path%>/servlet/UserAction?type=list&pageNum=1";
		}else if(pageType ==2){
			th.action = "<%=path%>/servlet/UserAction?type=list&pageNum="+${pUtil.currentPage-1 };
		}else if(pageType ==3){
			th.action = "<%=path%>/servlet/UserAction?type=list&pageNum="+${pUtil.currentPage+1 };
		}else if(pageType ==4){
			th.action = "<%=path%>/servlet/UserAction?type=list&pageNum="+${pUtil.recordCount };
		}
		th.submit();
	}
	
	function validatePage(){
		var th = document.form1;
		var num1 = th.pageIndex.value;
		var num2 = ${pUtil.recordCount };
		if(parseInt(num1) > 0 && parseInt(num1) <= parseInt(num2)){
		
		}else{
			th.pageIndex.value = "";
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
	<form action="" name="form1" method="post" target="main">
<%-- 		<div style="font-size: 25px;azimuth: center;width: 800px"><b>查询条件设置</b></div>
		<table width="800" style="border: 1px solid;" border="1" rules="cols">
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
		<table style="border: 1px solid;" border="1" rules="all" width="800">
			<tr>
				<th><input type="checkbox" name="checkall" value="" onclick="">
				</th>
				<th>用户名</th>
				<th>文档名</th>
				<th>创建时间</th>
			</tr>
			<c:if test="${listFavorite!=null }">
				<c:forEach var="favorite" items="${listFavorite}" varStatus="status">
					<tr bgcolor="<c:if test='${status.count%2!=0 }' >#cccccc</c:if>">
						<td align="center"><input type="checkbox" name="checkuser" value="<c:out value="${favorite.userId}"></c:out>"
							onclick=""></td>

						<td align="center"><c:out value="${favorite.userName}"></c:out></td>
						<td align="center"><c:out value='${favorite.bookName}'></c:out></td>
						<td align="center"><c:out value="${favorite.createTs}"></c:out></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${listFavorite== null }">
				<tr><td colspan="4" align="center"><c:out value="当前没有收藏文档" /></td></tr>
			</c:if>
		</table>
	</form>
</center>
</body>
</html>