package com.lgcns.icst.lecture.springjsp.lec2.mvcController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@WebServlet(name = "sampleController", urlPatterns = "/lec2/sample")
@Controller
@RequestMapping("/lec2/sample")
public class SampleController {

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView errorPage() {
        ModelAndView modelAndView = new ModelAndView("lec2/errorPage");
        modelAndView.addObject("errorMessage", "테스트 메시지 입니다.");
        return modelAndView;
    }

    @RequestMapping(value = "/teams/{teamId}/members/{memberId}", method = RequestMethod.GET)
    public String pathVariable(@PathVariable(value = "teamId") String var, @PathVariable Long memberId){
        System.out.println("var = " + var);
        System.out.println("memberId = " + memberId);
        return null;
    }
}
