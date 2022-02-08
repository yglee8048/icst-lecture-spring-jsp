package com.lgcns.icst.lecture.springjsp.lec2.frontController.v2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    MyView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    MyView doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
