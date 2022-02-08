package com.lgcns.icst.lecture.springjsp.lec1.servlet.freeBoard;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "freeBoardListServlet-lec1", urlPatterns = "/lec1/free-board/list")
public class FreeBoardListServlet extends HttpServlet {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardListServlet(FreeBoardBiz freeBoardBiz) {
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

        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();

            req.setAttribute("freeBoards", freeBoards);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/freeBoard/list.jsp");
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/lec1/errorPage.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
