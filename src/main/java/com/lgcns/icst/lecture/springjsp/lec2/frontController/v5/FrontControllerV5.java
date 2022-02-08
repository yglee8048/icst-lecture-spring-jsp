package com.lgcns.icst.lecture.springjsp.lec2.frontController.v5;

import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.LoginControllerV3;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v3.MyModelAndView;
import com.lgcns.icst.lecture.springjsp.lec2.frontController.v4.FreeBoardListControllerV4;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontController-v5", urlPatterns = "/lec1/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> urlMap;
    private final List<Adapter> adapters;

    @Autowired
    public FrontControllerV5(FreeBoardListControllerV4 freeBoardListControllerV4, LoginControllerV3 loginControllerV3) {
        urlMap = new HashMap<>();
        adapters = new ArrayList<>();

        urlMap.put("/lec1/free-board/list", freeBoardListControllerV4);
        urlMap.put("/lec1/member/login", loginControllerV3);

        adapters.add(new ControllerV3Adapter());
        adapters.add(new ControllerV4Adapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI().replace(req.getContextPath(), "");
        if (urlMap.containsKey(requestURI)) {
            Object handler = urlMap.get(requestURI);
            for (Adapter adapter : adapters) {
                if (adapter.supports(handler)) {
                    MyModelAndView myModelAndView = adapter.handle(handler, req.getMethod(), getParamMap(req), req.getSession());
                    myModelAndView.render(req, resp);
                    return;
                }
            }
            throw new RuntimeException("Adapter unmatched!");

        } else {
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
