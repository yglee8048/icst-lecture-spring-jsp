<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>게시글 작성</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">게시글 작성</h1>
    <form action="<%=request.getContextPath()%>/lec2/free-boards/write" method="post">
        <div class="form-group">
            <label for="writerName"><strong>작성자</strong></label>
            <input class="form-control" type="text" id="writerName" name="writerName"
                   value="<%=session.getAttribute(SessionKey.MEMBER_NAME)%>" disabled>
        </div>
        <div class="form-group">
            <label for="content"><strong>내용</strong></label>
            <textarea class="form-control" placeholder="내용을 작성하세요" id="content" name="content"></textarea>
        </div>
        <div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">작성하기</button>
            <button type="button" class="btn btn-danger btn-lg btn-block" onclick="history.back()">취소</button>
        </div>
    </form>
</div>
</body>
</html>