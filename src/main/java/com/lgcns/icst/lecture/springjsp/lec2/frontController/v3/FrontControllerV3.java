package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "frontController-v3", urlPatterns = "/lec1/*")
public class FrontControllerV3 extends HttpServlet {

    private final Map<String, ControllerV3> urlMap;

    @Autowired
    public FrontControllerV3(LoginControllerV3 loginControllerV3, FreeBoardListControllerV3 freeBoardListControllerV3) {
        urlMap = new HashMap<>();
        urlMap.put("/lec1/member/login", loginControllerV3);
        urlMap.put("/lec1/free-board/list", freeBoardListControllerV3);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI().replace(req.getContextPath(), "");
        if (urlMap.containsKey(requestURI)) {
            String method = req.getMethod();
            if (method.equals("GET")) {
                MyModelAndView myModelAndView = urlMap.get(requestURI).doGet(getParamMap(req), req.getSession());
                myModelAndView.render(req, resp);

            } else if (method.equals("POST")) {
                MyModelAndView myModelAndView = urlMap.get(requestURI).doPost(getParamMap(req), req.getSession());
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
