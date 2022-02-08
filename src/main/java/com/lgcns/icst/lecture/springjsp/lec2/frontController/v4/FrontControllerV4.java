package com.lgcns.icst.lecture.springjsp.lec2.frontController.v4;

import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MyModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "frontController-v4", urlPatterns = "/lec1/*")
public class FrontControllerV4 extends HttpServlet {

    private final Map<String, ControllerV4> urlMap;

    @Autowired
    public FrontControllerV4(LoginControllerV4 loginControllerV4, FreeBoardListControllerV4 freeBoardListControllerV4) {
        urlMap = new HashMap<>();
        urlMap.put("/lec1/member/login", loginControllerV4);
        urlMap.put("/lec1/free-board/list", freeBoardListControllerV4);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인증처리
        // 로깅
        
        String requestURI = req.getRequestURI().replace(req.getContextPath(), "");
        if (urlMap.containsKey(requestURI)) {
            String method = req.getMethod();

            Map<String, Object> model = new HashMap<>();
            ControllerV4 controllerV4 = urlMap.get(requestURI);
            if (method.equals("GET")) {
                String viewName = controllerV4.doGet(model, getParamMap(req), req.getSession());
                MyModelAndView myModelAndView = new MyModelAndView(viewName, model);
                myModelAndView.render(req, resp);

            } else if (method.equals("POST")) {
                String viewName = controllerV4.doPost(model, getParamMap(req), req.getSession());
                MyModelAndView myModelAndView = new MyModelAndView(viewName, model);
                myModelAndView.render(req, resp);

            } else {
                System.out.println("Method unmatched! : " + method);
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
            
        } else {
            // NOT_FOUND 에러
            System.out.println("URI unmatched! : " + requestURI);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        // 에러처리
    }

    private Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            paramMap.put(paramName, request.getParameter(paramName));
        }
        return paramMap;
    }
}
