<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>게시글 수정</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">게시글 수정</h1>
    <%
        FreeBoardDTO freeBoard = (FreeBoardDTO) request.getAttribute("freeBoard");
    %>
    <form action="<%=request.getContextPath()%>/lec2/free-boards/<%=freeBoard.getId()%>/update" method="post">
        <div class="form-group">
            <label for="id"></label>
            <input type="text" id="id" name="id" value="<%=freeBoard.getId()%>" hidden>
        </div>
        <div class="form-group">
            <label for="writerName"><strong>작성자</strong></label>
            <input class="form-control" type="text" id="writerName" name="writerName"
                   value="<%=freeBoard.getWriterName()%>" disabled>
        </div>
        <div class="form-group">
            <label for="content"><strong>내용</strong></label>
            <textarea class="form-control" placeholder="내용을 작성하세요" id="content"
                      name="content"><%=freeBoard.getContent()%></textarea>
        </div>
        <div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">작성하기</button>
            <button type="button" class="btn btn-danger btn-lg btn-block" onclick="history.back()">취소</button>
        </div>
    </form>
</div>
</body>
</html>