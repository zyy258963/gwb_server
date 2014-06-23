<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>Insert title here</title>
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
	<div style="margin-left: 10px;color: blue;"><b>当前目录：广告管理  &gt;&gt; 广告列表</b></div>
    <div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
<center>
	<form action="" name="form1" method="post" target="main">
		
		<table style="border: 1px solid;" border="1" rules="all" width="800">
			<tr>
				<th><input type="checkbox" name="checkall" value="" onclick="">
				</th>
				<th>广告名称</th>
				<th>广告图片</th>
				<th>广告链接</th>
				<th>广告位置</th>
				<th width="10%">操作</th>
			</tr>
			<c:if test="${listAd!=null }">
				<c:forEach var="ad" items="${listAd}" varStatus="status">
					<tr bgcolor="<c:if test='${status.count%2!=0 }' >#cccccc</c:if>">
						<td align="center"><input type="checkbox" name="checkad" value="<c:out value="${ad.adId}"></c:out>"
							onclick=""></td>
						<td align="center"><c:out value="${ad.adName}"></c:out></td>
						<td align="center"><c:out value="${ad.adPic}"></c:out></td>
						<td align="center"><c:out value="${ad.adUrl}"></c:out></td>
						<td align="center"><c:out value="${ad.adPosition}"></c:out></td>
						<td align="center"><a href="<%=path%>/AdminAction?type=viewAd&adId=${ad.adId}">查看</a> <a
							href="<%=path%>/AdminAction?type=deleteAd&adId=${ad.adId}" onclick="return confirm('确定要删除该条记录吗？');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${listAd==null || fn:length(listAd)<=0  }">
				<tr><td colspan="7" align="center"><c:out value="当前没有文档信息" /></td></tr>
			</c:if>
		</table>
	</form>
</center>
</body>
</html>