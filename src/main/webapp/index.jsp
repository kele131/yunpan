<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charsetutf-8">
<title>welcome</title>
</head>
<body>
	<h2>Hello World!!</h2>
	<form action="./login/welcome" method="post">
		name: <input id="username" name="username" type="text"></input><br>
		pass: <input id="password" name="password" type="password"></input><br>
		<input type="submit">
	</form>
	<span>ip:<%=request.getRemoteAddr() %>></span>
</body>
</html>