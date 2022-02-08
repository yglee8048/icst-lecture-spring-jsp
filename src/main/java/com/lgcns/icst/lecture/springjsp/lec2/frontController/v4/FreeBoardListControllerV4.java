package com.lgcns.icst.lecture.springjsp.lec2.frontController.v4;

import com.lgcns.icst.lecture.springjsp.lec1.biz.FreeBoardBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.FreeBoardDTO;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MethodNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class FreeBoardListControllerV4 implements ControllerV4 {

    private final FreeBoardBiz freeBoardBiz;

    @Autowired
    public FreeBoardListControllerV4(FreeBoardBiz freeBoardBiz) {
        this.freeBoardBiz = freeBoardBiz;
    }

    @Override
    public String doGet(Map<String, Object> model, Map<String, String> paramMap, HttpSession session) {
        String memberId = (String) session.getAttribute(SessionKey.MEMBER_ID);
        if (memberId == null) {
            return "redirect:/lec1/member/login";
        }

        try {
            List<FreeBoardDTO> freeBoards = freeBoardBiz.findAll();
            model.put("freeBoards", freeBoards);
            return "freeBoard/list";

        } catch (Exception e) {
            model.put("errorMessage", e.getMessage());
            return "errorPage";
        }
    }

    @Override
    public String doPost(Map<String, Object> model, Map<String, String> paramMap, HttpSession session) {
        System.out.println("FreeBoardListControllerV4.doPost");
        throw new MethodNotAllowedException();
    }
}
