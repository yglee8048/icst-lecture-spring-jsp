package com.lgcns.icst.lecture.springjsp.lec2.frontController.v5;

import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MethodNotAllowedException;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MyModelAndView;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v4.ControllerV4;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4Adapter implements Adapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public MyModelAndView handle(Object handler, String method, Map<String, String> paramMap, HttpSession session) {
        ControllerV4 controllerV4 = (ControllerV4) handler;
        Map<String, Object> model = new HashMap<>();
        if (method.equals("GET")) {
            String viewName = controllerV4.doGet(model, paramMap, session);
            return new MyModelAndView(viewName, model);

        } else if (method.equals("POST")) {
            String viewName = controllerV4.doPost(model, paramMap, session);
            return new MyModelAndView(viewName, model);
            
        } else {
            throw new MethodNotAllowedException();
        }
    }
}
