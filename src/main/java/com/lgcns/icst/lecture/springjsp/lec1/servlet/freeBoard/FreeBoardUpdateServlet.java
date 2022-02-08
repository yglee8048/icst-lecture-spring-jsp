package com.lgcns.icst.lecture.springjsp.lec1.servlet.freeBoard;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "freeBoardUpdateServlet-lec1", urlPatterns = "/lec1/free-board/update")
public class FreeBoardUpdateServlet extends HttpServlet {

    private FreeBoardBiz freeBoardBiz;

    @Autowired
    public void setFreeBoardBiz(FreeBoardBiz freeBoardBiz) {
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
            FreeBoardDTO freeBoard = freeBoardBiz.findFreeBoardById(Long.parseLong(id));

            req.setAttribute("freeBoard", freeBoard);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/freeBoard/update.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String content = req.getParameter("content");

        try {
            freeBoardBiz.update(Long.parseLong(id), content);
            resp.sendRedirect(req.getContextPath() + "/lec1/free-board/list");

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
