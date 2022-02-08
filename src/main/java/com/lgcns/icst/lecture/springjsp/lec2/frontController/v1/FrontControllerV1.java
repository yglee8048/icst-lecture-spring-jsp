package com.lgcns.icst.lecture.springjsp.lec2.frontController.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "frontController-v1", urlPatterns = "/lec1/*")
public class FrontControllerV1 extends HttpServlet {

    private final Map<String, ControllerV1> urlMap;   // <URI, 컨트롤러>

    @Autowired
    public FrontControllerV1(ApplicationContext applicationContext) {
        this.urlMap = new HashMap<>();
        urlMap.put("/lec1/member/login", applicationContext.getBean(LoginControllerV1.class));
        urlMap.put("/lec1/free-board/list", applicationContext.getBean(FreeBoardListControllerV1.class));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 공통 처리

        // url 맵핑 -> 컨트롤러 호출
        String requestURI = req.getRequestURI().replace(req.getContextPath(), "");
        System.out.println("requestURI = " + requestURI);

        if (urlMap.containsKey(requestURI)) {
            String method = req.getMethod();
            // 컨트롤러 호출
            if (method.equals("GET")) {
                urlMap.get(requestURI).doGet(req, resp);
            } else if (method.equals("POST")) {
                urlMap.get(requestURI).doPost(req, resp);
            } else {
                // 405 에러
                System.out.println("Method unmatched! : " + method);
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } else {
            // NOT_FOUND 에러
            System.out.println("URI unmatched! : " + requestURI);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
