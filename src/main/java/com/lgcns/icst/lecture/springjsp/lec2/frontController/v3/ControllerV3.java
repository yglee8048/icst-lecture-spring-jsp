package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ControllerV3 {

    MyModelAndView doGet(Map<String, String> paramMap, HttpSession session);

    MyModelAndView doPost(Map<String, String> paramMap, HttpSession session);
}
