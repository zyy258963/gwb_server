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
<title>查看文档分类</title>
<script type="text/javascript">
	function search(){
		var th = document.form1;
		th.action = "<%=path%>/AdminAction?type=listCategory";
		th.submit();
	}
	
	function turnToPage(pageType){
		var th = document.form1;
		if (pageType==1) {
			th.action = "<%=path%>/AdminAction?type=listCategory&pageNum=1";
		}else if(pageType ==2){
			th.action = "<%=path%>/AdminAction?type=listCategory&pageNum="+${pUtil.currentPage-1 };
		}else if(pageType ==3){
			th.action = "<%=path%>/AdminAction?type=listCategory&pageNum="+${pUtil.currentPage+1 };
		}else if(pageType ==4){
			th.action = "<%=path%>/AdminAction?type=listCategory&pageNum="+${pUtil.recordCount };
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
<div style="margin-left: 10px;color: blue;"><b>当前目录：文档分类  &gt;&gt; 文档分类查询</b></div>
    
     <div style="margin-top: 10px;font-style: normal;color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>       
            
<center>
	<form action="" name="form1" method="post" target="main">
		<div style="font-size: 25px;azimuth: center;width: 800px"><b>查询条件设置</b></div>
		<table width="800" style="border: 1px solid;" border="1" rules="cols">
			<tr>
				<td align="right" class="STYLE7">文档分类名称：</td>
				<td align="left"><input type="text" name="keywords" value="${keywords }">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:search();">查询</a></td>
				<td align="center"><a href="<%=path%>/jsp/category/addCategory.jsp" target="main">添加新文档分类</a>
			</tr>
		</table>
		<div style="height: 20px;"></div>
		<div style="font-size: 25px;azimuth: center;width: 800px"><b>查询结果如下</b></div>
		<table style="border: 1px solid;" border="1" rules="all" width="800">
			<tr>
				<th><input type="checkbox" name="checkall" value="" onclick="">
				</th>
				<th>文档分类</th>
				<th>操作</th>
			</tr>
			<c:if test="${listCategory!=null || fn:length(listCategory)<=0 }">
				<c:forEach var="category" items="${listCategory}" varStatus="status">
					<tr bgcolor="<c:if test='${status.count%2!=0 }' >#cccccc</c:if>">
						<td align="center"><input type="checkbox" name="checkcategory" value="<c:out value="${category.categoryId}"></c:out>"
							onclick=""></td>

						<td align="center"><c:out value="${category.categoryName}"></c:out></td>
						<td align="center"><a href="<%=path%>/AdminAction?type=viewCategory&categoryId=${category.categoryId}">修改</a> <a
							href="<%=path%>/AdminAction?type=deleteCategory&categoryId=${category.categoryId}" onclick="return confirm('确定要删除该条记录吗？');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${listCategory==null }">
				<tr><td colspan="3" align="center"><c:out value="当前没有文档分类的信息" /></td></tr>
			</c:if>
			<tr>
				<td colspan="3" align="right"><span class="STYLE7">共&nbsp;<b><c:out
							value="${pUtil.recordCount }"></c:out></b>&nbsp;条记录</span>&nbsp; 
							<span class="STYLE7">当前是第&nbsp;<b>${pUtil.currentPage }/${totalPages}</b>&nbsp;页</span>&nbsp; 
							<input type="button" value="首页" onclick="turnToPage(1);">&nbsp;
							<input type="button" value="上一页" onclick="turnToPage(2);">&nbsp;
							<input type="button" value="下一页" onclick="turnToPage(3);">&nbsp;
							<input type="button" value="尾页" onclick="turnToPage(4);">&nbsp;
					 <span class="STYLE7">跳转到</span> 
							<input type="text" name="pageIndex" width="3" style="width: 30px;" onkeyup="javascript:validatePage();">页&nbsp;&nbsp;
						<input type="button" onclick="javascript:search();" value="跳转">
				</td>
			</tr>
		</table>
	</form>
</center>
</body>
</html>