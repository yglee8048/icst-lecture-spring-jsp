package com.lgcns.icst.lecture.springjsp.lec2.frontController.v5;

import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MyModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface Adapter {

    boolean supports(Object handler);

    MyModelAndView handle(Object handler, String method, Map<String, String> paramMap, HttpSession session);
}
