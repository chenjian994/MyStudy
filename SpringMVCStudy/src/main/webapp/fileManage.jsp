<%--
  Created by IntelliJ IDEA.
  User: chenjian
  Date: 13-10-27
  Time: 上午8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>资源管理</title>
</head>
<body>
上传:
<form action="/SpringMVCStudy/resource/upload" method="post" enctype="multipart/form-data">
    文件名：<input type="text" name="fileName"/><br/>
    请选择文件：<input type="file" name="file"/><br/>
    <input type="submit" value="上传"/>
</form>
<hr/>
资源列表：
    <ul>
    <c:forEach var="f" items="${showFiles}">
        <li><a href='<c:out value="${f.url}"/>'><c:out value="${f.name}"/></a></li>
    </c:forEach>
    </ul>
</body>
</html>