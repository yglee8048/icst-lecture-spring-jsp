package com.lgcns.icst.lecture.springjsp.lec2.frontController.v2;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginControllerV2 implements ControllerV2 {

    private final MemberBiz memberBiz;

    @Autowired
    public LoginControllerV2(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @Override
    public MyView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/lec1/member/login.jsp");
    }

    @Override
    public MyView doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String memberPw = request.getParameter("memberPw");

        try {
            MemberDTO memberDTO = memberBiz.login(memberId, memberPw);

            HttpSession session = request.getSession();
            session.setAttribute(SessionKey.MEMBER_ID, memberDTO.getMemberId());
            session.setAttribute(SessionKey.MEMBER_NAME, memberDTO.getMemberName());

            response.sendRedirect(request.getContextPath() + "/lec1/free-board/list");
            return null;

        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            return new MyView("/WEB-INF/views/lec1/errorPage.jsp");
        }
    }
}
