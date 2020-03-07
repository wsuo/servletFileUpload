<%--
  Created by IntelliJ IDEA.
  User: wsuo
  Date: 2020/3/4 0004
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
${message}<br>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td align="center" colspan="2">文件上传</td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" size="30"></td>
        </tr>
        <tr>
            <td>文件:</td>
            <td><input type="file" name="filename" size="30"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
<%! int count = 0;%>

<%=count%>
</body>
</html>
