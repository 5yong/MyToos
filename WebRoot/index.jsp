<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function change(){
			var img = document.getElementById("vcode");
			img.src = "/MyTools/verifyCodeServlet?random="+ new Date().getTime();
		}
	
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
    <form action="/MyTools/AServlet" method="get">
    <input type="hidden" name="method" value="add">
    name:<input type="text" name="name">
    verifyCode:<input type="text" name="code">&nbsp;&nbsp;<img id="vcode" alt="" src="/MyTools/verifyCodeServlet"><a href="javascript:change()">看不清,换一个</a>
    <input type="submit" value="提交">
    </form>
  </body>
</html>
