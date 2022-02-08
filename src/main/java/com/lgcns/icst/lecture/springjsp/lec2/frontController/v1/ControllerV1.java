package com.lgcns.icst.lecture.springjsp.lec2.frontController.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
