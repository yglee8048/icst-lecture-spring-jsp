package com.lgcns.icst.lecture.springjsp.lec2.frontController.v1;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginControllerV1 implements ControllerV1 {

    private final MemberBiz memberBiz;

    @Autowired
    public LoginControllerV1(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/lec1/member/login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String memberPw = request.getParameter("memberPw");

        try {
            MemberDTO memberDTO = memberBiz.login(memberId, memberPw);

            HttpSession session = request.getSession();
            session.setAttribute(SessionKey.MEMBER_ID, memberDTO.getMemberId());
            session.setAttribute(SessionKey.MEMBER_NAME, memberDTO.getMemberName());

            response.sendRedirect(request.getContextPath() + "/lec1/free-board/list");

        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
