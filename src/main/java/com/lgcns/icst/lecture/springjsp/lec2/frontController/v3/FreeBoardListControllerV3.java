package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class FreeBoardListControllerV3 implements ControllerV3 {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardListControllerV3(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @Override
    public MyModelAndView doGet(Map<String, String> paramMap, HttpSession session) {
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            return new MyModelAndView("redirect:/lec1/member/login");
        }

        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();

            MyModelAndView myModelAndView = new MyModelAndView("freeBoard/list");
            myModelAndView.addObject("freeBoards", freeBoards);
            return myModelAndView;

        } catch (Exception e) {
            MyModelAndView myModelAndView = new MyModelAndView("errorPage");
            myModelAndView.addObject("errorMessage", e.getMessage());
            return myModelAndView;
        }
    }

    @Override
    public MyModelAndView doPost(Map<String, String> paramMap, HttpSession session) {
        System.out.println("FreeBoardListControllerV3.doPost");
        throw new MethodNotAllowedException();
    }
}
