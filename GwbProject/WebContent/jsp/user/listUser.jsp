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
<title>Insert title here</title>
<script type="text/javascript">
	function search(){
		var th = document.form1;
		th.action = "<%=path%>/AdminAction?type=listUser";
		th.submit();
	}
	
	function turnToPage(pageType){
		var th = document.form1;
		if (pageType==1) {
			th.action = "<%=path%>/AdminAction?type=listUser&pageNum=1";
		}else if(pageType ==2){
			th.action = "<%=path%>/AdminAction?type=listUser&pageNum="+${pUtil.currentPage-1 };
		}else if(pageType ==3){
			th.action = "<%=path%>/AdminAction?type=listUser&pageNum="+${pUtil.currentPage+1 };
		}else if(pageType ==4){
			th.action = "<%=path%>/AdminAction?type=listUser&pageNum="+${pUtil.recordCount};
		}
		th.submit();
	}

	function validatePage() {
		var th = document.form1;
		var num1 = th.pageIndex.value;
		var num2 = ${pUtil.recordCount};
		if (parseInt(num1) > 0 && parseInt(num1) <= parseInt(num2)) {

		} else {
			th.pageIndex.value = "";
		}
	}

	function confirmDelete() {
		if (!confirm('确认删除吗？'))
			return false;
	}

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
			th.action = "<%=path%>/AdminAction?type=deleteManyUser&checkIds="+idStr;
		    th.submit();
		}else{
			return false;
		}
	}
</script>
<STYLE type=text/css>
.neon {
	FILTER: glow(color = #002E60, strength = 3)
}

BODY {
	MARGIN: 0px
}

BODY {
	MARGIN-TOP: 0px;
	SCROLLBAR-FACE-COLOR: #005fc5;
	FONT-SIZE: 15px;
	BACKGROUND: #ffffff;
	SCROLLBAR-HIGHLIGHT-COLOR: #799ae1;
	SCROLLBAR-SHADOW-COLOR: #799ae1;
	SCROLLBAR-3DLIGHT-COLOR: #005fc5;
	SCROLLBAR-ARROW-COLOR: #ffffff;
	SCROLLBAR-TRACK-COLOR: #aabfec;
	SCROLLBAR-DARKSHADOW-COLOR: #799ae1
}
</STYLE>
<LINK href="<%=path%>/images/duan_1.css" type=text/css rel=stylesheet>
</head>
<body bgColor="#ffffff">
	<div style="margin-left: 10px; color: blue;">
		<b>当前目录：用户管理 &gt;&gt; 用户查询</b>
	</div>
	<div style="margin-top: 10px; font-style: normal; color: red;">
		<c:if test="${respMsg!= null }">
			<b>${respMsg }</b>
		</c:if>
	</div>
	<center>
		<form action="" name="form1" method="post" target="main">
			<div style="font-size: 25px; azimuth: center; width: 800px">
				<b>查询条件设置</b>
			</div>
			<table width="800" style="border: 1px solid;" border="1" rules="cols">
				<tr>
					<td align="right" class="STYLE7">用户名：</td>
					<td align="left"><input type="text" name="keywords"
						value="${keywords }">
					</td>
					<td align="left"><input type="checkbox" name="isAvailable"
						value="0" <c:if test="${isAvailable == 0 }">checked</c:if>>是否锁定（<b>锁定</b>请选中）</td>
					<td><a href="javascript:search();">查询</a>
					</td>
					<td align="center"><a href="<%=path%>/jsp/user/addUser.jsp"
						target="main">添加新用户</a></td>
				</tr>
			</table>
			<div style="height: 20px;"></div>
			<div style="font-size: 25px; azimuth: center; width: 800px">
				<b>查询结果如下</b>
			</div>
			<table border="0" rules="all" width="800">
				<tr height="40px;">
					<td colspan="5" align="right" >
						<input type="button"  onclick="deleteMany();" style="color: red;" value="批量删除">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			
			<table style="border: 1px solid;" border="1" rules="all" width="800">
				<tr>
					<th><input id="checkAll" type="checkbox" name="checkall"
						value="" onclick="checkSelectAll();">序号</th>
					<th>用户名</th>
					<th>手机号</th>
					<th>当前状态</th>
					<th>操作</th>
				</tr>
				<c:if test="${listUser!=null }">
					<c:forEach var="user" items="${listUser}" varStatus="status">
						<tr bgcolor="<c:if test='${status.count%2!=0 }' >#cccccc</c:if>">
							<td align="center"><input type="checkbox" name="checkuser" 
								value="<c:out value="${user.userId}"></c:out>" onclick="">
								<c:out value="${startNo+status.index}"></c:out></td>
							<td align="center"><c:out value="${user.userName}"></c:out>
							</td>
							<td align="center"><c:out value='${user.telephone}'></c:out>
							</td>
							<td align="center"><c:if test="${user.isAvailable == 0 }">
									<span style="color: red;"><b>锁定</b>
									</span>&nbsp;&nbsp;</c:if> <c:if test="${user.isAvailable == 1 }">
									<span style="color: green;"><b>未锁定</b>
									</span>&nbsp;&nbsp;</c:if> <c:if test="${user.macAddress=='0' }">
									<span style="color: red;"><b>未绑定</b>
									</span>&nbsp;&nbsp;</c:if> <c:if test="${user.macAddress!='0' }">
									<span style="color: green;"><b>已绑定</b>
									</span>&nbsp;&nbsp;</c:if></td>
							<td align="center"><a
								href="<%=path%>/AdminAction?type=viewUser&userId=${user.userId}">修改</a>&nbsp;&nbsp;<a
								href="<%=path%>/AdminAction?type=unbindUser&userId=${user.userId}"
								onclick="return confirm('确认解除该用户的绑定吗？');">解除绑定</a>&nbsp;&nbsp;<a
								href="<%=path%>/AdminAction?type=deleteUser&userId=${user.userId}"
								onclick="return confirm('确认删除这条记录吗？');">删除</a>&nbsp;&nbsp;<a
								href="<%=path%>/AdminAction?type=listOperator&userId=${user.userId}"
								target="_blank">操作记录</a>&nbsp;&nbsp;<a
								href="<%=path%>/AdminAction?type=listFavorite&userId=${user.userId}"
								target="_blank">收藏夹</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${listUser==null }">
					<tr>
						<td colspan="5" align="center"><c:out value="当前没有用户信息" />
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="5" align="right"><span class="STYLE7">共&nbsp;<b><c:out
									value="${pUtil.recordCount }"></c:out>
						</b>&nbsp;条记录</span>&nbsp; <span class="STYLE7">当前是第&nbsp;<b>${pUtil.currentPage
								}/${totalPages}</b>&nbsp;页</span>&nbsp; <input type="button" value="首页"
						onclick="turnToPage(1);">&nbsp; <input type="button"
						value="上一页" onclick="turnToPage(2);">&nbsp; <input
						type="button" value="下一页" onclick="turnToPage(3);">&nbsp;
						<input type="button" value="尾页" onclick="turnToPage(4);">&nbsp;
						<span class="STYLE7">跳转到</span> <input type="text"
						name="pageIndex" width="3" style="width: 30px;"
						onkeyup="javascript:validatePage();">页&nbsp;&nbsp; <input
						type="button" onclick="javascript:search();" value="跳转"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>