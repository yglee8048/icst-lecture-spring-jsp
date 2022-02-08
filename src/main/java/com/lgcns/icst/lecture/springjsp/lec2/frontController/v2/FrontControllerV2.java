package com.lgcns.icst.lecture.springjsp.lec2.frontController.v2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "frontController-v2", urlPatterns = "/lec1/*")
public class FrontControllerV2 extends HttpServlet {

    private final Map<String, ControllerV2> urlMap;

    public FrontControllerV2(LoginControllerV2 loginControllerV2, FreeBoardListControllerV2 freeBoardListControllerV2) {
        this.urlMap = new HashMap<>();
        urlMap.put("/lec1/member/login", loginControllerV2);
        urlMap.put("/lec1/free-board/list", freeBoardListControllerV2);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI().replace(req.getContextPath(), "");
        if (urlMap.containsKey(requestURI)) {
            String method = req.getMethod();

            MyView myView;
            switch (method) {
                case "GET":
                    myView = urlMap.get(requestURI).doGet(req, resp);
                    break;
                case "POST":
                    myView = urlMap.get(requestURI).doPost(req, resp);
                    break;
                default:
                    System.out.println("Method unmatched! : " + method);
                    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    return;
            }

            if (myView != null) {
                myView.render(req, resp);
            }

        } else {
            // NOT_FOUND 에러
            System.out.println("URI unmatched! : " + requestURI);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
