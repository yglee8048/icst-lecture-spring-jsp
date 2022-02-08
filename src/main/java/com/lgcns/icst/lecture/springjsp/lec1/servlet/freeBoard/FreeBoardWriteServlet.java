package com.lgcns.icst.lecture.springjsp.lec1.servlet.freeBoard;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import lombok.RequiredArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "freeBoardWriteServlet-lec6", urlPatterns = "/lec1/free-board/write")
@RequiredArgsConstructor
public class FreeBoardWriteServlet extends HttpServlet {

    private final FreeBoardBiz freeBoardBiz;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            resp.sendRedirect(req.getContextPath() + "/lec1/member/login");
            return;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/freeBoard/write.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");

        HttpSession session = req.getSession();
        String writerId = (String) session.getAttribute(SessionKey.MEMBER_ID);

        try {
            freeBoardBiz.save(content, writerId);
            resp.sendRedirect(req.getContextPath() + "/lec1/free-board/list");

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
