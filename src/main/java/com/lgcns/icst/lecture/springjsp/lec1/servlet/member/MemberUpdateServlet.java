package com.lgcns.icst.lecture.springjsp.lec1.servlet.member;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "memberUpdateServlet-lec1", urlPatterns = "/lec1/member/update")
public class MemberUpdateServlet extends HttpServlet {

    private MemberBiz memberBiz;

    @Autowired
    public void setMemberBiz(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            resp.sendRedirect(req.getContextPath() + "/lec1/member/login");
            return;
        }

        try {
            MemberDTO member = memberBiz.findById(memberId);

            req.setAttribute("member", member);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/member/update.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
