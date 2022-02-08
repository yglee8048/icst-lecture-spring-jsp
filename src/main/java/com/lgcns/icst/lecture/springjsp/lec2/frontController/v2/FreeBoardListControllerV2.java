package com.lgcns.icst.lecture.springjsp.lec2.frontController.v2;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class FreeBoardListControllerV2 implements ControllerV2 {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardListControllerV2(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @Override
    public MyView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            response.sendRedirect(request.getContextPath() + "/lec1/member/login");
            return null;
        }

        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();

            request.setAttribute("freeBoards", freeBoards);
            return new MyView("/WEB-INF/views/lec1/freeBoard/list.jsp");

        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            return new MyView("/WEB-INF/views/lec1/errorPage.jsp");
        }
    }

    @Override
    public MyView doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FreeBoardListControllerV1.doPost");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return null;
    }
}
