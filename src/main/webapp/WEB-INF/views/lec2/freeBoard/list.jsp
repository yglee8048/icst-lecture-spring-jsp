<%@ page import="java.util.List" %>
<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey" %>
<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>게시판</title>
</head>
<body>

<h2>게시판</h2>
<div class="col-xs-12">
    <div>
        <p>
            <strong>
                <a href="<%=request.getContextPath()%>/lec2/member/update">
                    <%= session.getAttribute(SessionKey.MEMBER_NAME) %>
                </a>
            </strong> 님 안녕하세요.
            <a href="<%=request.getContextPath()%>/lec2/member/logout">로그아웃</a>
        </p>
    </div>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>번호</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성시간</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<FreeBoardDTO> freeBoards = (List<FreeBoardDTO>) request.getAttribute("freeBoards");
            for (FreeBoardDTO freeBoard : freeBoards) {
        %>
        <tr>
            <td><%= freeBoard.getId() %>
            </td>
            <td><%= freeBoard.getContent() %>
            </td>
            <td><%= freeBoard.getWriterName() %>
            </td>
            <td><%= freeBoard.getWriteDate() %>
            </td>
            <td>
                <button type="button" class="btn btn-success"
                        onclick="location.href='<%=request.getContextPath()%>/lec2/free-boards/<%=freeBoard.getId()%>/update'">
                    수정
                </button>
            </td>
            <td>
                <button type="button" class="btn btn-danger"
                        onclick="location.href='<%=request.getContextPath()%>/lec2/free-boards/<%=freeBoard.getId()%>/delete'">
                    삭제
                </button>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <button type="button" class="btn btn-primary"
            onclick="location.href='<%=request.getContextPath()%>/lec2/free-boards/write'">글쓰기
    </button>
</div>

</body>
</html>