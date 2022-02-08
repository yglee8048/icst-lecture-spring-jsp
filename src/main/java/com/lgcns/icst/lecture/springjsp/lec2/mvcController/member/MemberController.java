package com.lgcns.icst.lecture.springjsp.lec2.mvcController.member;

import com.lgcns.icst.lecture.springjsp.lec1.biz.MemberBiz;
import com.lgcns.icst.lecture.springjsp.lec1.constant.SessionKey;
import com.lgcns.icst.lecture.springjsp.lec1.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/lec2/member")
public class MemberController {

    private final MemberBiz memberBiz;

    @Autowired
    public MemberController(MemberBiz memberBiz) {
        this.memberBiz = memberBiz;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView("lec2/member/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, @RequestParam String memberId, @RequestParam String memberPw) {

        try {
            MemberDTO memberDTO = memberBiz.login(memberId, memberPw);

            session.setAttribute(SessionKey.MEMBER_ID, memberDTO.getMemberId());
            session.setAttribute(SessionKey.MEMBER_NAME, memberDTO.getMemberName());

            return new ModelAndView("redirect:/lec2/free-boards");

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("lec2/errorPage");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("redirect:/lec2/member/login");
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView signUpPage() {
        return new ModelAndView("lec2/member/signUp");
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView signUp(@ModelAttribute MemberDTO memberDTO) {
        try {
            memberBiz.signUp(memberDTO);
            return new ModelAndView("redirect:/lec2/member/login");

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("lec2/errorPage");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updatePage(@SessionAttribute(name = SessionKey.MEMBER_ID, required = false) String memberId) {
        if (memberId == null) {
            return new ModelAndView("redirect:/lec2/member/login");
        }

        try {
            MemberDTO member = memberBiz.findById(memberId);

            ModelAndView modelAndView = new ModelAndView("lec2/member/update");
            modelAndView.addObject("member", member);
            return modelAndView;

        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("lec2/errorPage");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String memberUpdate(@SessionAttribute(name = SessionKey.MEMBER_ID) String memberId,
                               @RequestParam String memberName, @RequestParam String memberPw,
                               Model model, HttpSession session) {
        if (memberId == null) {
            return "redirect:/lec2/member/login";
        }

        try {
            memberBiz.update(memberId, memberPw, memberName);
            session.setAttribute(SessionKey.MEMBER_NAME, memberName);
            return "redirect:/lec2/free-boards";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "lec2/errorPage";
        }
    }
}
