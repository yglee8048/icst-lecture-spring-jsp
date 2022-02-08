package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginControllerV3 implements ControllerV3 {

    private final MemberBiz memberBiz;

    @Autowired
    public LoginControllerV3(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @Override
    public MyModelAndView doGet(Map<String, String> paramMap, HttpSession session) {
        return new MyModelAndView("member/login");
    }

    @Override
    public MyModelAndView doPost(Map<String, String> paramMap, HttpSession session) {
        String memberId = paramMap.get("memberId");
        String memberPw = paramMap.get("memberPw");

        try {
            MemberDTO memberDTO = memberBiz.login(memberId, memberPw);

            session.setAttribute(SessionKey.MEMBER_ID, memberDTO.getMemberId());
            session.setAttribute(SessionKey.MEMBER_NAME, memberDTO.getMemberName());

            return new MyModelAndView("redirect:/lec1/free-board/list");

        } catch (Exception e) {
            MyModelAndView myModelAndView = new MyModelAndView("errorPage");
            myModelAndView.addObject("errorMessage", e.getMessage());

            return myModelAndView;
        }
    }
}
