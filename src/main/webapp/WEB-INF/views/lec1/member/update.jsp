<%@ page import="com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>회원정보 수정</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">회원정보 수정</h1>
    <%
        MemberDTO member = (MemberDTO) request.getAttribute("member");
    %>
    <form action="<%=request.getContextPath()%>/lec1/member/update" method="post">
        <div class="form-group">
            <label for="memberId"><strong>아이디</strong></label>
            <input class="form-control" type="text" id="memberId" name="memberId" value="<%=member.getMemberId()%>"
                   disabled>
        </div>
        <div class="form-group">
            <label for="memberPw"><strong>비밀번호</strong></label>
            <input class="form-control" type="password" id="memberPw" name="memberPw" value="<%=member.getMemberPw()%>"
                   required>
        </div>
        <div class="form-group">
            <label for="memberName"><strong>이름</strong></label>
            <input class="form-control" type="text" id="memberName" name="memberName"
                   value="<%=member.getMemberName()%>" required>
        </div>
        <div class="form-group">
            <label for="point"><strong>포인트</strong></label>
            <input class="form-control" type="number" id="point" name="point" value="<%=member.getPoint()%>" disabled>
        </div>
        <div class="form-group">
            <label for="rank"><strong>등급</strong></label>
            <input class="form-control" type="text" id="rank" name="rank" value="<%=member.getRank()%>" disabled>
        </div>
        <div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">수정하기</button>
            <button type="button" class="btn btn-danger btn-lg btn-block" onclick="history.back()">취소</button>
        </div>
    </form>
</div>
</body>
</html>
