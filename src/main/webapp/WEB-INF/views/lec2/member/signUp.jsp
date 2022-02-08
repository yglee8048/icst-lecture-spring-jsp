<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
    <title>회원가입</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">회원가입</h1>
    <form action="<%=request.getContextPath()%>/lec2/member/sign-up" method="post">
        <div class="form-group">
            <label for="memberId"><strong>아이디</strong></label>
            <input type="text" class="form-control" placeholder="아이디를 입력하세요" id="memberId" name="memberId" required>
        </div>
        <div class="form-group">
            <label for="memberPw"><strong>비밀번호</strong></label>
            <input type="password" class="form-control" placeholder="비밀번호를 입력하세요" id="memberPw" name="memberPw"
                   required>
        </div>
        <div class="form-group">
            <label for="memberName"><strong>이름</strong></label>
            <input type="text" class="form-control" placeholder="아이디를 입력하세요" id="memberName" name="memberName" required>
        </div>
        <div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">회원가입</button>
            <button type="button" class="btn btn-danger btn-lg btn-block" onclick="history.back()">취소
            </button>
        </div>
    </form>
</div>
</body>
</html>