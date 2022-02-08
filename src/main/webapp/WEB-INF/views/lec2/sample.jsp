<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>오류</title>
</head>
<body>

<h2 class="text-danger text-center"> Sample </h2>

<p class="text-center">
    오류 내용 : <%= request.getAttribute("freeBoard")%>
</p>

</body>
</html>
