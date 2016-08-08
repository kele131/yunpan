<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>文件主页</title>
</head>
<body>

 <form action="<%=request.getContextPath()%>/files/Upload" method="POST" enctype="multipart/form-data"> 
            你的文件: <input type="file" name="file"/><br/> 
            </br>
            </br>
            <input type="submit" value="上传"/> 
        </form> 

</body>
</html>