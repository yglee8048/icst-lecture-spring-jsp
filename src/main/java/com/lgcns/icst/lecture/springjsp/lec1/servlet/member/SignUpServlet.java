package com.lgcns.icst.lecture.springjsp.lec1.servlet.member;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signUpServlet-lec1", urlPatterns = "/lec1/member/sign-up")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/member/signUp.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String memberId = req.getParameter("memberId");
        String memberPw = req.getParameter("memberPw");
        String memberName = req.getParameter("memberName");

        MemberBiz memberBiz = new MemberBiz();
        try {
            memberBiz.signUp(memberId, memberPw, memberName);
            resp.sendRedirect(req.getContextPath() + "/lec1/member/login");

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
