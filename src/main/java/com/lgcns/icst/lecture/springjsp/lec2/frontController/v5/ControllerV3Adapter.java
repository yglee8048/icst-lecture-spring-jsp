package com.lgcns.icst.lecture.springjsp.lec2.frontController.v5;

import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.ControllerV3;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MethodNotAllowedException;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MyModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class ControllerV3Adapter implements Adapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public MyModelAndView handle(Object handler, String method, Map<String, String> paramMap, HttpSession session) {
        ControllerV3 controllerV3 = (ControllerV3) handler;
        if (method.equals("GET")) {
            return controllerV3.doGet(paramMap, session);
        } else if (method.equals("POST")) {
            return controllerV3.doPost(paramMap, session);
        } else {
            throw new MethodNotAllowedException();
        }
    }
}
