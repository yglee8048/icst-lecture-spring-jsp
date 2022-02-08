package com.lgcns.icst.lecture.springjsp.lec2.frontController.v4;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface ControllerV4 {

    String doGet(Map<String, Object> model, Map<String, String> paramMap, HttpSession session);

    String doPost(Map<String, Object> model, Map<String, String> paramMap, HttpSession session);
}
