package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyModelAndView {

    private String viewName;
    private final Map<String, Object> model;

    public MyModelAndView(String viewName) {
        this.viewName = viewName;
        this.model = new HashMap<>();
    }

    public MyModelAndView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public void addObject(String attributeName, Object object) {
        this.model.put(attributeName, object);
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (viewName.startsWith("redirect:")) {
            String redirectUrl = request.getContextPath() + viewName.replace("redirect:", "");
            response.sendRedirect(redirectUrl);
        } else {
            for (String s : model.keySet()) {
                request.setAttribute(s, model.get(s));
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/lec1/" + viewName + ".jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
