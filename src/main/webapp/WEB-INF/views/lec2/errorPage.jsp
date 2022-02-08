<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>오류</title>
</head>
<body>

<h2 class="text-danger text-center"> 오류가 발생했습니다. </h2>

<p class="text-center">
    오류 내용 : <%= request.getAttribute("errorMessage")%>
</p>

</body>
</html>
