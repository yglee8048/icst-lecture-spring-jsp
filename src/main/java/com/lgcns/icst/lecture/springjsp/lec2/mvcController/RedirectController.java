package com.lgcns.icst.lecture.springjsp.lec2.mvcController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/lec2")
public class RedirectController {

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public RedirectView redirectTest(HttpServletResponse response) {
        return new RedirectView("/lec2/member/login");
    }
}
