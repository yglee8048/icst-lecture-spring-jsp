package com.lgcns.icst.lecture.springjsp.lec1.servlet.freeBoard;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "freeBoardDeleteServlet-lec1", urlPatterns = "/lec1/free-board/delete")
public class FreeBoardDeleteServlet extends HttpServlet {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardDeleteServlet(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            resp.sendRedirect(req.getContextPath() + "/lec1/member/login");
            return;
        }

        String id = req.getParameter("id");

        try {
            freeBoardBiz.delete(Long.parseLong(id));
            resp.sendRedirect(req.getContextPath() + "/lec1/free-board/list");

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
