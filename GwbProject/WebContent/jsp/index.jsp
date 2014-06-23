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
<title>网站管理员登陆</title>
<link href="images/skin.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	function checkLogin() {
		var th = document.loginForm;
		th.action = "<%=path%>/IosLoginAction?type=appLogin";
		th.submit();
	}
</script>
</head>
<body>
		<table width="100%" height="166" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="42" valign="top"><table width="100%" height="42"
						border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
						<tr>
							<td width="1%" height="21">&nbsp;</td>
							<td height="42">&nbsp;</td>
							<td width="17%">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr align="center">
				<td valign="top" align="center"><table width="100%" height="532" border="0"
						cellpadding="0" cellspacing="0" class="login_bg">
						<tr>
							<td width="40%">&nbsp;</td>
							<td width="50%" valign="bottom" align="center"><table width="100%"
									height="59" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td>&nbsp;</td>
										<td width="96%" height="38"><span class="login_txt_bt">登陆信息网后台管理</span>
										</td>
										
									</tr>
									<tr>
									<td colspan="2"><c:if test="${respMsg!=null}"><span style="color: red;">${respMsg}</span></c:if></td>
									</tr>	
									<tr>
										<td>&nbsp;</td>
										<td height="21"><table cellSpacing="0" cellPadding="0"
												width="100%" border="0" id="table211" height="328">
												<tr>
													<td height="164" colspan="2" align="center"><form
															name="loginForm" action="" method="post">
															<table cellSpacing="0" cellPadding="0" width="100%"
																border="0" height="143" id="table212">
																<tr>
																	<td width="13%" height="38" class="top_hui_text" align="right"><span
																		class="login_txt">管理员：&nbsp;&nbsp; </span>
																	</td>
																	<td height="38" colspan="2" class="top_hui_text" align="left"><input
																		name="telephone" class="editbox4" value="" size="20">
																	</td>
																</tr>
																<tr>
																	<td width="13%" height="35" class="top_hui_text" align="right"><span
																		class="login_txt"> 密 码： &nbsp;&nbsp; </span>
																	</td>
																	<td height="35" colspan="2" class="top_hui_text" align="left"><input
																		class="editbox4" type="password" size="20"
																		name="password"> <img src="images/luck.gif"
																		width="19" height="18"></td>
																</tr>
																<tr>
																	<td width="13%" height="35" class="top_hui_text" align="right"><span
																		class="login_txt"> mac地址： &nbsp;&nbsp; </span>
																	</td>
																	<td height="35" colspan="2" class="top_hui_text" align="left"><input
																		class="editbox4" type="text" size="20" value="11111111111111"
																		name="macAddress"> <img src="images/luck.gif"
																		width="19" height="18"></td>
																</tr>
																<tr>
																	<td width="13%" height="35" ><input name="sub" onclick="checkLogin();"
																		type="button" class="button"  value="登 陆">
																	</td>
																	
																</tr>
															</table>
															<br>
														</form>
													</td>
												</tr>
												<tr>
													<td width="433" height="164" align="right" valign="bottom"><img
														src="images/login-wel.gif" width="242" height="138">
													</td>
													<td width="57" align="right" valign="bottom">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20"><table width="100%" border="0" cellspacing="0"
						cellpadding="0" class="login-buttom-bg">
						<tr>
							<td align="center"><span class="login-buttom-txt">Copyright
									&copy; 2014-2014 </span>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</body>
</html>
