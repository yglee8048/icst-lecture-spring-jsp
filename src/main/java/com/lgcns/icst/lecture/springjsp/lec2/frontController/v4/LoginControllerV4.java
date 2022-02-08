package com.lgcns.icst.lecture.springjsp.lec2.frontController.v4;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginControllerV4 implements ControllerV4 {

    private final MemberBiz memberBiz;

    @Autowired
    public LoginControllerV4(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @Override
    public String doGet(Map<String, Object> model, Map<String, String> paramMap, HttpSession session) {
        return "member/login";
    }

    @Override
    public String doPost(Map<String, Object> model, Map<String, String> paramMap, HttpSession session) {
        String memberId = paramMap.get("memberId");
        String memberPw = paramMap.get("memberPw");

        try {
            MemberDTO memberDTO = memberBiz.login(memberId, memberPw);

            session.setAttribute(SessionKey.MEMBER_ID, memberDTO.getMemberId());
            session.setAttribute(SessionKey.MEMBER_NAME, memberDTO.getMemberName());

            return "redirect:/lec1/free-board/list";

        } catch (Exception e) {
            model.put("errorMessage", e.getMessage());
            return "errorPage";
        }
    }
}
